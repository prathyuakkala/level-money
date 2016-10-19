package com.capone.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.capone.web.jsonview.Views;

public class LMResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	List<LMTransaction> result;

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

	public List<LMTransaction> getResult() {
		return result;
	}

	public void setResult(List<LMTransaction> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "LMResponseBody [msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}

}
