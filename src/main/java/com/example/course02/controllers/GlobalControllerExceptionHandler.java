package com.example.course02.controllers;

import com.example.course02.exceptions.HorrendousException;
import com.example.course02.exceptions.TooManyQuestionsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(TooManyQuestionsException.class)
    public ResponseEntity<String> handleTooMuchQuestionsException(WebRequest request, TooManyQuestionsException e){
        logger.error("Too much questions requested" + request.getDescription(true));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. There is not enough questions to satisfy the request" + e);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<String> handleMethodNotAllowedException(WebRequest request, MethodNotAllowedException e){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Error. Method is not accessible");
    }

    @ExceptionHandler(HorrendousException.class)
    public ResponseEntity<String> handleHorrendousException(WebRequest request, MethodNotAllowedException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Something went terribly wrong");
    }
}
