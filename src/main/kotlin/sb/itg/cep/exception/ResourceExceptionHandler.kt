package sb.itg.cep.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import sb.itg.cep.exception.model.StandardError

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(
        e: NotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError<String>> {
        val exception = StandardError(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            path = request.servletPath,
            message = e.message!!,
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception)
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(
        e: BadRequestException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError<String>> {
        val exception = StandardError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            path = request.servletPath,
            message = e.message!!,
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validation(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError<Map<String, List<String?>>>> {

        val errors = e.fieldErrors.groupBy(FieldError::getField, FieldError::getDefaultMessage)

        return ResponseEntity.badRequest().body(
            StandardError(
                status = HttpStatus.BAD_REQUEST.value(),
                message = HttpStatus.BAD_REQUEST.name,
                error = errors,
                path = request.servletPath
            )
        )
    }
}