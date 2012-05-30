package com.umeng.ufp.core.domain;

import com.google.protobuf.ByteString;
import com.umeng.ufp.core.domain.protobuf.UserActionLogProtos.UserActionLogEntry;

/*
 * UserActionLog class decribe the user's operation history log 
 * which not include trigger and cascade operation
 */

public class UserActionLog {
    private Integer id;//log id  
    private String opDate; //op datetime  
    private String opType; //add, edit, change
    private String objectType; //app, adSlot, ad, adContent, adOrder, user
    private Integer objectId; //appId, adSlotId, adId, adOrderId, userId
    private ByteString objectOldValue; //object serialized by protobuf when create log
    private ByteString objectNewValue; //object serialized by protobuf when create log
    private String fieldName; //update field name
    private String fieldOldValue;//update field old value
	private String fieldNewValue;//update field new value
    private Integer userId; //a account to a user
    private Integer operatorId; //a user include many operators
    
    public ByteString serialize() {
    	UserActionLogEntry.Builder userActionLogEntry = UserActionLogEntry.newBuilder();
    	userActionLogEntry.setOpDate(opDate);
    	userActionLogEntry.setOpType(opType);
    	userActionLogEntry.setObjectType(objectType);
    	userActionLogEntry.setObjectId(objectId);
    	userActionLogEntry.setObjectOldvalue(objectOldValue);
    	userActionLogEntry.setObjectNewvalue(objectNewValue);
    	userActionLogEntry.setUserId(userId);
    	userActionLogEntry.setOperatorId(operatorId);	
    	
    	return userActionLogEntry.build().toByteString();
    	
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpDate() {
		return opDate;
	}

	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}

	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public ByteString getObjectOldValue() {
		return objectOldValue;
	}

	public void setObjectOldValue(ByteString objectOldValue) {
		this.objectOldValue = objectOldValue;
	}

	public ByteString getObjectNewValue() {
		return objectNewValue;
	}

	public void setObjectNewValue(ByteString objectNewValue) {
		this.objectNewValue = objectNewValue;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldOldValue() {
		return fieldOldValue;
	}

	public void setFieldOldValue(String fieldOldValue) {
		this.fieldOldValue = fieldOldValue;
	}

	public String getFieldNewValue() {
		return fieldNewValue;
	}

	public void setFieldNewValue(String fieldNewValue) {
		this.fieldNewValue = fieldNewValue;
	}
}