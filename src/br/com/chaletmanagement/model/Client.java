package br.com.chaletmanagement.model;

import java.time.LocalDate;

public class Client {
	private Integer clientId;
	private String name;
	private String RG;
	private LocalDate birthday;

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String RG) {
		this.RG = RG;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
