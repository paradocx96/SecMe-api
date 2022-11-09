///*
// * SSD - SecMe API
// *
// * @author IT19180526 - S.A.N.L.D. Chandrasiri
// * @version 1.0
// */
//package com.secme.security;
//
//import com.secme.model.ErrorMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.servlet.http.HttpServletRequest;
//
///*
// * Common Error Handler
// *
// * @author IT19180526 - S.A.N.L.D. Chandrasiri
// * @version 1.0
// *
// * */
//@RestControllerAdvice
//public class GlobalErrorHandler {
//
//    // Error Response for Not Found
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ErrorMessage handleNotFound(final HttpServletRequest request, final Exception error) {
//        return ErrorMessage.from("Not Found");
//    }
//
//    // Error Response for Permission Denied
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(AccessDeniedException.class)
//    public ErrorMessage handleAccessDenied(final HttpServletRequest request, final Exception error) {
//        return ErrorMessage.from("Permission denied");
//    }
//
//    // Error Response for Internal Server Error
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Throwable.class)
//    public ErrorMessage handleInternalError(final HttpServletRequest request, final Exception error) {
//        return ErrorMessage.from(error.getMessage());
//    }
//}
