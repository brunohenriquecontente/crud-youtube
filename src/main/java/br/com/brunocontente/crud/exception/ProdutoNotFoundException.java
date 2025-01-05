package br.com.brunocontente.crud.exception;

import org.springframework.http.HttpStatus;

public class ProdutoNotFoundException extends Exception{

    private final Issue issue;

    public ProdutoNotFoundException(String message){
        super(message);
        this.issue = new Issue(message, HttpStatus.NOT_FOUND.name());
    }
}
