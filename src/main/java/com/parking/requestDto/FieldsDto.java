package com.parking.requestDto;

import java.io.Serializable;

public class FieldsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String orgahoraires;
	private String tarif_15;
	private String tarif_1h30;
	private String tarif_30;
	private String tarif_1h;
	private String tarif_3h;
	private String tarif_2h;
	private String tarif_4h;
	private Integer max;
	private Integer free;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgahoraires() {
		return orgahoraires;
	}

	public void setOrgahoraires(String orgahoraires) {
		this.orgahoraires = orgahoraires;
	}

	public String getTarif_15() {
		return tarif_15;
	}

	public void setTarif_15(String tarif_15) {
		this.tarif_15 = tarif_15;
	}

	public String getTarif_1h30() {
		return tarif_1h30;
	}

	public void setTarif_1h30(String tarif_1h30) {
		this.tarif_1h30 = tarif_1h30;
	}

	public String getTarif_30() {
		return tarif_30;
	}

	public void setTarif_30(String tarif_30) {
		this.tarif_30 = tarif_30;
	}

	public String getTarif_1h() {
		return tarif_1h;
	}

	public void setTarif_1h(String tarif_1h) {
		this.tarif_1h = tarif_1h;
	}

	public String getTarif_3h() {
		return tarif_3h;
	}

	public void setTarif_3h(String tarif_3h) {
		this.tarif_3h = tarif_3h;
	}

	public String getTarif_2h() {
		return tarif_2h;
	}

	public void setTarif_2h(String tarif_2h) {
		this.tarif_2h = tarif_2h;
	}

	public String getTarif_4h() {
		return tarif_4h;
	}

	public void setTarif_4h(String tarif_4h) {
		this.tarif_4h = tarif_4h;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

}
