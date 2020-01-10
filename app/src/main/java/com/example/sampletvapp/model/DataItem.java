package com.example.sampletvapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("image")
	private String image;

	@SerializedName("listPeople")
	private List<Object> listPeople;

	@SerializedName("release_year")
	private int releaseYear;

	@SerializedName("rating")
	private Object rating;

	@SerializedName("runtime")
	private Object runtime;

	@SerializedName("description")
	private Object description;

	@SerializedName("play_with")
	private int playWith;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("listStudio")
	private List<Object> listStudio;

	@SerializedName("id")
	private int id;

	@SerializedName("source_id")
	private int sourceId;

	@SerializedName("modified_at")
	private String modifiedAt;

	@SerializedName("listGenres")
	private List<ListGenresItem> listGenres;

	@SerializedName("slug")
	private String slug;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setListPeople(List<Object> listPeople){
		this.listPeople = listPeople;
	}

	public List<Object> getListPeople(){
		return listPeople;
	}

	public void setReleaseYear(int releaseYear){
		this.releaseYear = releaseYear;
	}

	public int getReleaseYear(){
		return releaseYear;
	}

	public void setRating(Object rating){
		this.rating = rating;
	}

	public Object getRating(){
		return rating;
	}

	public void setRuntime(Object runtime){
		this.runtime = runtime;
	}

	public Object getRuntime(){
		return runtime;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setPlayWith(int playWith){
		this.playWith = playWith;
	}

	public int getPlayWith(){
		return playWith;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setListStudio(List<Object> listStudio){
		this.listStudio = listStudio;
	}

	public List<Object> getListStudio(){
		return listStudio;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSourceId(int sourceId){
		this.sourceId = sourceId;
	}

	public int getSourceId(){
		return sourceId;
	}

	public void setModifiedAt(String modifiedAt){
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedAt(){
		return modifiedAt;
	}

	public void setListGenres(List<ListGenresItem> listGenres){
		this.listGenres = listGenres;
	}

	public List<ListGenresItem> getListGenres(){
		return listGenres;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"image = '" + image + '\'' + 
			",listPeople = '" + listPeople + '\'' + 
			",release_year = '" + releaseYear + '\'' + 
			",rating = '" + rating + '\'' + 
			",runtime = '" + runtime + '\'' + 
			",description = '" + description + '\'' + 
			",play_with = '" + playWith + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",listStudio = '" + listStudio + '\'' + 
			",id = '" + id + '\'' + 
			",source_id = '" + sourceId + '\'' + 
			",modified_at = '" + modifiedAt + '\'' + 
			",listGenres = '" + listGenres + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}