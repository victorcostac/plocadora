package com.devweb.plocadora.web.exception;

import com.devweb.plocadora.web.model.ErroRequisicaoApiModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Interceptador global de exceções para a API.
 * Retorna respostas no formato ErroRequisicaoApiModel definido na especificação
 * OpenAPI.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de argumento ilegal (validações de negócio).
     * Exemplo: "Ator não encontrado", "Número de inscrição já cadastrado"
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("400");
        erro.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Trata exceções de validação de argumentos de método (Bean Validation).
     * Exemplo: @NotNull, @NotBlank, @Size, etc.
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("400");

        // Extrai a primeira mensagem de erro de validação
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Erro de validação nos dados fornecidos");

        erro.setMensagem(mensagem);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Trata exceções de parsing de JSON.
     * Exemplo: JSON malformado, tipos incompatíveis
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 400 Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("400");
        erro.setMensagem("Formato de JSON inválido ou tipos de dados incompatíveis");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Trata exceções de tipo de argumento incompatível.
     * Exemplo: passar string onde espera-se número no path parameter
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("400");
        erro.setMensagem("Tipo de dado inválido para o parâmetro '" + ex.getName() + "'");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Trata exceções de formato de número.
     * Exemplo: passar "abc" onde espera-se um número
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 400 Bad Request
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleNumberFormatException(NumberFormatException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("400");
        erro.setMensagem("Formato de número inválido. Esperado um valor numérico");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Trata exceções de recurso não encontrado.
     * Pode ser usado para criar uma exceção customizada ResourceNotFoundException
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 404 Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("404");
        erro.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    /**
     * Trata exceções genéricas não capturadas pelos handlers específicos.
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleGenericException(Exception ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("500");
        erro.setMensagem("Erro interno do servidor: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    /**
     * Trata exceções de estado ilegal.
     * Usado para operações que não podem ser realizadas no estado atual do recurso.
     * Exemplo: tentar finalizar uma locação já finalizada.
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 409 Conflict
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleIllegalStateException(IllegalStateException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("409");
        erro.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    /**
     * Trata exceções de operação não suportada.
     * 
     * @param ex exceção lançada
     * @return ResponseEntity com HTTP 400 Bad Request
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ErroRequisicaoApiModel> handleUnsupportedOperationException(
            UnsupportedOperationException ex) {
        ErroRequisicaoApiModel erro = new ErroRequisicaoApiModel();
        erro.setStatus("400");
        erro.setMensagem("Operação não suportada: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
