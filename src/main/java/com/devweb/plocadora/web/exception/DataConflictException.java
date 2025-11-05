package com.devweb.plocadora.web.exception;

/**
 * Exceção lançada quando há um conflito de dados.
 * Exemplo: Tentativa de criar registro com chave única duplicada.
 * Resulta em resposta HTTP 400 Bad Request.
 */
public class DataConflictException extends IllegalArgumentException {

    public DataConflictException(String message) {
        super(message);
    }

    public DataConflictException(String resourceName, String field, Object value) {
        super(resourceName + " já existe com " + field + ": " + value);
    }
}
