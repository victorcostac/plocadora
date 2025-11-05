package com.devweb.plocadora.web.exception;

/**
 * Exceção lançada quando um recurso não é encontrado.
 * Resulta em resposta HTTP 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " não encontrado com ID: " + id);
    }

    public ResourceNotFoundException(String resourceName, String field, Object value) {
        super(resourceName + " não encontrado com " + field + ": " + value);
    }
}
