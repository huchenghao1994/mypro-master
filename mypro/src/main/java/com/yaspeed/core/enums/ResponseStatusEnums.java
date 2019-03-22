package com.yaspeed.core.enums;

public enum ResponseStatusEnums {

	PARANOTVALID(12022,"Bad Registry Parameter");
	
	private final int value;

	private final String reasonPhrase;
	
	private ResponseStatusEnums(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}
	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}
}
