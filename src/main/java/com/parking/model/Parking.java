package com.parking.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer nhits;

	private Parameters parameters;

	@JsonProperty("records")
	private List<Records> records;

	public Integer getNhits() {
		return nhits;
	}

	public void setNhits(Integer nhits) {
		this.nhits = nhits;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public List<Records> getRecords() {
		return records;
	}

	public void setRecords(List<Records> records) {
		this.records = records;
	}

}
