/**
 * 
 */
package com.wuxianmeihao.core.domain.constants;

import java.util.HashSet;
import java.util.Set;


/**
 * @author ke
 *
 */
public class AdSlotConstants {
	
	public class LandingType {
	    public final static String BANNER = "banner";
	    public final static String CUSTOM = "custom";
	    public final static String BIGIMAGE = "bigimage";
	    public final static String EMBED = "embed";
	    public final static String WAP = "wap";
	    public final static String TEXT = "text";
	    public final static String PUSH = "push";
	}
    
	public class DisplayStrategy {
	    public final static String PRIOR = "prior";
	    public final static String ROTATE = "rotate";
	}
	
	public class EnablePreload {
	    public final static String YES = "yes";
	    public final static String NO = "no";
	}
	
	public class EnablePage {
	    public final static String YES = "yes";
	    public final static String NO = "no";
	}
	
	public class EnableNew {
	    public final static String YES = "yes";
	    public final static String NO = "no";
	}
	
	public class AdNetworkStrategy {
	    public final static String NONE = "none";
	    public final static String FILL = "fill";
	    public final static String PERCENT = "percent";
	}
	
	public class Template {
	    public final static String APPLIST = "applist";
	    public final static String ICONLIST = "iconlist";
	    public final static String HORIZON_BIGIMAGE = "horizon_bigimage";
	    public final static String VERTICAL_BIGIMAGE = "vertical_bigimage";
	}
	
	public class PushStrategy {
		public final static String NONE = "none";
		public final static String ONCE_PER_DAY = "onceperday";
		public final static String WHEN_AD_CHANGE = "whenadchange";
		public final static String NO_LIMIT = "nolimit"; 
	}
    
	public class Opensize {
		public final static String NONE = "none";
		public final static String HUNDRED = "100";
		public final static String EIGHTY = "80";
		public final static String SIXTY = "60";
		public final static String MESSAGEBOX = "4";
	}
	
    public static Set<String> landingTypeSet = new HashSet<String>();
    static {
    	landingTypeSet.add(AdSlotConstants.LandingType.BANNER);
    	landingTypeSet.add(AdSlotConstants.LandingType.BIGIMAGE);
    	landingTypeSet.add(AdSlotConstants.LandingType.CUSTOM);
    	landingTypeSet.add(AdSlotConstants.LandingType.EMBED);
    	landingTypeSet.add(AdSlotConstants.LandingType.TEXT);
    	landingTypeSet.add(AdSlotConstants.LandingType.WAP);
    	landingTypeSet.add(AdSlotConstants.LandingType.PUSH);
    }
    
    public static Set<String> displayStrategySet = new HashSet<String>();
    static {
    	displayStrategySet.add(AdSlotConstants.DisplayStrategy.PRIOR);
    	displayStrategySet.add(AdSlotConstants.DisplayStrategy.ROTATE);
    }
    
    public static Set<String> templateSet = new HashSet<String>();
    static {
    	templateSet.add(AdSlotConstants.Template.APPLIST);
    	templateSet.add(AdSlotConstants.Template.ICONLIST);
    	templateSet.add(AdSlotConstants.Template.HORIZON_BIGIMAGE);
    	templateSet.add(AdSlotConstants.Template.VERTICAL_BIGIMAGE);
    }
    
    public static Set<String> enablePreloadSet = new HashSet<String>();
    static {
    	enablePreloadSet.add(AdSlotConstants.EnablePreload.YES);
    	enablePreloadSet.add(AdSlotConstants.EnablePreload.NO);
    }
    
    public static Set<String> enableNewSet = new HashSet<String>();
    static {
    	enableNewSet.add(AdSlotConstants.EnableNew.YES);
    	enableNewSet.add(AdSlotConstants.EnableNew.NO);
    }
    
    public static Set<String> enablePageSet = new HashSet<String>();
    static {
    	enablePageSet.add(AdSlotConstants.EnablePage.YES);
    	enablePageSet.add(AdSlotConstants.EnablePage.NO);
    }
    
    public static Set<String> adNetworkStrategySet = new HashSet<String>();
    static {
    	adNetworkStrategySet.add(AdSlotConstants.AdNetworkStrategy.NONE);
    	adNetworkStrategySet.add(AdSlotConstants.AdNetworkStrategy.FILL);
    	adNetworkStrategySet.add(AdSlotConstants.AdNetworkStrategy.PERCENT);
    }
    
    public static Set<String> opensizeSet = new HashSet<String>();
    static {
    	opensizeSet.add(AdSlotConstants.Opensize.NONE);
    	opensizeSet.add(AdSlotConstants.Opensize.HUNDRED);
    	opensizeSet.add(AdSlotConstants.Opensize.EIGHTY);
    	opensizeSet.add(AdSlotConstants.Opensize.SIXTY);
    	opensizeSet.add(AdSlotConstants.Opensize.MESSAGEBOX);
    }
    
    public static Set<String> pushStrategySet = new HashSet<String>();
    static {
    	pushStrategySet.add(AdSlotConstants.PushStrategy.NONE);
    	pushStrategySet.add(AdSlotConstants.PushStrategy.ONCE_PER_DAY);
    	pushStrategySet.add(AdSlotConstants.PushStrategy.WHEN_AD_CHANGE);
    	pushStrategySet.add(AdSlotConstants.PushStrategy.NO_LIMIT);
    }
}
