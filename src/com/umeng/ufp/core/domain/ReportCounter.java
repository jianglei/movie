package com.umeng.ufp.core.domain;

import java.util.Date;

public class ReportCounter {
    private String date = null;
    private Integer adId = null;
    private String adSlotId = null;
    private String category = null;
    private Integer impression = 0;
    private Integer download = 0;
    private Integer click = 0;
    private Integer avgPriceClick = 0;
    private Integer appstoreClick = 0;
    private double revenue = 0.0;
    private Date updatedTime = null;
    private double avgPrice = 0.0;
    private double clickRate = 0.0;
    private Integer promoterImpression = 0;
    private Integer promoterClick = 0;
    private Integer promoterAppstoreClick = 0;
    private Integer promoterDownload = 0;
    private double promoterClickRate = 0.0;
    
    public ReportCounter plus(ReportCounter counter) {
    	this.impression = this.impression + counter.impression;
    	this.download = this.download + counter.download;
    	this.click = this.click + counter.click;
    	this.appstoreClick = this.appstoreClick + counter.appstoreClick;
    	this.avgPriceClick = this.avgPriceClick + counter.avgPriceClick;
    	this.revenue = this.revenue + counter.revenue;
    	this.avgPrice = this.avgPriceClick == 0 ?  0.0 : this.revenue / this.avgPriceClick;
    	this.clickRate = this.impression == 0 ? 0.0 : (double)this.click / this.impression;
    	this.promoterImpression = this.promoterImpression + counter.promoterImpression;
    	this.promoterClick = this.promoterClick + counter.promoterClick;
    	this.promoterAppstoreClick = this.promoterAppstoreClick + counter.promoterAppstoreClick;
    	this.promoterDownload = this.promoterDownload + counter.promoterDownload;
    	this.promoterClickRate = this.promoterImpression == 0 ? 0.0 : (double)this.promoterClick / this.promoterImpression;
    	return this;
    }
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getAdSlotId() {
		return adSlotId;
	}
	public void setAdSlotId(String adSlotId) {
		this.adSlotId = adSlotId;
	}
	public Integer getImpression() {
		return impression;
	}
	public void setImpression(Integer impression) {
		this.impression = impression;
	}
	public Integer getDownload() {
		return download;
	}
	public void setDownload(Integer download) {
		this.download = download;
	}

	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public double getClickRate() {
		return clickRate;
	}
	public void setClickRate(double clickRate) {
		this.clickRate = clickRate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	public Integer getAppstoreClick() {
		return appstoreClick;
	}
	public void setAppstoreClick(Integer appstoreClick) {
		this.appstoreClick = appstoreClick;
	}
	public Integer getPromoterClick() {
		return promoterClick;
	}
	public void setPromoterClick(Integer promoterClick) {
		this.promoterClick = promoterClick;
	}
	public Integer getPromoterAppstoreClick() {
		return promoterAppstoreClick;
	}
	public void setPromoterAppstoreClick(Integer promoterAppstoreClick) {
		this.promoterAppstoreClick = promoterAppstoreClick;
	}
	public Integer getPromoterDownload() {
		return promoterDownload;
	}
	public void setPromoterDownload(Integer promoterDownload) {
		this.promoterDownload = promoterDownload;
	}
	public Integer getPromoterImpression() {
		return promoterImpression;
	}
	public void setPromoterImpression(Integer promoterImpression) {
		this.promoterImpression = promoterImpression;
	}
	public double getPromoterClickRate() {
		return promoterClickRate;
	}
	public void setPromoterClickRate(double promoterClickRate) {
		this.promoterClickRate = promoterClickRate;
	}
   
}
  