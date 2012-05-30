package com.umeng.ufp.core.domain;

import java.util.Date;

import com.google.protobuf.ByteString;
import com.umeng.core.utils.DateUtil;
import com.umeng.ufp.core.domain.protobuf.UserOpLogProtos.UserOpLogEntry;
/*
 * UserOpLog class decribe the user's operation history log 
 * which include trigger and cascade operation
 */
public class UserOpLog {
    private Integer id;//log id  
    private Date opDate; //op datetime  
    private String opType; //add, edit, change
    private String objectType; //app, adSlot, ad, adContent, adOrder, user
    private String objectKey; //appId, adSlotId, adId, adOrderId, userId, adHolder(adSlotIdList:adIdList)
    private String objectValue; //new object, which is a serialized string  
    private Integer userId; //a account to a user
    private Date createdTime; //log create time by system
    
    public ByteString serialize() {
    	UserOpLogEntry.Builder userOpLogEntry = UserOpLogEntry.newBuilder();
    	userOpLogEntry.setOpDate(DateUtil.getDateString(opDate.getTime()));
    	userOpLogEntry.setOpType(opType);
    	userOpLogEntry.setObjectType(objectType);
    	userOpLogEntry.setObjectKey(objectKey);
    	userOpLogEntry.setObjectValue(objectValue);
    	userOpLogEntry.setUserId(userId);
    	  	
    	return userOpLogEntry.build().toByteString();
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
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getObjectValue() {
		return objectValue;
	}
	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
	}
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}