package com.example.sampletvapp.model;


import com.google.gson.annotations.SerializedName;


public class Token{

	@SerializedName("access_token")
	private String accessToken;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	@Override
 	public String toString(){
		return 
			"Token{" + 
			"access_token = '" + accessToken + '\'' + 
			"}";
		}
}