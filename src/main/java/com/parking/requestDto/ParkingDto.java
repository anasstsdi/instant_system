package com.parking.requestDto;

import java.io.Serializable;

public class ParkingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FieldsDto fields;
	private GeometryDto geometry;

	public FieldsDto getFields() {
		return fields;
	}

	public void setFields(FieldsDto fields) {
		this.fields = fields;
	}

	public GeometryDto getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryDto geometry) {
		this.geometry = geometry;
	}

}
