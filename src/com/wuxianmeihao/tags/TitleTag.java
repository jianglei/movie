package com.wuxianmeihao.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.umeng.core.utils.StringUtil;

/**
 * 标题截断标记
 * 使用方法: <s:title text="${some-variable}" length="10" />
 * 
 * @author ke
 *
 */
public class TitleTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String text;
	
	private int length;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(StringUtil.subStringCN(text, length));
		} catch (IOException e) {
			// 是否应该抛出异常?
		}
		return SKIP_BODY;
	}


}
