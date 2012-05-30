package com.umeng.ufp.core.domain;

import java.util.Date;

public class AdHolder {
    private Integer adId;
    private Integer adSlotId;
    private Integer prior;
    private Double ctr;
    private Double weight;
    private Integer adState;
    private Integer adSlotState;
    private Integer state;
    private Integer userId;
    
    @Override
	public String toString() {
		return "adId=" + adId + ", adSlotId=" + adSlotId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String serialize() {
    	return "";
    }
    
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getAdSlotId() {
		return adSlotId;
	}
	public void setAdSlotId(Integer adSlotId) {
		this.adSlotId = adSlotId;
	}
	public Integer getPrior() {
		return prior;
	}
	public void setPrior(Integer prior) {
		this.prior = prior;
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getCtr() {
		return ctr;
	}
	public void setCtr(Double ctr) {
		this.ctr = ctr;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getAdSlotState() {
		return adSlotState;
	}

	public void setAdSlotState(Integer adSlotState) {
		this.adSlotState = adSlotState;
	}

	public Integer getAdState() {
		return adState;
	}

	public void setAdState(Integer adState) {
		this.adState = adState;
	}

}