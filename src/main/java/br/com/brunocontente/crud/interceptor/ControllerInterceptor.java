package br.com.brunocontente.crud.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {


@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String requestId = UUID.randomUUID().toString();
    MDC.put("requestId", requestId);
    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
    log.info("[{}] Request received: Método: {} URI: {} RemoteAddress: {}", requestId, request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
     return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)   {
        String requestId = MDC.get("requestId");
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("[{}] Request processed: Método: {} URI: {} Status: {} Execution time: {} ms", requestId, request.getMethod(), request.getRequestURI(), response.getStatus(), executionTime);
    }
}
