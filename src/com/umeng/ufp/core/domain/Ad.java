package com.umeng.ufp.core.domain;

import java.util.Date;

import com.google.protobuf.ByteString;
import com.umeng.ufp.core.domain.protobuf.AdProtos.AdEntry;

public class Ad {
    private Integer id;
    private Integer userId;
    
    private Integer adOrderId;
    private String name;
    private String platform;
    
    private Integer prior;
    private String budget;
    private Double budgetLimit;
    private String priceType;
    private Double costPrice;
    
    private Date startTime;
    private Date endTime;
    
    private String channels;
    private String areas;
    private String networks;
    
    private Integer state;
    private Integer fixedRank;

    public ByteString serialize() {
		AdEntry.Builder adEntry = AdEntry.newBuilder();
		
        if(id != null) adEntry.setId(id);
        if(adOrderId != null) adEntry.setAdOrderId(adOrderId);
        if(name != null) adEntry.setName(name);
        if(platform != null) adEntry.setPlatform(platform);
        if(prior != null) adEntry.setPrior(prior);
        if(budget != null) adEntry.setBudget(budget);
        if(budgetLimit != null) adEntry.setBudgetLimit(budgetLimit);
        if(priceType != null) adEntry.setPriceType(priceType);
        if(costPrice != null) adEntry.setCostPrice(costPrice);
        if(startTime != null) adEntry.setStartTime(startTime.getTime());
        if(endTime != null) adEntry.setEndTime(endTime.getTime());
        if(channels != null) adEntry.setChannels(channels);
        if(areas != null) adEntry.setAreas(areas);
        if(networks != null) adEntry.setNetworks(networks);
        if(fixedRank != null) adEntry.setFixedRank(fixedRank);
        
		return adEntry.build().toByteString();
	}
    

	@Override
	public String toString() {
		return "Ad [id=" + id + ", userId=" + userId + ", adOrderId="
				+ adOrderId + ", name=" + name + ", platform=" + platform
				+ ", prior=" + prior + ", budget=" + budget + ", budgetLimit="
				+ budgetLimit + ", priceType=" + priceType + ", costPrice="
				+ costPrice + ", startTime=" + startTime + ", endTime="
				+ endTime + ", channels=" + channels + ", areas=" + areas
				+ ", networks=" + networks + ", fixedRank=" + fixedRank + ", state=" + state + "]";
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
	public Integer getAdOrderId() {
		return adOrderId;
	}
	public void setAdOrderId(Integer adOrderId) {
		this.adOrderId = adOrderId;
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
	public Integer getPrior() {
		return prior;
	}
	public void setPrior(Integer prior) {
		this.prior = prior;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public Double getBudgetLimit() {
		return budgetLimit;
	}
	public void setBudgetLimit(Double budgetLimit) {
		this.budgetLimit = budgetLimit;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
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
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getNetworks() {
		return networks;
	}
	public void setNetworks(String networks) {
		this.networks = networks;
	}

	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getFixedRank() {
		return fixedRank;
	}
	
	public void setFixedRank(Integer fixedRank){
		this.fixedRank = fixedRank;
	}
}