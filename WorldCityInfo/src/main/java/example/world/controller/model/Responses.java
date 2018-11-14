package example.world.controller.model;

import java.io.Serializable;

public class Responses implements Serializable {
	public static enum Status {
		/**
		 * 成功
		 */
		SUCCESS {
			@Override
			public Responses toRespones(Object data) {
				return new Responses(200, "success", data);
			}
		},
		/**
		 * 错误
		 */
		ERROE {
			@Override
			public Responses toRespones(Object data) {
				return new Responses(500, "error", data);
			}
		},
		/**
		 * 警告
		 */
		WARN {
			@Override
			public Responses toRespones(Object data) {
				return new Responses(400, "warn", data);
			}
		},
		/**
		 * 账号或者密码错误！
		 */
		ACCOUNT_OR_PASSWORD_ERROR {
			@Override
			public Responses toRespones(Object data) {
				return new Responses(401, "账号或者密码错误！", data);
			}
		};
		public abstract Responses toRespones(Object data);

	}

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;

	private Object data;

	public Responses() {
		super();
	}

	public Responses(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Responses(Integer status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Respones [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
