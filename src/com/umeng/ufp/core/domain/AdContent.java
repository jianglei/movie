package com.umeng.ufp.core.domain;

import com.google.protobuf.ByteString;
import com.umeng.ufp.core.domain.protobuf.AdContentProtos.AdContentEntry;


public class AdContent {
	private Integer id;
	private Integer userId;
    private String contentType;
    private String landingType;
    private String displayType;
    private String landingSize;
    private String url;
    private String icon;
    private String img;
    private String title;
    private String textFont;
    private String textSize;
    private String textColor;
    private String adWords;
    private String provider;
    private String description;
    
    private String price;
    private String platform;
    private Integer size;
    private String appPackageName;
    private String appVersionCode;
    private String appVersionName;
    private String appStoreId;
    private String bundleIdentifier;
    
    public ByteString serialize() {
		AdContentEntry.Builder adContentEntry = AdContentEntry.newBuilder();
		
		if(id != null) adContentEntry.setId(id);
		if(contentType != null) adContentEntry.setContentType(contentType);
		if(landingType != null) adContentEntry.setLandingType(landingType);
		if(displayType != null) adContentEntry.setDisplayType(displayType);
		if(landingSize != null) adContentEntry.setLandingSize(landingSize);
		if(url != null) adContentEntry.setUrl(url);
		if(icon != null) adContentEntry.setIcon(icon);
		if(img != null) adContentEntry.setImg(img);
		if(title != null) adContentEntry.setTitle(title);
		if(textFont != null) adContentEntry.setTextFont(textFont);
		if(textSize != null) adContentEntry.setTextSize(textSize);
		if(textColor != null) adContentEntry.setTextColor(textColor);
		if(adWords != null) adContentEntry.setAdWords(adWords);
		if(provider != null) adContentEntry.setProvider(provider);
		if(description != null) adContentEntry.setDescription(description);
		if(price != null) adContentEntry.setPrice(price);
		if(platform != null) adContentEntry.setPlatform(platform);
		if(size != null) adContentEntry.setSize(size);
		if(appPackageName != null) adContentEntry.setAppPackageName(appPackageName);
		if(appVersionCode != null) adContentEntry.setAppVersionCode(appVersionCode);
		if(appVersionName != null) adContentEntry.setAppVersionName(appVersionName);
		if(appStoreId != null) adContentEntry.setAppStoreId(appStoreId);
		if(bundleIdentifier != null) adContentEntry.setBundleIdentifier(bundleIdentifier);

		return adContentEntry.build().toByteString();
	}
    
	@Override
	public String toString() {
		return "AdContent [id=" + id + ", userId=" + userId + ", contentType="
				+ contentType + ", landingType=" + landingType
				+ ", displayType=" + displayType + ", landingSize="
				+ landingSize + ", url=" + url + ", icon=" + icon + ", img="
				+ img + ", title=" + title + ", textFont=" + textFont
				+ ", textSize=" + textSize + ", textColor=" + textColor
				+ ", adWords=" + adWords + ", provider=" + provider
				+ ", description=" + description + ", price=" + price
				+ ", platform=" + platform + ", size=" + size
				+ ", appPackageName=" + appPackageName + ", appVersionCode="
				+ appVersionCode + ", appVersionName=" + appVersionName
				+ ", appStoreId=" + appStoreId + ", bundleIdentifier="
				+ bundleIdentifier + "]";
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
	public String getLandingType() {
		return landingType;
	}
	public void setLandingType(String landingType) {
		this.landingType = landingType;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdWords() {
		return adWords;
	}
	public void setAdWords(String adWords) {
		this.adWords = adWords;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getAppPackageName() {
		return appPackageName;
	}
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}
	public String getAppVersionCode() {
		return appVersionCode;
	}
	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}
	public String getAppVersionName() {
		return appVersionName;
	}
	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}
	public String getAppStoreId() {
		return appStoreId;
	}
	public void setAppStoreId(String appStoreId) {
		this.appStoreId = appStoreId;
	}
	public String getBundleIdentifier() {
		return bundleIdentifier;
	}
	public void setBundleIdentifier(String bundleIdentifier) {
		this.bundleIdentifier = bundleIdentifier;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTextFont() {
		return textFont;
	}
	public void setTextFont(String textFont) {
		this.textFont = textFont;
	}
	public String getTextSize() {
		return textSize;
	}
	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getLandingSize() {
		return landingSize;
	}
	public void setLandingSize(String landingSize) {
		this.landingSize = landingSize;
	}
 
}