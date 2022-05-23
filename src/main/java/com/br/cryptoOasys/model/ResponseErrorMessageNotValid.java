package com.br.cryptoOasys.model;

import java.util.List;

import com.br.cryptoOasys.exceptions.ResponseMessage;

public class ResponseErrorMessageNotValid extends ResponseMessage{
	
	private List<FieldNotValid> fields;

	public List<FieldNotValid> getFields() {
		return fields;
	}

	public void setFields(List<FieldNotValid> field) {
		this.fields = field;
	}		
}