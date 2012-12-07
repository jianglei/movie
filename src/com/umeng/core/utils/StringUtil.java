package com.umeng.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 处理字符串
     * 
     * @param str
     * @return "" || str
     */
    public static String delSpecialChar(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim().replaceAll("[%&'',，；’。‘、;=?]*", "");
        }
    }

    public static boolean isEmpty(String s) {

        if (s == null || s.trim().equals("")) {
            return true;
        }
        return false;
    }
    
    public static boolean isEmpty(Object s) {

        if (s == null || s.toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {

        if (s == null || s.trim().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 清除掉所有特殊字符
     * 
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        String regEx = "[`~!@$%^&*()+=|{}':;',\\[\\].<>《》/?~！@￥%……&*（）——+|{}【】‘；：\"”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    public static String truncate(String str, int maxLen){
        str=(str==null) ? "" : str;
        str=str.trim();
        if(str.length()<=maxLen){
            return str;
        }else{
            return str.substring(0, maxLen+1)+"...";
        }
    }
    

	private final static char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	private final static int DIGITS_0 = '0';

	private final static int DIGITSA9 = 'A' - '9' - 1;

	private final static String[] PRE_ZERO = { "", "0", "00", "000", "0000", "00000", "000000", "0000000" };

	/**
	 * 比较两个字符串是否相等，如果两个字符创都是空，则也相等
	 * 
	 * @param	s1
	 * @param	s2
	 * @return
	 */
	public final static boolean isEqual(String s1, String s2) {
		if (s1 != null) {
			if (s1.equals(s2)) {
				return true;
			}
		} else if (s2 == null) {
			return true;
		}
		return false;
	}

	/**
	 * 返回异常的堆栈信息
	 */
	public static String throwableToString(Throwable t) {
		if (t == null) return "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * 解析 IP 地址为长整数
	 */
	public static long ip2number(String strIP) {
		if (strIP != null) {
			StringTokenizer st = new StringTokenizer(strIP, ".");
			if (st.countTokens() == 4) {
				long lResultIP = 0;
				while (st.hasMoreTokens()) {
					int iToken = 0;
					try {
						iToken = Integer.parseInt(st.nextToken().trim());
					} catch (Exception ex) {
						iToken = -1;
					}
					if (iToken < 0 || iToken > 255) {
						return -1L;
					}
					lResultIP = lResultIP << 8;
					lResultIP = lResultIP | iToken;
				}
				return lResultIP;
			}
		}
		return -1L;
	}

	/**
	 * 转化16进制字符串为整数
	 */
	public static int hex2int(String s) {
		int iResult = 0;
		int iLength = s.length() < 8 ? s.length() : 8;
		int iTemp = 0;
		for (int i = 0; i < iLength; i++) {
			iTemp = s.charAt(i) - DIGITS_0;
			if (iTemp > 9) iTemp = iTemp - DIGITSA9;
			iTemp &= 0xF;
			iResult <<= 4;
			iResult |= iTemp;
		}
		return iResult;
	}

	/**
	 * 格式化整数为16进制字符串
	 */
	public static String int2dec(int i, int digit) {
		String s = String.valueOf(i);
		int l = s.length();
		if (l < digit) {
			return PRE_ZERO[digit - l] + s;
		}
		if (l > digit) {
			return s.substring(0, digit);
		}
		return s;
	}

	/**
	 * 格式化整数为16进制字符串
	 */
	public static String int2hex(int j) {
		char[] buf = new char[8];
		for (int i = buf.length - 1; i >= 0; i--) {
			buf[i] = DIGITS[j & 0xF];
			j >>>= 4;
		}
		return new String(buf);
	}

	/**
	 * 转换字节为16进制的文本描述形式
	 * 
	 * @param	b	指定的字节
	 * @return	转化结果
	 */
	public final static String byte2Hex(byte b) {
		char[] out = new char[2];
		out[0] = DIGITS[(b >>> 4) & 0X0F];
		out[1] = DIGITS[b & 0X0F];
		return (new String(out));
	}

	/**
	 * 把输入的字节树组，逐字节转化为其16进制的文本描述形式
	 * 
	 * @param buf
	 *            输入的字节树组
	 * @param off
	 *            需要转化的开始位置
	 * @param len
	 *            需要转化的结束位置
	 * @return 转化结果
	 */
	public final static String bytesToHex(byte[] buf, int off, int len) {
		char[] out = new char[len * 2];

		for (int i = 0, j = 0; i < len; i++) {
			int a = buf[off++];
			out[j++] = DIGITS[(a >>> 4) & 0X0F];
			out[j++] = DIGITS[a & 0X0F];
		}
		return (new String(out));
	}

	public final static boolean isIPAddress(String s) {
		if (s != null) {
			int dot1 = s.indexOf('.');
			if (dot1 <= 0) {
				return false;
			}
			int temp;
			try {
				temp = Integer.parseInt(s.substring(0, dot1++));
				if (temp < 0 || temp > 255) {
					return false;
				}
			} catch (Exception ex) {
				return false;
			}

			int dot2 = s.indexOf('.', dot1);
			if (dot2 <= 0) {
				return false;
			}
			try {
				temp = Integer.parseInt(s.substring(dot1, dot2++));
				if (temp < 0 || temp > 255) {
					return false;
				}
			} catch (Exception ex) {
				return false;
			}

			int dot3 = s.indexOf('.', dot2);
			if (dot3 <= 0) {
				return false;
			}
			try {
				temp = Integer.parseInt(s.substring(dot2, dot3++));
				if (temp < 0 || temp > 255) {
					return false;
				}
			} catch (Exception ex) {
				return false;
			}

			try {
				temp = Integer.parseInt(s.substring(dot3));
				if (temp < 0 || temp > 255) {
					return false;
				}
			} catch (Exception ex) {
				return false;
			}

			return true;
		}
		return false;
	}

	private static boolean[] xlstInvalidChars = new boolean[256];

	static {
		for (int i = 0; i < xlstInvalidChars.length; i++) {
			xlstInvalidChars[i] = false;
		}

		// 0-8
		for (int i = 0; i < 9; i++) {
			xlstInvalidChars[i] = true;
		}

		//11 0B
		xlstInvalidChars[11] = true;

		// 14-31
		for (int i = 14; i < 32; i++) {
			xlstInvalidChars[i] = true;
		}

		xlstInvalidChars[127] = true;
	}

	/** 删掉xlst不支持的字符，然后返回原文 */
	public static String getXSLTFreeText(String text) {
		if (isEmpty(text)) return null;

		StringBuffer sb = new StringBuffer(text.length() * 2);
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c > 127) {
				sb.append(c);
			} else {
				if (!xlstInvalidChars[c]) {
					sb.append(c);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 将URL地址中给定的一个参数去掉。
	 * 
	 * @param queryString
	 *            将要处理的URL地址
	 * @param toescape
	 *            要删掉的参数名称。
	 */
	public static String getSubQueryString(String queryString, String toescape) {
		// String queryString = request.getQueryString() ;
		if (queryString == null) return "";

		int pos = queryString.indexOf(toescape);
		if (pos < 0) return queryString; // 不存在需要的数

		StringBuffer sb = new StringBuffer(128);
		int total = queryString.length();

		if (pos > 0) {
			int i = 0;
			while (i < pos) {
				sb.append(queryString.charAt(i));
				i++;
			}
		}

		while (pos < total && queryString.charAt(pos++) != '&') {
		}

		while (pos < total) {
			sb.append(queryString.charAt(pos));
			pos++;
		}
		int sbl = sb.length();
		if (sbl > 0 && sb.charAt(sbl - 1) == '&') {
			sb.setLength(sbl - 1);
		}

		return sb.toString();
	}

	/**
	 * 把null变成""<br>
	 */
	public static String dealNull(String str) {
		String returnstr = null;
		if (str == null)
			returnstr = "";
		else
			returnstr = str;
		return returnstr;
	}

	public static int toInt(String s, int defaultValue) {
		try {
			return new Integer(s).intValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 把string转换成int
	 * 
	 * @param s
	 *            要转换成int的String
	 * @return 如果转换失败，返回-1
	 */
	public static int toInt(String s) {
		return toInt(s, -1);
	}

	/**
	 * 把string数组转换成int数组
	 * 
	 * @param s
	 *            要转换成int的String数组
	 * @return 如果转换失败，返回null
	 */
	public static int[] toIntArray(String[] strArray) {

		if (null == strArray || strArray.length <= 0) return null;

		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) // 同时封多个版
		{
			int j = 0;
			try {
				j = Integer.valueOf(strArray[i]).intValue();
			} catch (Exception e) {
				return null;
			}
			if (j > 0) intArray[i] = j;
		}
		return intArray;
	}

	//过滤html字符，并将<>替换成&lt;&gt;，增加对&#NN;格式字符的清理
	//如果字符串为null，则返回空字符串""
	public static String filterInput(String str) {
		return filterInput(str, false);
	}

	//过滤html字符，并将<>替换成&lt;&gt;，增加对&#NN;格式字符的清理
	//如果字符串为null，则返回空字符串""
	public static String filterInput(String str, boolean half2full) {
		if (isEmpty(str)) {
			return "";
		}
		//应该只过滤对应<>的&#字符，以及开始和结束的空格 //replaceFirst("^[　 ]+", "").replaceFirst("[　 ]+$", "").
		String res = str.trim().replaceAll("&nbsp;", " ").replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
				">", "&gt;").replace("\"", "&quot;").replaceAll("(\\ue40a|\\ue40b)", "");
		return res;
		//"&#(?i)(60|62|074|076|x3c|x3e)(?-i);", "");.replaceAll("<[^>]*>", "")
	} //过滤html字符，并将<>替换成&lt;&gt;，增加对&#NN;格式字符的清理

	//反向过滤字符串
	public static String unfilterInput(String str) {
		if (isEmpty(str)) {
			return "";
		}
		//应该只过滤对应<>的&#字符，以及开始和结束的空格
		String res = str.replaceAll("&amp;", "&");
		return res;
	}

	/**
	 * String2Alpha
	 */
	// 字母Z使用了两个标签，这里有２７个值
	// i, u, v都不做声母, 跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪',
			'期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座' };

	public static char[] alphatable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',

	'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static int[] table = new int[27];

	// 初始化
	static {
		for (int i = 0; i < 27; ++i) {
			table[i] = gbValue(chartable[i]);
		}
	}

	// 主函数,输入字符,得到他的声母,
	// 英文字母返回对应的大写字母
	// 其他非简体汉字返回 '0'

	private static char Char2Alpha(char ch) {

		if (ch >= 'a' && ch <= 'z') return (char) (ch - 'a' + 'A');
		if (ch >= 'A' && ch <= 'Z') return ch;

		int gb = gbValue(ch);
		if (gb < table[0]) return '0';

		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb)) break;
		}

		if (i >= 26)
			return '0';
		else
			return alphatable[i];
	}

	// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
	public static String String2Alpha(String SourceStr) {
		String Result = "";
		try {

			Result = "" + Char2Alpha(SourceStr.charAt(0));
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}

	private static boolean match(int i, int gb) {
		if (gb < table[i]) return false;

		int j = i + 1;

		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i]))
			++j;

		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];

	}

	// 取出汉字的编码
	private static int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 返回特定长度的字符串
	 * @param str
	 * @param len
	 * @return
	 */
	public static String subString(String str, int len) {
		if (str == null || str.length() <= len)
			return str;
		return str.substring(0, len);
	}
	/**
	 * 根据char大于256来判断长度
	 * @param str
	 * @param len 中文字符长度
	 * @return
	 */
	@Deprecated
	public static String subStringCN2(String str, int len) {
		if(str == null){
			return null;
		}
		int sum = 0;
		int i = 0;
		for(i = 0 ; i < str.length(); i++){
			char c = str.charAt(i);
			int charLen = c<256?1:2;
			if(sum + charLen > len * 2){
				break;
			}
			sum +=charLen;
		}
		return str.substring(0, i);
	}
	/**
	 * 根据string的length()和getBytes().length来判断长度
	 * @param str
	 * @param len 英文字符长度
	 * @return
	 */
	public static String subStringCN(String str, int len) {
		if (str == null)
			return "";
		str = str.trim();
		
		String res="";
		if(len <= 2)
			return str;
		
		for(int i=0;i<str.length();i++){
			if(str.substring(0,i+1).getBytes().length > len) {
				res = str.substring(0,i);
				for(int j=0;j<len;j++){
					if(res.getBytes().length > len - 2) {
						res = str.substring(0,i-j-1);
					}
					else {
						res += "…";
						break;
					}
				}
				break;
			}
		}
		if(res == "")
			res = str;
		return res;
	}

	/**
	 * 去掉首尾空格,包括空格/全角空格/tab.
	 * @param title
	 * @return 过滤后的字符串
	 */
	public static String trim(String title) {
		if(title == null){
			return "";
		}
		int count = title.length();
		int len = count;
		int st = 0;
		char[] val = new char[title.length()];    /* avoid getfield opcode */
		title.getChars(0, title.length(), val, 0);
		while ((st < len) && ((val[st] <= ' ') || val[st] == 0x3000 || val[st] == '\t')) {
			st++;
		}
		while ((st < len) && (val[len - 1] <= ' ' || val[len - 1 ] == 0x3000 || val[len - 1] == '\t')) {
			len--;
		}
		return ((st > 0) || (len < count)) ? title.substring(st, len) : title;
	}

	public static String encodeUrl(String url, String queryString) {
		if(queryString != null){
			url += '?' + queryString;
		}
		try {
			return URLEncoder.encode(url.toString(), "GBK");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	public static String encodeGBK(String str) {
		try {
			return URLEncoder.encode(str, "GBK");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static String randomStr(int length) {
		String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char ch = base.charAt((int) Math.floor(Math.random()* base.length()));
			buff.append(ch);
		}
		return buff.toString();
	}

	public static String normalizeEmail(String email) {
		if(email == null){
			return null;
		}
		int index = email.indexOf('@');
		if(index < 0){
			return email;
		} else {
			return email.substring(0, index) + email.substring(index).toLowerCase();
		}
	}

	public static String filterHtmlTag(String str) {
		if(str == null){
			return null;
		}
		str = filterNonXmlChar(str);
		str = str.replaceAll("(&nbsp;|　)+", " ");
		//<br>替换为空格
		str = str.replaceAll("(?i)< *br */? *>", " ");

		//去除所有html tag
		str = str.replaceAll("(?i)< *(script|object).*?/", "</");
		str = str.replaceAll("<.*?>", "");
		
		//去除回车与制表符
		str = str.replaceAll("(\r|\n|\t)", " ");
		//多个空格替换为一个空格
		str = str.replaceAll(" +", " ");
		return str;
	}

	private static String filterNonXmlChar(String str){
		if(str == null){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) <= 31){
				continue;
			}
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = "中国打232";
		System.out.println(subStringCN(s, 3));
		System.out.println(subStringCN2(s, 6));
	}
	
    
}

