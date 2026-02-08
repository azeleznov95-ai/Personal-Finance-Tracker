    package com.example.clothesshop.exeptions;

    import com.example.clothesshop.dto.ErrorResponse;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.time.LocalDateTime;

    @RestControllerAdvice

    public class ExceptionsHandle {
        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ErrorResponse> ConflictExceptionHandle(ConflictException ex){
            ErrorResponse errorResponse= new ErrorResponse();
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(409);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(409).body(errorResponse);

        }
        @ExceptionHandler(IncorrectLoginOrPasswordException.class)
        public ResponseEntity<ErrorResponse>  IncorrectLoginOrPasswordExceptionHandle(IncorrectLoginOrPasswordException ex){
            ErrorResponse errorResponse= new ErrorResponse();
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(401);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(401).body(errorResponse);

        }
        @ExceptionHandler(EmptyUser.class)
        public ResponseEntity<ErrorResponse> EmptyUserHandle(EmptyUser ex){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(400);
            return ResponseEntity.status(400).body(errorResponse);

        }
        @ExceptionHandler(EmptyCategory.class)
        public ResponseEntity<ErrorResponse> EmptyCategoryHandle(EmptyCategory ex){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(400);
            return ResponseEntity.status(400).body(errorResponse);

        }
        @ExceptionHandler(IdIsInvalid.class)
        public ResponseEntity<ErrorResponse> IdIsInvalidHandle(IdIsInvalid ex){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(400);
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
        
    }
