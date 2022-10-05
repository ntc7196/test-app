package com.cmc.trans.service;

import com.cmc.trans.model.ResponseDataAPI;
import javassist.NotFoundException;
import org.hibernate.PessimisticLockException;
import org.hibernate.StaleObjectStateException;
import org.hibernate.StaleStateException;
import org.hibernate.exception.LockTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.OptimisticLockException;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

    String locked= "Transaction failed due to other transaction in processing";

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<?> handleSQLException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = LockTimeoutException.class)
    public ResponseEntity<?> handleLockTimeoutException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = OptimisticLockException.class)
    public ResponseEntity<?> handleOptimisticLockException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = PessimisticLockException.class)
    public ResponseEntity<?> handlePessimisticLockException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.LOCKED).body(new ResponseDataAPI(ex.getMessage()));
    }

    @ExceptionHandler(value = StaleObjectStateException.class)
    public ResponseEntity<?> handleStaleObjectStateException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(locked));
    }

    @ExceptionHandler(value = StaleStateException.class)
    public ResponseEntity<?> handleStaleStateException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(locked));
    }

}