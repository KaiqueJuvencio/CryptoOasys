package com.br.cryptoOasys.model;

import java.util.List;

import com.br.cryptoOasys.exceptions.ResponseErrorMessage;

public class ResponseErrorMessageNotValid extends ResponseErrorMessage{
	
	private List<FieldNotValid> fields;

	public List<FieldNotValid> getFields() {
		return fields;
	}

	public void setFields(List<FieldNotValid> field) {
		this.fields = field;
	}		
}