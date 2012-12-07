package com.wuxianmeihao.utils;

import java.io.*;

public class FileConfig implements Serializable {
    private static final long serialVersionUID = 7269408849215776748L;

    private String filePath;

    private String tempFilePath;

    private String symbol;

    private String picUrl; 

    private String adpmPicUrl; 

    private String promotionUrl;

    private int picWidth;

    private int picHeight;

    private int campaignDailyBudget;

    private String batchUploadPath;

    private String thriftHost;

    private int thriftTimeOut;
    
    private String ipDBPath;
    
    private String apkParseCommand;

    /**
     * @return the ipDBPath
     */
    public String getIpDBPath() {
        return ipDBPath;
    }

    /**
     * @param ipDBPath the ipDBPath to set
     */
    public void setIpDBPath(String ipDBPath) {
        this.ipDBPath = ipDBPath;
    }

    public String getAdpmPicUrl() {
        return adpmPicUrl;
    }

    public String getBatchUploadPath() {
        return batchUploadPath;
    }

    public int getCampaignDailyBudget() {
        return campaignDailyBudget;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getPicHeight() {
        return picHeight;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public int getPicWidth() {
        return picWidth;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTempFilePath() {
        return tempFilePath;
    }

    public String getThriftHost() {
        return thriftHost;
    }

    public int getThriftTimeOut() {
        return thriftTimeOut;
    }

    public void setAdpmPicUrl(String adpmPicUrl) {
        this.adpmPicUrl = adpmPicUrl;
    }

    public void setBatchUploadPath(String batchUploadPath) {
        this.batchUploadPath = batchUploadPath;
    }

    public void setCampaignDailyBudget(int campaignDailyBudget) {
        this.campaignDailyBudget = campaignDailyBudget;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }

    public void setThriftHost(String thriftHost) {
        this.thriftHost = thriftHost;
    }

    public void setThriftTimeOut(int thriftTimeOut) {
        this.thriftTimeOut = thriftTimeOut;
    }

    /**
     * @return the apkParseCommand
     */
    public String getApkParseCommand() {
        return apkParseCommand;
    }

    /**
     * @param apkParseCommand the apkParseCommand to set
     */
    public void setApkParseCommand(String apkParseCommand) {
        this.apkParseCommand = apkParseCommand;
    }

}
