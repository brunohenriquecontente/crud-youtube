package br.com.brunocontente.crud.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProdutoNotFoundException.class)
    public Issue handleProdutoNotFoundException(ProdutoNotFoundException e){
        String requestId = MDC.get("requestId");
        log.error("[{}] Exception occorred: {} ",requestId, e.getMessage());
        return new Issue(e.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Issue handleException(Exception e){
        String requestId = MDC.get("requestId");
        log.error("[{}] Exception occorred: {} ",requestId, e.getMessage());
        return new Issue(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
    }
}
