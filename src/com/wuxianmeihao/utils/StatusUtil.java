package com.wuxianmeihao.utils;


public class StatusUtil {
	public static String getStatus(Integer state) {
		String status = null;
		if(state != null) {
			Integer value = (state == Constants.STATE_NORMAL ? Constants.STATE_NORMAL : (state ^ (state & (state - 1))));
			switch(value) {
			case Constants.STATE_NORMAL:
				status = Constants.Status.NORMAL;
				break;
			case Constants.STATE_DELETE:
				status = Constants.Status.DELETE;
				break;
			case Constants.STATE_PAUSE:
				status = Constants.Status.PAUSE;
				break;
			case Constants.STATE_LIMIT:
				status = Constants.Status.LIMIT;
				break;
			case Constants.STATE_FINISH:
				status = Constants.Status.FINISH;
				break;
			case Constants.STATE_READY:
				status = Constants.Status.READY;
				break;
			case Constants.STATE_ADSLOT_PAUSE:
				status = Constants.Status.ADSLOT_PAUSE;
				break;
			case Constants.STATE_ADORDER_PAUSE:
				status = Constants.Status.ADORDER_PAUSE;
				break;
			case Constants.STATE_APP_PAUSE:
				status = Constants.Status.APP_PAUSE;
				break;
			case Constants.STATE_ADORDER_FINISH:
				status = Constants.Status.ADORDER_FINISH;
				break;
			case Constants.STATE_ADORDER_READY:
				status = Constants.Status.ADORDER_READY;
				break;
			default:
				status = Constants.Status.KNWON;
				break;
			}
		}
		
		return status;
	}
	
	public static Integer getRevertState(String status) {
    	Integer state = null;
    	if(status.equals(Constants.Status.NORMAL)) {
    		state = ~Constants.STATE_PAUSE;
    	} else if(status.equals(Constants.Status.PAUSE)) {
    		state = ~Constants.STATE_NORMAL;
    	} else if(status.equals(Constants.Status.DELETE)) {
    		state = ~Constants.STATE_PAUSE;
    	} else if(status.equals(Constants.Status.LIMIT)) {
    		state = ~Constants.STATE_NORMAL;
    	} else if(status.equals(Constants.Status.FINISH)) {
    		state = ~Constants.STATE_READY;
    	} else if(status.equals(Constants.Status.READY)) {
    		state = ~Constants.STATE_FINISH;
    	} else if(status.equals(Constants.Status.ADSLOT_PAUSE)) {
    		state = ~Constants.STATE_NORMAL;
    	} else if(status.equals(Constants.Status.ADORDER_PAUSE)) {
    		state = ~Constants.STATE_NORMAL;
    	} else if(status.equals(Constants.Status.APP_PAUSE)) {
    		state = ~Constants.STATE_NORMAL;
    	} else if(status.equals(Constants.Status.ADORDER_FINISH)) {
    		state = ~Constants.STATE_ADORDER_READY;
    	} else if(status.equals(Constants.Status.ADORDER_READY)) {
    		state = ~Constants.STATE_ADORDER_FINISH;
    	}
    	return state;
    }
	
    public static Integer getState(String status) {
    	Integer state = null;
    	if(status.equals(Constants.Status.NORMAL)) {
    		state = Constants.STATE_NORMAL;
    	} else if(status.equals(Constants.Status.PAUSE)) {
    		state = Constants.STATE_PAUSE;
    	} else if(status.equals(Constants.Status.DELETE)) {
    		state = Constants.STATE_DELETE;
    	} else if(status.equals(Constants.Status.LIMIT)) {
    		state = Constants.STATE_LIMIT;
    	} else if(status.equals(Constants.Status.FINISH)) {
    		state = Constants.STATE_FINISH;
    	} else if(status.equals(Constants.Status.READY)) {
    		state = Constants.STATE_READY;
    	} else if(status.equals(Constants.Status.ADSLOT_PAUSE)) {
    		state = Constants.STATE_ADSLOT_PAUSE;
    	} else if(status.equals(Constants.Status.ADORDER_PAUSE)) {
    		state = Constants.STATE_ADORDER_PAUSE;
    	} else if(status.equals(Constants.Status.APP_PAUSE)) {
    		state = Constants.STATE_APP_PAUSE;
    	} else if(status.equals(Constants.Status.ADORDER_FINISH)) {
    		state = Constants.STATE_ADORDER_FINISH;
    	} else if(status.equals(Constants.Status.ADORDER_READY)) {
    		state = Constants.STATE_ADORDER_READY;
    	}
    	return state;
    }
    
    public static boolean containsStatus(Integer state, String status) {
    	Integer containState = getState(status);
    	return containState == null ? false : (containState & state) > 0;
    }
    
    public static Integer upgradeState(String type, Integer state) {
    	Integer status = Constants.STATE_NORMAL;
    	if(type.equals("app")) {
    		status |= containsStatus(state, Constants.Status.PAUSE) ? Constants.STATE_APP_PAUSE : Constants.STATE_NORMAL;
    	} else if(type.equals("adslot")) {
    		status |= containsStatus(state, Constants.Status.PAUSE) ? Constants.STATE_ADSLOT_PAUSE : Constants.STATE_NORMAL;
    	} else if(type.equals("adorder")) {
    		status |= containsStatus(state, Constants.Status.PAUSE) ? Constants.STATE_ADORDER_PAUSE : Constants.STATE_NORMAL;
    		status |= containsStatus(state, Constants.Status.READY) ? Constants.STATE_ADORDER_READY : Constants.STATE_NORMAL;
    		status |= containsStatus(state, Constants.Status.FINISH) ? Constants.STATE_ADORDER_FINISH : Constants.STATE_NORMAL;
    	}
    	return status;
    }
}
