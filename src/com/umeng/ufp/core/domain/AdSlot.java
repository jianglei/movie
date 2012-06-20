package com.umeng.ufp.core.domain;

import com.google.protobuf.ByteString;
import com.umeng.ufp.core.domain.protobuf.AdSlotProtos.AdSlotEntry;

public class AdSlot {
	private Integer id;
    private Integer userId;
    
    private Integer appId;
    private String name;
    private String landingType;
    private String landingSize;
    private String landingImages;
    private String platform;
    
    private String device;
    private String channels;
    private String areas;
    private String textSize;
    private String displayStrategy;
    private String timeslots;
    private String template;
    private String animIn;
    private String enablePreload;
    private String enablePage;
    private Integer state;
    
    private String adNetworkStrategy;
    private Boolean xpEnable;
    private Integer xpPercent;
    private String appkey;
    private Boolean uadsEnable;
    private Integer uadsPercent;
    private String uadsKey;
	private String uadsKey2;
	
	private String opensize;
	private String pushStrategy;
	
    
    public ByteString serialize() {
		AdSlotEntry.Builder adSlotEntry = AdSlotEntry.newBuilder();
		
		if(id != null) adSlotEntry.setId(id);
		if(appId != null) adSlotEntry.setAppId(appId);
		if(name != null) adSlotEntry.setName(name);
		if(landingType != null) adSlotEntry.setLandingType(landingType);
		if(landingSize != null) adSlotEntry.setLandingSize(landingSize);
		if(landingImages != null) adSlotEntry.setLandingImages(landingImages);
		if(platform != null) adSlotEntry.setPlatform(platform);
		if(device != null) adSlotEntry.setDevice(device);
		if(channels != null) adSlotEntry.setChannels(channels);
		if(areas != null) adSlotEntry.setAreas(areas);
		if(textSize != null) adSlotEntry.setTextSize(textSize);
		if(displayStrategy != null) adSlotEntry.setDisplayStrategy(displayStrategy);
		if(timeslots != null) adSlotEntry.setTimeslots(timeslots);
		if(template != null) adSlotEntry.setTemplate(template);
		if(animIn != null) adSlotEntry.setAnimIn(animIn);
		if(enablePreload != null) adSlotEntry.setEnablePreload(enablePreload);
		if(enablePage != null) adSlotEntry.setEnablePage(enablePage);
		if(adNetworkStrategy != null) adSlotEntry.setAdNetworkStrategy(adNetworkStrategy);
		if(xpEnable != null) adSlotEntry.setXpEnable(xpEnable);
		if(xpPercent != null) adSlotEntry.setXpPercent(xpPercent);
		if(appkey != null) adSlotEntry.setAppkey(appkey);
		if(uadsEnable != null) adSlotEntry.setUadsEnable(uadsEnable);
		if(uadsPercent != null) adSlotEntry.setUadsPercent(uadsPercent);
		if(uadsKey != null) adSlotEntry.setUadsKey(uadsKey);
		if(uadsKey2 != null) adSlotEntry.setUadsKey2(uadsKey2);
		if(opensize != null) adSlotEntry.setOpensize(opensize);
		if(pushStrategy != null) adSlotEntry.setPushStrategy(pushStrategy);
		return adSlotEntry.build().toByteString();
	}
    
    
	@Override
	public String toString() {
		return "AdSlot [id=" + id + ", userId=" + userId + ", appId=" + appId
				+ ", name=" + name + ", landingType=" + landingType
				+ ", landingSize=" + landingSize + ", landingImages="
				+ landingImages + ", platform=" + platform + ", device="
				+ device + ", channels=" + channels + ", areas=" + areas
				+ ", textSize=" + textSize + ", displayStrategy="
				+ displayStrategy + ", timeslots=" + timeslots + ", template="
				+ template + ", animIn=" + animIn + ", enablePreload="
				+ enablePreload + ", enablePage=" + enablePage + ", state="
				+ state + ", adNetworkStrategy=" + adNetworkStrategy
				+ ", xpEnable=" + xpEnable + ", xpPercent=" + xpPercent
				+ ", appkey=" + appkey + ", uadsEnable=" + uadsEnable
				+ ", uadsPercent=" + uadsPercent + ", uadsKey=" + uadsKey
				+ ", uadsKey2=" + uadsKey2 + ", opensize=" + opensize 
				+ ", pushStrategy=" + pushStrategy + "]";
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
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLandingType() {
		return landingType;
	}
	public void setLandingType(String landingType) {
		this.landingType = landingType;
	}
	public String getLandingSize() {
		return landingSize;
	}
	public void setLandingSize(String landingSize) {
		this.landingSize = landingSize;
	}
	public String getLandingImages() {
		return landingImages;
	}
	public void setLandingImages(String landingImages) {
		this.landingImages = landingImages;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
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
	public String getTimeslots() {
		return timeslots;
	}
	public void setTimeslots(String timeslots) {
		this.timeslots = timeslots;
	}
	public String getEnablePreload() {
		return enablePreload;
	}
	public void setEnablePreload(String enablePreload) {
		this.enablePreload = enablePreload;
	}
	public String getEnablePage() {
		return enablePage;
	}
	public void setEnablePage(String enablePage) {
		this.enablePage = enablePage;
	}
	public Boolean getXpEnable() {
		return xpEnable;
	}
	public void setXpEnable(Boolean xpEnable) {
		this.xpEnable = xpEnable;
	}
	public Integer getXpPercent() {
		return xpPercent;
	}
	public void setXpPercent(Integer xpPercent) {
		this.xpPercent = xpPercent;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAdNetworkStrategy() {
		return adNetworkStrategy;
	}
	public void setAdNetworkStrategy(String adNetworkStrategy) {
		this.adNetworkStrategy = adNetworkStrategy;
	}
	public String getTextSize() {
		return textSize;
	}
	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}
	public String getDisplayStrategy() {
		return displayStrategy;
	}
	public void setDisplayStrategy(String displayStrategy) {
		this.displayStrategy = displayStrategy;
	}
	public Boolean getUadsEnable() {
		return uadsEnable;
	}
	public void setUadsEnable(Boolean uadsEnable) {
		this.uadsEnable = uadsEnable;
	}
	public Integer getUadsPercent() {
		return uadsPercent;
	}
	public void setUadsPercent(Integer uadsPercent) {
		this.uadsPercent = uadsPercent;
	}
	public String getUadsKey() {
		return uadsKey;
	}
	public void setUadsKey(String uadsKey) {
		this.uadsKey = uadsKey;
	}
	public String getUadsKey2() {
		return uadsKey2;
	}
	public void setUadsKey2(String uadsKey2) {
		this.uadsKey2 = uadsKey2;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getAnimIn() {
		return animIn;
	}
	public void setAnimIn(String animIn) {
		this.animIn = animIn;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}

	public String getOpensize() {
		return opensize;
	}
	public void setOpensize(String opensize) {
		this.opensize = opensize;
	}
	
	public String getPushStrategy() {
		return pushStrategy;
	}
	public void setPushStrategy(String pushStrategy) {
		this.pushStrategy = pushStrategy;
	}	
}
