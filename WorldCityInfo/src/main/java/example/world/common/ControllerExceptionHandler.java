package example.world.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import example.world.common.exception.BindingResultException;
import example.world.controller.model.Responses;

@RestControllerAdvice
public class ControllerExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public Responses errorHandler(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		Responses respones = Responses.Status.ERROE.toRespones(null);
		respones.setMessage(String.valueOf(exception.getMessage()));
		return respones;
	}

	@ExceptionHandler(RuntimeException.class)
	public Responses warnHandler(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		Responses respones = Responses.Status.WARN.toRespones(null);
		respones.setMessage(String.valueOf(exception.getMessage()));
		return respones;
	}

	@ExceptionHandler(BindingResultException.class)
	public Responses bindingResult(Exception exception) {
		Responses respones = Responses.Status.WARN.toRespones(null);
		respones.setMessage(String.valueOf(exception.getMessage()));
		return respones;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Responses bindingResult(MethodArgumentNotValidException exception) {
		LOGGER.info("MethodArgumentNotValidException");
		BindingResult bindingResult = exception.getBindingResult();
		Responses respones = Responses.Status.WARN.toRespones(null);
		respones.setMessage(
				String.valueOf(bindingResult.getAllErrors().stream().findFirst().get().getDefaultMessage()));
		return respones;
	}

}
