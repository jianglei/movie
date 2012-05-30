/**
 * 
 */
package com.umeng.ufp.core.domain.constants;

import java.util.HashSet;
import java.util.Set;


/**
 * @author ke
 *
 */
public class AdContentConstants {
	
	public class ContentType {
		public final static String APP = "app";
		public final static String WEB = "web";
	}
	
	public class EntryType {
	    public final static String BANNER = "banner";
	    public final static String CUSTOM = "custom";
	    public final static String BIGIMAGE = "bigimage";
	    public final static String EMBED = "embed";
	    public final static String WAP = "wap";
	    public final static String TEXT = "text";
	}
	
	public class LandingType {
	    public final static String DETAIL = "detail";
	    public final static String DOWNLOAD = "download";
	    public final static String WEBVIEW = "webview";
	    public final static String BROWSER = "browser";
	    public final static String GOTOAPPSTORE = "gotoAppStore";
	}
    
	public class DisplayType {
	    public final static String STANDARD = "standard";
	    public final static String IMAGE = "image";
	    public final static String TEXT = "text";
	}
	

    
    public static Set<String> contentTypeSet = new HashSet<String>();
    static {
    	contentTypeSet.add(AdContentConstants.ContentType.WEB);
    	contentTypeSet.add(AdContentConstants.ContentType.APP);
    }
    
    public static Set<String> landingTypeSet = new HashSet<String>();
    static {
    	landingTypeSet.add(AdContentConstants.LandingType.BROWSER);
    	landingTypeSet.add(AdContentConstants.LandingType.DETAIL);
    	landingTypeSet.add(AdContentConstants.LandingType.DOWNLOAD);
    	landingTypeSet.add(AdContentConstants.LandingType.GOTOAPPSTORE);
    	landingTypeSet.add(AdContentConstants.LandingType.WEBVIEW);
    }
    
    public static Set<String> entryTypeSet = new HashSet<String>();
    static {
    	entryTypeSet.add(AdContentConstants.EntryType.BANNER);
    	entryTypeSet.add(AdContentConstants.EntryType.BIGIMAGE);
    	entryTypeSet.add(AdContentConstants.EntryType.CUSTOM);
    	entryTypeSet.add(AdContentConstants.EntryType.EMBED);
    	entryTypeSet.add(AdContentConstants.EntryType.TEXT);
    	entryTypeSet.add(AdContentConstants.EntryType.WAP);
    }
    
    public static Set<String> displayTypeSet = new HashSet<String>();
    static {
    	displayTypeSet.add(AdContentConstants.DisplayType.STANDARD);
    	displayTypeSet.add(AdContentConstants.DisplayType.IMAGE);
    	displayTypeSet.add(AdContentConstants.DisplayType.TEXT);
    }
}
