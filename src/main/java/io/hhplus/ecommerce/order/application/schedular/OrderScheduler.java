package io.hhplus.ecommerce.order.application.schedular;

import io.hhplus.ecommerce.order.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

    private final OrderService orderService;

    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(cacheNames = "popularProducts", key = "'popularProductsIds'")
    public void refreshPopularProductsCached() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDateTime = now.minusDays(3).toLocalDate().atStartOfDay();
        LocalDateTime endDateTime = now.minusDays(1).toLocalDate().atTime(23, 59, 59);

        orderService.getPopularProducts(startDateTime, endDateTime);
    }
}
