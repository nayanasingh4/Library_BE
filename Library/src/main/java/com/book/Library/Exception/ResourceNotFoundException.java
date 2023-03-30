package com.book.Library.Exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9079454849611061074L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
