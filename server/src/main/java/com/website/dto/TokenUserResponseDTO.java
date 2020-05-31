package com.website.dto;

import io.swagger.annotations.ApiModelProperty;

public class TokenUserResponseDTO {

	@ApiModelProperty(position = 1)
	private String token;

	@ApiModelProperty(position = 2)
	private boolean error;

	@ApiModelProperty(position = 3)
	private String message;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
