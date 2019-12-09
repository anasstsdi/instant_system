package com.parking.model;

import java.io.Serializable;

public class Parameters implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataset;
	private String timezone;
	private Integer rows;
	private String format;

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
