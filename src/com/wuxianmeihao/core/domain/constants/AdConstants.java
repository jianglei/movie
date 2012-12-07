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
public class AdConstants {
	public class SystemStatus {
	    public final static String NORMAL = "normal";
	    public final static String LIMIT = "limit";
	}
    
	public class Budget {
	    public final static String NONE = "none";
	    public final static String LIMIT= "limit";
	}
	
	public class PriceType {
	    public final static String NONE = "none";
	    public final static String IMPRESSION = "impression";
	    public final static String CLICK = "click";
	    public final static String DOWNLOAD = "download";
	}
	
    public static Set<String> budgetSet = new HashSet<String>();
    static {
    	budgetSet.add(AdConstants.Budget.NONE);
    	budgetSet.add(AdConstants.Budget.LIMIT);
    }
    
    public static Set<String> priceTypeSet = new HashSet<String>();
    static {
    	priceTypeSet.add(AdConstants.PriceType.NONE);
    	priceTypeSet.add(AdConstants.PriceType.IMPRESSION);
    	priceTypeSet.add(AdConstants.PriceType.CLICK);
    	priceTypeSet.add(AdConstants.PriceType.DOWNLOAD);
    }


}
