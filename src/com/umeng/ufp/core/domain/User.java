package com.umeng.ufp.core.domain;

import java.util.Date;

import com.google.protobuf.ByteString;
import com.umeng.ufp.core.domain.protobuf.UserProtos.UserEntry;

public class User {
    private Integer id;

    private String email;
    private String nickname;
    private String password;
    private String type;

	private String name;
    private String phone;
    private String logoUrl;
    private String logoName;
    private String blacklist;
    private String status;
    
    private Integer loginTimes;
    private Date lastLoginTime;
    
	public ByteString serialize() {
		UserEntry.Builder userEntry = UserEntry.newBuilder();
		
		if(id != null) userEntry.setId(id);
		if(email != null) userEntry.setEmail(email);
		if(nickname != null) userEntry.setNickname(nickname);
		if(password != null) userEntry.setPassword(password);
		if(type != null) userEntry.setType(type);
		if(name != null) userEntry.setName(name);
		if(phone != null) userEntry.setPhone(phone);
		if(logoUrl != null) userEntry.setLogoUrl(logoUrl);
		if(logoName != null) userEntry.setLogoName(logoName);
		if(blacklist != null) userEntry.setBlacklist(blacklist);
		if(status != null) userEntry.setStatus(status);
		if(loginTimes != null) userEntry.setLoginTimes(loginTimes);
		if(lastLoginTime != null) userEntry.setLastLoginTime(lastLoginTime.getTime());

		return userEntry.build().toByteString();
	}
    
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", nickname=" + nickname
				+ ", password=" + password + ", type=" + type + ", name="
				+ name + ", phone=" + phone + ", logoUrl=" + logoUrl
				+ ", logoName=" + logoName + ", blacklist=" + blacklist
				+ ", status=" + status + ", loginTimes=" + loginTimes
				+ ", lastLoginTime=" + lastLoginTime + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}

}