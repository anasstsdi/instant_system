package com.parking.model;

import java.io.Serializable;

public class Records implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String datasetid;
	private String recordid;
	private String record_timestamp;
	private Fields fields;
	private Geometry geometry;

	public String getDatasetid() {
		return datasetid;
	}

	public void setDatasetid(String datasetid) {
		this.datasetid = datasetid;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getRecord_timestamp() {
		return record_timestamp;
	}

	public void setRecord_timestamp(String record_timestamp) {
		this.record_timestamp = record_timestamp;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

}
