package com.example.sampletvapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TvChannel{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("total_page")
	private int totalPage;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	@SerializedName("total_results")
	private int totalResults;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setTotalPage(int totalPage){
		this.totalPage = totalPage;
	}

	public int getTotalPage(){
		return totalPage;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"TvChannel{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",total_page = '" + totalPage + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}