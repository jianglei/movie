package com.umeng.ufp.publisher.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.umeng.core.utils.StringUtil;

public class AndroidUtil {
    private static Logger log = Logger.getLogger(AndroidUtil.class);

	public static Map<String, String> getAPKInfo(String localPath, String path) {
		   if(StringUtil.isEmpty(localPath) || StringUtil.isEmpty(path)) return null;
		   
		   Map<String, String> info = new HashMap<String, String>();
		   
           Process process = null;
           try {
//               process = Runtime.getRuntime().exec(Constants.APK_ANALYSIS_CMD + " " + localPath + path);
               process = Runtime.getRuntime().exec(localPath + Constants.APK_ANALYSIS_CMD + " " + localPath + path);
               BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream())); 
               
               StringBuffer sb=new StringBuffer();
               String inline;  
               
               while(null!=(inline=br.readLine())){  
                   sb.append(inline).append("\n");  
               }
              
               Pattern p = Pattern.compile(Constants.PACKAGE_NAME_REGEX);
               Matcher match = p.matcher(sb);
                
               if ( match.find() ) {
            	   log.debug(match.group(0));
            	   log.debug(match.group(1));
                   info.put("appPackageName", match.group(1));
               } 
               p = Pattern.compile(Constants.VERSION_NAME_REGEX);
               match = p.matcher(sb);
				
               if ( match.find() ) {
            	   log.debug(match.group(0));
            	   log.debug(match.group(1));
                   info.put("appVersionName", match.group(1));
               } 
               p = Pattern.compile(Constants.VERSION_CODE_REGEX);
				match = p.matcher(sb);
               if ( match.find() ) {
            	   log.debug(match.group(0));
            	   log.debug(match.group(1));
                   info.put("appVersionCode", match.group(1));
               }
           } catch (IOException e) {
               e.printStackTrace();
               throw new RuntimeException("packagename analysis fail", e);
           }
		   
           return info;
	}
	
	 
}
