package com.example.sampletvapp.model;


import com.google.gson.annotations.SerializedName;


public class ListGenresItem{

	@SerializedName("genre")
	private String genre;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("modified_at")
	private String modifiedAt;

	public void setGenre(String genre){
		this.genre = genre;
	}

	public String getGenre(){
		return genre;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setModifiedAt(String modifiedAt){
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedAt(){
		return modifiedAt;
	}

	@Override
 	public String toString(){
		return 
			"ListGenresItem{" + 
			"genre = '" + genre + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",modified_at = '" + modifiedAt + '\'' + 
			"}";
		}
}