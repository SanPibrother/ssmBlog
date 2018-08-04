package blog.exception;

public class MyException extends Exception{
	/**
	 * 自定义的预期异常
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public MyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyException(String message) {
		super(message);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
