package hello.hellokotlin.temp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.fieldErrors.forEach {
            error -> errors[error.field] = error.defaultMessage ?: "잘못된 입력입니다"
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }
}