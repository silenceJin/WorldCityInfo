package example.world.common;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import example.world.common.exception.BindingResultException;
import io.netty.util.CharsetUtil;

public class ParameterDispose {
	private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

	/**
	 * @param src
	 *            需要防止通配符注入的字符串
	 * @param esc
	 *            转义字符 /
	 * @return src = "%_%" return "/%/_/%"
	 */
	public static String preventWildcardInject(String src, char esc) {
		if (StringUtils.isEmpty(src)) {
			return src;
		}
		StringBuilder builder = new StringBuilder();
		int length = src.length();
		for (int i = 0; i < length; i++) {
			char charAt = src.charAt(i);
			if (Character.isLetterOrDigit(charAt)) {
				builder.append(charAt);
			} else {
				builder.append(esc).append(charAt);
			}

		}
		return builder.toString();
	}

	/**
	 * @param src
	 *            需要防止通配符注入的字符串
	 * @return rc = "%_%" return "/%/_/%"
	 */
	public static String preventWildcardInject(String src) {
		return preventWildcardInject(src, '/');
	}

	public static String md5(String src) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] digest = messageDigest.digest(src.getBytes(CharsetUtil.UTF_8));
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : digest) {
				String str = Integer.toHexString(b & 0xff);
				if (str.length() == 1) {
					stringBuilder.append("0");
				}
				stringBuilder.append(str);
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void throwError(BindingResult bindingResult, String... fields) {
		if (bindingResult.hasErrors()) {
			for (String field : fields) {
				FieldError fieldError = bindingResult.getFieldError(field);
				if (fieldError != null) {
					throw new BindingResultException(fieldError.getDefaultMessage());
				}
			}
		}
	}

	public static void throwError(Object object, String... fields) {
		List<String> list = Arrays.asList(fields);
		Set<ConstraintViolation<Object>> validate = validatorFactory.getValidator().validate(object);
		if (validate != null && validate.size() > 0) {
			for (ConstraintViolation<Object> constraintViolation : validate) {
				if (list.contains(constraintViolation.getPropertyPath().toString())) {
					throw new BindingResultException(constraintViolation.getMessage());
				}
			}
		}
	}
}
