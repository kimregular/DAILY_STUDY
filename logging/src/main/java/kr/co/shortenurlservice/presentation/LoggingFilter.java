package kr.co.shortenurlservice.presentation;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(httpServletRequest);

            String url = wrappedRequest.getRequestURI();
            String method = wrappedRequest.getMethod();
            String body = wrappedRequest.getReader().lines().reduce("", String::concat);

            log.trace("Incoming Request : URL={}, Method={}, Body={}", url, method, body);
            log.trace("Incoming Request Headers : {}", wrappedRequest.getHeaderNames());
            filterChain.doFilter(wrappedRequest, servletResponse);
        } else {
            // httpServletRequest가 아닌 경우 그대로 전달
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
