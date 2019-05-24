package pl.mecinapatrycja.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mecinapatrycja.dto.ErrorDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handler(Exception e, HttpServletRequest httpServletRequest) {
        Random r = new Random();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        final int number = 100000000;
        errorDto.setEvent(ThreadLocalRandom.current().nextLong(number));
        if (e instanceof BaseException) {
            errorDto.setMessage(e.getMessage());
            errorDto.setEvent(ThreadLocalRandom.current().nextLong(number));
        }
        logger.error(errorDto.getMessage());
        if (errorDto.getMessage().equals("400 Bad Request")) {
            errorDto.setMessage("Coś poszło nie tak... :(");
        }
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
