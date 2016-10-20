package com.capone.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.capone.web.jsonview.Views;
/**
 * Model class for the JSON Representation for Response Object
 * @author Prathyusha
 * @since 10-18-2016
 */
public class LMResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	Object result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "LMResponseBody [msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}

}
