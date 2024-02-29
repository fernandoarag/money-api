package com.fernandoarag.moneyapi.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fernandoarag.moneyapi.api.service.exception.NonexistentOrInactiveCategoryException;
import com.fernandoarag.moneyapi.api.service.exception.NonexistentOrInactivePersonException;

@ControllerAdvice
public class MoneyapiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("system.invalid", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<ReponseError> errors = Arrays.asList(new ReponseError(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ReponseError> errors = createListOfErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
            WebRequest request) {

        String userMessage = messageSource.getMessage("system.not-found", null,
                LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<ReponseError> errors = Arrays.asList(new ReponseError(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {

        String userMessage = messageSource.getMessage("system.access-denied", null,
                LocaleContextHolder.getLocale());
        String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<ReponseError> errors = Arrays.asList(new ReponseError(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NonexistentOrInactivePersonException.class })
    public ResponseEntity<Object> handleNonexistentOrInactivePersonException(NonexistentOrInactivePersonException ex,
            WebRequest request) {
        String userMessage = messageSource.getMessage("peopleModel.not-found", null,
                LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();

        List<ReponseError> errors = Arrays.asList(new ReponseError(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NonexistentOrInactiveCategoryException.class })
    public ResponseEntity<Object> handleNonexistentOrInactiveCategoryException(
            NonexistentOrInactiveCategoryException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("categoriesModel.not-found", null,
                LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();

        List<ReponseError> errors = Arrays.asList(new ReponseError(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<ReponseError> createListOfErrors(BindingResult bindingResult) {
        List<ReponseError> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            errors.add(new ReponseError(userMessage, developerMessage));
        }

        return errors;
    }

    public static class ReponseError {
        private String userMessage;
        private String developerMessage;

        public ReponseError(String userMessage, String developerMessage) {
            this.userMessage = userMessage;
            this.developerMessage = developerMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public void setUserMessage(String userMessage) {
            this.userMessage = userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }

    }
}
