package com.springfirat.springbootrestapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

// Controller da dönüş tipimiz ResponseEntity olduğu için ResponseEntityExceptionHandler'ı extends ederim.
//Controller ımıza advice olacak bu sınıfımız.
//WebRequest spring in requestlerinden gelen özellikleri alır.
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponse> illegalException(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"1000",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

    //Burda kendi exeption ımızı fırlatıyoruz yukarıda illegal fırlatıyorduk.
    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<ExceptionResponse> userNotFound(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"2000",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }
    //Tahmin edemedğimiz bir hata olursa bu daha genel.
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> exception(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"5000",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }
}
