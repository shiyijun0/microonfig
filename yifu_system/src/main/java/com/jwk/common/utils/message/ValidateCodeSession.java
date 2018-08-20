package com.jwk.common.utils.message;

import java.io.Serializable;

public class ValidateCodeSession implements Serializable {

	private static final long serialVersionUID = -2721704613668810479L;

	private String validate_code;
	private long create_time;

	public ValidateCodeSession(String validate_code) {
		this.create_time = System.currentTimeMillis();
		this.validate_code = validate_code;
	}

	public String getValidate_code() {
		return validate_code;
	}

	public long getCreate_time() {
		return create_time;
	}

}
