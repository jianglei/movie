package com.wuxianmeihao.core.domain;

public class Movie {
	private int id;
	private int movieGroupId;
	private String name;
	private String url;
	private int isDel;
	private String createTime;
	
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieGroupId() {
		return movieGroupId;
	}
	public void setMovieGroupId(int movieGroupId) {
		this.movieGroupId = movieGroupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
