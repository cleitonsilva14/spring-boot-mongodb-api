package io.springboot.mongodb.api.exception;

public class TodoCollectionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TodoCollectionException(String message) {
		super(message);
	}
	
	public static String notFoundException(String id) {
		return "Todo wit id: '" + id + "' not found!";
	}
	
	public static String todoAlreadyExists() {
		return "Todo with given name already exists";
	}

}
