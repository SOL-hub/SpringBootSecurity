package com.test.zaritalkParksol.domin;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("UserInfoV2")
public class Use2 extends User{
	
	private String auth;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Use2(int string, String id, String nickname, String account_type, String account_id, String quit,
			Date joinDate, String auth) {
		super(string, id, nickname, account_type, account_id, quit, joinDate);
		this.auth = auth;
	}

	/*
	 * public Use2() { // TODO Auto-generated constructor stub }
	 */
	

}
