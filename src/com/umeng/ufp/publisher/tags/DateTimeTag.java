package com.umeng.ufp.publisher.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author ke
 *
 */
public class DateTimeTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private long timestamp;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print(format.format(timestamp));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
}
