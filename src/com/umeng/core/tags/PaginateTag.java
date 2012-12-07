package com.umeng.core.tags;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 分页标签
 * @author hywang
 *
 */
public class PaginateTag extends TagSupport{
	//当前页
    public int pageNo=1;
    //总页数
    public int totalPages=1;
    //显示的页数
    public int displayPageCount=5;
    //每页记录数
    public int pageSize=5;
    //url
    private String requestUrl;
	@Override
	public int doStartTag() throws JspException {
		int[] range=computeDisplayRange();
		StringBuilder builder=new StringBuilder();
		if(totalPages>1){//总页数超过一页时才生出分页
			if(!requestUrl.contains("?")){
				requestUrl+="?";
			}
			builder.append("<div class=\"umengADsystem_page\"><ul>");
			if(range[0]>1){
				builder.append("<li><a href=\""+requestUrl+"&pageNo="+1+"&pageSize="+pageSize+"\" class=\"queryLink\">首页</a></li>");
				builder.append("<li><a href=\""+requestUrl+"&pageNo="+(pageNo-1)+"&pageSize="+pageSize+"\" class=\"queryLink\">上一页</a></li>");
			}
			for(int i=range[0]; i<=range[1]; i++){
				if(i==pageNo){
					builder.append("<li><span>"+i+"</span></li>");	
				}else{
					builder.append("<li><a href=\""+requestUrl+"&pageNo="+i+"&pageSize="+pageSize+"\" class=\"queryLink\">"+i+"</a></li>");
				}
			}
			if(range[1]<totalPages){
				builder.append("<li><a href=\""+requestUrl+"&pageNo="+(pageNo+1)+"&pageSize="+pageSize+"\" class=\"queryLink\">下一页</a></li>");
				builder.append("<li><a href=\""+requestUrl+"&pageNo="+totalPages+"&pageSize="+pageSize+"\"  class=\"queryLink\">尾页</a></li>");
			}
			builder.append("</ul></div>");
			try {
				pageContext.getOut().print(builder.toString());
			} catch (IOException e) {
				 throw new JspTagException("PaginateTag: "+e.getMessage());
			}
		}
		return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		 return EVAL_PAGE;
	}
	/**
	 * 计算需要显示的页的范围
	 * @return
	 */
	private int[] computeDisplayRange(){
		int[] range=new int[2];
		if(displayPageCount>=totalPages){
			range[0]=1;
			range[1]=totalPages;
		}else{
			range[0]=pageNo-displayPageCount/2;
			range[1]=range[0]+displayPageCount-1;
			if(range[0]<1){
				range[0]=1;
				range[1]=range[0]+displayPageCount-1;
			}
			if(range[1]>totalPages){
				range[1]=totalPages;
				range[0]=range[1]-displayPageCount+1;
			}
		}
		return range;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getDisplayPageCount() {
		return displayPageCount;
	}
	public void setDisplayPageCount(int displayPageCount) {
		this.displayPageCount = displayPageCount;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
