package io.hhplus.ecommerce.config.aop;

import io.hhplus.ecommerce.common.annotation.DistributedLock;
import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.common.util.CustomSpringELParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {

    private final RedissonClient redissonClient;

    @Around("@annotation(io.hhplus.ecommerce.common.annotation.DistributedLock)")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DistributedLock distributedLock = methodSignature.getMethod().getAnnotation(DistributedLock.class);

        String key = (String) CustomSpringELParser.getDynamicValue(methodSignature.getParameterNames(), joinPoint.getArgs(), distributedLock.key());

        RLock lock = redissonClient.getLock("LOCK:" + key);

        try {

            boolean available = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());

            if (!available) {
                throw new IllegalStateException(ExceptionMessage.REDIS_LOCK_ACQUIRE_FAILED.getMessage());
            }

            log.info("락 획득(KEY: {})", key);
            return joinPoint.proceed();
        } catch (InterruptedException e) {
            throw new IllegalStateException(ExceptionMessage.REDIS_LOCK_ACQUIRE_FAILED.getMessage());
        } finally {
            if(lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("락 해제(KEY: {})", key);
            }
        }
    }
}
