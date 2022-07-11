package com.smi.parents_place.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParticipantExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ParticipantIncorrectData> handleNoSuchParticipantException(NoSuchParticipantException exception){
        ParticipantIncorrectData incorrectData = new ParticipantIncorrectData();
        incorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ParticipantIncorrectData> handleAllException(Exception exception){
        ParticipantIncorrectData incorrectData = new ParticipantIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);}


}
