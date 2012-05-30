package com.umeng.ufp.core.domain;

import com.google.protobuf.ByteString;
import com.umeng.ufp.core.domain.protobuf.AppProtos.AppEntry;

public class App {
    private Integer id;

    private Integer userId;
    private String name;
    private String platform;
    private String description;
    private Integer state;
    
	public ByteString serialize() {
		AppEntry.Builder appEntry = AppEntry.newBuilder();
		
		if(id != null) appEntry.setId(id);
		if(name != null) appEntry.setName(name);
		if(platform != null) appEntry.setPlatform(platform);
		if(description != null) appEntry.setDescription(description);
		
		return appEntry.build().toByteString();
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", platform=" + platform + ", description=" + description
				+ ", state=" + state + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}