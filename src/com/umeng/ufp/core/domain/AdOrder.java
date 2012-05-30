package com.umeng.ufp.core.domain;

import java.util.Date;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.umeng.ufp.core.domain.protobuf.AdOrderProtos.AdOrderEntry;

public class AdOrder {
    private Integer id;
    private Integer userId;
    
    private String name;
    private String comments;    
    private String customer;
	private Date startTime;
    private Date endTime;
    private Integer state;
    
    public ByteString serialize() {
		AdOrderEntry.Builder adOrderEntry = AdOrderEntry.newBuilder();
		
		if(id != null) adOrderEntry.setId(id);
		if(name != null) adOrderEntry.setName(name);
		if(comments != null) adOrderEntry.setComments(comments);
		if(customer != null) adOrderEntry.setCustomer(customer);
		if(startTime != null) adOrderEntry.setStartTime(startTime.getTime());
		if(endTime != null) adOrderEntry.setEndTime(endTime.getTime());

		return adOrderEntry.build().toByteString();
	}


	@Override
	public String toString() {
		return "AdOrder [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", comments=" + comments + ", customer=" + customer
				+ ", startTime=" + startTime + ", endTime=" + endTime
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

    
}
