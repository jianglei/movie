/**
 * 
 */
package com.wuxianmeihao.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ke
 * 
 */
public class Constants {
	public final static String PATH_UPLOAD = "public/uploads";
	// public final static String APK_ANALYSIS_CMD = "aapt dump badging ";
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
	 * state[bitmap] []: self state <>: self system state (): trigger state {}:
	 * future state [0] delete [1] pause {2} <3> limit (4) adslot_pause (5)
	 * adorder_pause (6) app_pause (7) adorder_finish <8> finish (9)
	 * adorder_ready <10> ready
	 */

	public final static int STATE_DELETE_BITINDEX = 0; // DELETE: 1
	public final static int STATE_PAUSE_BITINDEX = 1; // PAUSE: 2
	public final static int STATE_LIMIT_BITINDEX = 3; // LIMIT: 8
	public final static int STATE_ADSLOT_PAUSE_BITINDEX = 4; // ADSLOT_PAUSE: 16
	public final static int STATE_ADORDER_PAUSE_BITINDEX = 5; // ADORDER_PAUSE:
	// 32
	public final static int STATE_APP_PAUSE_BITINDEX = 6; // APP_PAUSE: 64
	public final static int STATE_ADORDER_FINISH_BITINDEX = 7; // ADORDER_FINISH:
	// 128
	public final static int STATE_FINISH_BITINDEX = 8; // FINISH_PAUSE: 256
	public final static int STATE_ADORDER_READY_BITINDEX = 9; // ADORDER_READY:
	// 512
	public final static int STATE_READY_BITINDEX = 10; // READY: 1024

	public final static int STATE_NORMAL = 0; // DELETE: 0
	public final static int STATE_DELETE = 1 << STATE_DELETE_BITINDEX; // DELETE:
	// 1
	public final static int STATE_PAUSE = 1 << STATE_PAUSE_BITINDEX; // PAUSE: 2
	public final static int STATE_LIMIT = 1 << STATE_LIMIT_BITINDEX; // LIMIT: 8
	public final static int STATE_ADSLOT_PAUSE = 1 << STATE_ADSLOT_PAUSE_BITINDEX; // ADSLOT_PAUSE:
	// 16
	public final static int STATE_ADORDER_PAUSE = 1 << STATE_ADORDER_PAUSE_BITINDEX; // ADORDER_PAUSE:
	// 32
	public final static int STATE_APP_PAUSE = 1 << STATE_APP_PAUSE_BITINDEX; // APP_PAUSE:
	// 64
	public final static int STATE_ADORDER_FINISH = 1 << STATE_ADORDER_FINISH_BITINDEX; // ADORDER_FINISH:
	// 128
	public final static int STATE_FINISH = 1 << STATE_FINISH_BITINDEX; // FINISH:
	// 256
	public final static int STATE_ADORDER_READY = 1 << STATE_ADORDER_READY_BITINDEX; // ADORDER_READY:
	// 512
	public final static int STATE_READY = 1 << STATE_READY_BITINDEX; // READY:

	// 1024

	public class Status {
		// self state
		public final static String NORMAL = "normal";
		public final static String PAUSE = "pause";
		public final static String DELETE = "delete";
		// self system state
		public final static String LIMIT = "limit";
		public final static String FINISH = "finish";
		public final static String READY = "ready";
		// trigger state
		public final static String ADSLOT_PAUSE = "adslot_pause";
		public final static String ADORDER_PAUSE = "adorder_pause";
		public final static String APP_PAUSE = "app_pause";
		public final static String ADORDER_FINISH = "adorder_finish";
		public final static String ADORDER_READY = "adorder_ready";
		// unknown state
		public final static String KNWON = "knwon";
	}

	public static Set<String> paltformSet = new HashSet<String>();
	static {
		paltformSet.add(Constants.Platform.IOS);
		paltformSet.add(Constants.Platform.ANDROID);
	}

	public final static int CATEGORY_ZYJM = 0; // 综艺节目
	public final static int CATEGORY_XGDSJ = 1; // 香港电视剧
	public final static int CATEGORY_OMDSJ = 2; // 欧美电视剧
	public final static int CATEGORY_GCDSJ = 3; // 国产电视剧
	public final static int CATEGORY_HGDSJ = 4; // 韩国电视剧
	public final static int CATEGORY_RBDSJ = 5; // 日本电视剧
	public final static int CATEGORY_HWDSJ = 6; // 海外电视剧
	public final static int CATEGORY_KTDM = 7; // 卡通动漫
	public final static int CATEGORY_KBDY = 8; // 恐怖片
	public final static int CATEGORY_JQDY = 9; // 剧情片
	public final static int CATEGORY_DZDY = 10; // 动作片
	public final static int CATEGORY_XJDY = 11; // 喜剧片
	public final static int CATEGORY_JLDY = 12; // 纪录片
	public final static int CATEGORY_AQDY = 13; // 爱情片
	public final static int CATEGORY_KHDY = 14; // 科幻片
	public final static int CATEGORY_ZZDY = 15; // 战争片

	public class Category {

		public final static String ZYJM = "综艺节目";
		public final static String XGDSJ = "香港电视剧";
		public final static String OMDSJ = "欧美电视剧";
		public final static String GCDSJ = "国产电视剧";
		public final static String HGDSJ = "韩国电视剧";
		public final static String RBDSJ = "日本电视剧";
		public final static String HWDSJ = "海外电视剧";
		public final static String KTDM = "卡通动漫";
		public final static String KBDY = "恐怖片";
		public final static String JQDY = "剧情片";
		public final static String DZDY = "动作片";
		public final static String XJDY = "喜剧片";
		public final static String JLDY = "纪录片";
		public final static String AQDY = "爱情片";
		public final static String KHDY = "科幻片";
		public final static String ZZDY = "战争片";
	}

	public final static Set<String> ZYSet = new HashSet<String>();
	static {
		ZYSet.add(Constants.Category.ZYJM);
	}
	public final static Set<String> DSJSet = new HashSet<String>();
	static {
		DSJSet.add(Constants.Category.XGDSJ);
		DSJSet.add(Constants.Category.OMDSJ);
		DSJSet.add(Constants.Category.GCDSJ);
		DSJSet.add(Constants.Category.HGDSJ);
		DSJSet.add(Constants.Category.RBDSJ);
		DSJSet.add(Constants.Category.HWDSJ);
	}
	public final static Set<String> DMSet = new HashSet<String>();
	static {
		DMSet.add(Constants.Category.KTDM);
	}
	public final static Set<String> DYSet = new HashSet<String>();
	static {
		DYSet.add(Constants.Category.KBDY);
		DYSet.add(Constants.Category.JQDY);
		DYSet.add(Constants.Category.DZDY);
		DYSet.add(Constants.Category.XJDY);
		DYSet.add(Constants.Category.JLDY);
		DYSet.add(Constants.Category.AQDY);
		DYSet.add(Constants.Category.KHDY);
		DYSet.add(Constants.Category.ZZDY);
	}

	public class Category_type {
		public final static String ZY = "zongyi";
		public final static String DSJ = "dianshiju";
		public final static String DM = "dongman";
		public final static String DY = "dianying";
	}

	public final static Map<String, Set<String>> CategoryTypeMap = new HashMap<String, Set<String>>();
	static {
		CategoryTypeMap.put(Category_type.ZY, ZYSet);
		CategoryTypeMap.put(Category_type.DSJ, DSJSet);
		CategoryTypeMap.put(Category_type.DM, DMSet);
		CategoryTypeMap.put(Category_type.DY, DYSet);
	}

}
