package com.linekong.erating.api.pojo.response;

public class SdkConfigDTO {
	
	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("name=%s,value=%s", name,value);
	}
	
	

}
