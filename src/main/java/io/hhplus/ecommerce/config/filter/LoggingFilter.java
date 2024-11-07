package io.hhplus.ecommerce.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("[REQUEST] URI: {} {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());

        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);

        log.info("[RESPONSE] STATUS: {} ({}ms)", httpServletResponse.getStatus(), (System.currentTimeMillis() - startTime));
    }
}

