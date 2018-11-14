package example.world.common;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ControllerLogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogAspect.class);

	@Pointcut("execution(public * example.world.controller.*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		Optional<ServletRequestAttributes> optional = Optional
				.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()));
		optional.ifPresent(servletRequestAttributes -> {
			Optional<HttpServletRequest> nullable = Optional.ofNullable(servletRequestAttributes.getRequest());
			nullable.ifPresent(request -> {
				LOGGER.info("请求的URL：{} ---->>> 执行的方法：{} ---->>> 请求的参数：{}", request.getRequestURI(),
						joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()));
			});
		});

	}

	@AfterReturning(returning = "response", pointcut = "log()")
	public void doAfterReturning(Object response) {
		LOGGER.info("返回的数据是：{}", response);
	}
}
