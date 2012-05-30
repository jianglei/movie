/**
 * 
 */
package com.umeng.ufp.publisher.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.umeng.ufp.core.domain.constants.AdContentConstants;


/**
 * @author ke
 *
 */
public class Constants {
    public final static String PATH_UPLOAD = "public/uploads";
//    public final static String APK_ANALYSIS_CMD = "aapt dump badging ";
    public final static String APK_ANALYSIS_CMD = "tools/aapt dump badging ";
    
    public final static String PIC_URL = "/public/uploads/";
    
    public final static String VERSION_NAME_REGEX = "versionName='(\\S+?)'";
    
    public final static String VERSION_CODE_REGEX = "versionCode='(\\S+?)'";
    
    public final static String PACKAGE_NAME_REGEX = "name='(\\S+?)'";
  
	public final static String CATEGORY_XP = "0";
	public final static String CATEGORY_UFP = "1";
	public final static String CATEGORY_XP_ADS = "2";

	public final static long IMAGE_SIZE_LIMIT = 1024 * 100;
	
	public class Platform {
		public final static String IOS = "iOS";
		public final static String ANDROID = "android";
	}
	
	/*
	 * state[bitmap]
	 * []: self state
	 * <>: self system state
	 * (): trigger state 
	 * {}: future state
	 *   [0]   delete 
	 *   [1]   pause  
	 *   {2}   
	 *   <3>   limit
	 *   (4)   adslot_pause 
	 *   (5)   adorder_pause
	 *   (6)   app_pause
	 *   (7)   adorder_finish
	 *   <8>   finish
	 *   (9)  adorder_ready
	 *   <10>  ready
	 * 
	 * */

    public final static int STATE_DELETE_BITINDEX = 0; //DELETE: 1
    public final static int STATE_PAUSE_BITINDEX = 1; //PAUSE: 2
    public final static int STATE_LIMIT_BITINDEX = 3; //LIMIT: 8
    public final static int STATE_ADSLOT_PAUSE_BITINDEX = 4; //ADSLOT_PAUSE: 16
    public final static int STATE_ADORDER_PAUSE_BITINDEX = 5; //ADORDER_PAUSE: 32
    public final static int STATE_APP_PAUSE_BITINDEX = 6; //APP_PAUSE: 64
    public final static int STATE_ADORDER_FINISH_BITINDEX = 7; //ADORDER_FINISH: 128
    public final static int STATE_FINISH_BITINDEX = 8; //FINISH_PAUSE: 256
    public final static int STATE_ADORDER_READY_BITINDEX = 9; //ADORDER_READY: 512
    public final static int STATE_READY_BITINDEX = 10; //READY: 1024

    public final static int STATE_NORMAL = 0; //DELETE: 0
    public final static int STATE_DELETE = 1 << STATE_DELETE_BITINDEX; //DELETE: 1
    public final static int STATE_PAUSE = 1 << STATE_PAUSE_BITINDEX; //PAUSE: 2
    public final static int STATE_LIMIT= 1 << STATE_LIMIT_BITINDEX; //LIMIT: 8
    public final static int STATE_ADSLOT_PAUSE = 1 << STATE_ADSLOT_PAUSE_BITINDEX; //ADSLOT_PAUSE: 16
    public final static int STATE_ADORDER_PAUSE = 1 << STATE_ADORDER_PAUSE_BITINDEX; //ADORDER_PAUSE: 32
    public final static int STATE_APP_PAUSE = 1 << STATE_APP_PAUSE_BITINDEX; //APP_PAUSE: 64
    public final static int STATE_ADORDER_FINISH = 1 << STATE_ADORDER_FINISH_BITINDEX; //ADORDER_FINISH: 128
    public final static int STATE_FINISH= 1 << STATE_FINISH_BITINDEX; //FINISH: 256
    public final static int STATE_ADORDER_READY = 1 << STATE_ADORDER_READY_BITINDEX; //ADORDER_READY: 512
    public final static int STATE_READY = 1 << STATE_READY_BITINDEX; //READY: 1024
    
	public class Status {
		//self state
		public final static String NORMAL = "normal"; 
	    public final static String PAUSE = "pause"; 
	    public final static String DELETE = "delete"; 
		//self system state
		public final static String LIMIT = "limit"; 
		public final static String FINISH = "finish"; 
		public final static String READY = "ready";
		//trigger state
		public final static String ADSLOT_PAUSE = "adslot_pause";
		public final static String ADORDER_PAUSE = "adorder_pause";
		public final static String APP_PAUSE = "app_pause";
		public final static String ADORDER_FINISH = "adorder_finish"; 
		public final static String ADORDER_READY = "adorder_ready";
		//unknown state
		public final static String KNWON = "knwon";
	}
	
	public static Set<String> paltformSet = new HashSet<String>();
    static {
    	paltformSet.add(Constants.Platform.IOS);
    	paltformSet.add(Constants.Platform.ANDROID);
    }
    
}
