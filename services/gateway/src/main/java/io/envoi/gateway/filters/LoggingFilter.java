package io.envoi.gateway.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class LoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/actuator")) {
            log.debug("Actuator request: {} {}", httpRequest.getMethod(), path);
            chain.doFilter(request, response);
            return;
        }

        long startTime = System.currentTimeMillis();

        log.info("Incoming request: {} {}", httpRequest.getMethod(), path);

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        log.info("Request processed in {} ms", duration);
    }
}