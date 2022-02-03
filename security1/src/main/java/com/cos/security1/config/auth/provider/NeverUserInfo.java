package com.cos.security1.config.auth.provider;

import java.util.Map;

public class NeverUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attruibutes; //getAttributes();
	
	public NeverUserInfo(Map<String, Object> attributes) {
		this.attruibutes= attributes;
	}
	
	@Override
	public String getProviderId() {
		
		return (String) attruibutes.get("sub");
	}

	@Override
	public String getProvider() {
		return "google";
	}

	@Override
	public String getEmail() {
		return (String)attruibutes.get("email");
	}

	@Override
	public String getName() {
		return (String) attruibutes.get("name");
	}

}
