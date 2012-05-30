package com.umeng.ufp.publisher.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.umeng.core.utils.FileUtil;
import com.umeng.core.utils.PictureUtil;
import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.publisher.utils.Constants;

@Controller
// 声明该类为控制器类
public class UFPServiceController extends BaseController<User, Integer> {

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private static Map<String, String> FilePathMapping = new HashMap<String, String>();
	static {
		FilePathMapping.put("icon", "icon/");
		FilePathMapping.put("icon_wap", "icon/");
		FilePathMapping.put("icon_app", "icon/");
		FilePathMapping.put("apk", "app/");
		FilePathMapping.put("pic", "img/");
	}
	
	@RequestMapping(value = "/upload/upload")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("uploadType",request.getAttribute("uploadType"));
		return "upload/upload";
	}

	@RequestMapping(value = "/upload/uploadFile", method = RequestMethod.POST)
	// 将文件上传请求映射到该方法
	public String handleFormUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("uploadType") String uploadType, // 设置请求参数的名称和类型
			@RequestParam("width") int width,
			@RequestParam("height") int height,
			@RequestParam("fileObject") String fileObject,
			@RequestParam("callback") String callback,
			Model model) { // 请求参数一定要与form中的参数名对应
		
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

	    CommonsMultipartFile mFile = (CommonsMultipartFile) multipartRequest.getFile(fileObject);
	    model.addAttribute("callback", callback);
	    model.addAttribute("uploadType", uploadType);
	    model.addAttribute("feedback", multipartRequest.getParameter("feedback"));
	    model.addAttribute("fileObject", fileObject);
	    
		// 在上传过程中出现问题时返回客户端的信息
		String message = "";

		if (!((mFile == null) || mFile.isEmpty())) {
		    String fullFileName = mFile.getFileItem().getName();
		    String fileName = fullFileName.substring(fullFileName.lastIndexOf("\\") + 1);

            // 检查文件类型
            message = checkType(fileName, uploadType);
            if (StringUtil.isNotEmpty(message)) {
                return uploadAssert(model, message);
            }

            String localPath = getServletContext().getRealPath("/") 
            		+ Constants.PATH_UPLOAD + "/" + FilePathMapping.get(uploadType);//本地保存路径

            // 生成文件名同时创建不存在的目录
            String fileRealName = getRealFileName(fileName, localPath, Constants.PATH_UPLOAD + "/" + FilePathMapping.get(uploadType));
            
            // 创建文件
            File file = new File(localPath + "/" + fileRealName);

            try {
                // 将上传的文件写入新建的文件中
                mFile.getFileItem().write(file);
                
                // 检查图片文件的高度和宽度
                //TODO:
                message = checkPicSize(uploadType, file, width, height);
                
                //TODO : apk analysis   
            } catch (Exception e) {
                e.printStackTrace();
                message = "在服务器上创建文件失败";
                return uploadAssert(model, message);
            }
            String url = (Constants.PIC_URL + FilePathMapping.get(uploadType) + fileRealName);
            model.addAttribute("url", url.replace("\\\\", "/"));
        } else {
            message = "文件不能为空";
        }
        return uploadAssert(model, message);
    }
	
	/*
	@RequestMapping(value = "/service/grabAppStoreInfo", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> grabAppStoreInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("url") String url,
			Model model) {
		Map<String, Object> resultParams = new HashMap<String, Object>();
		User user = getLoginUser(request);
		if(user != null) {
			try {
				String appStoreId = getAppStoreId(url);
				resultParams = xpWebService.grabAppStoreInfo(appStoreId);
				resultParams.put("status", "ok");
			} catch (Exception e) {
				e.printStackTrace();
				resultParams.put("status", "failed");
			}
		}
		return resultParams;
	}
	
	private String getAppStoreId(String url) {
		String appStoreId = null;
		try {
			String path = url.split("\\?")[0];
			int pos = path.lastIndexOf("id") + 2;
			appStoreId = path.substring(pos);
		} catch (Exception e) {                
			e.printStackTrace();
		}
		return appStoreId;
	}*/

    /**
     * 对文件名进行修改
     * 
     * @param fileName
     * @return
     */
    private String getRealFileName(String fileName, String localPath, String dir) {
        String[] fileNameArray = fileName.split("\\.");
        if (fileNameArray.length < 2) {
            return null;
        }
        return FileUtil.generateFileName(
                "."+fileNameArray[fileNameArray.length - 1],
                "", localPath, dir, dir, fileName);
        		//fileConfig.getSymbol(), dir, dir);
    }

    /**
     * 检查上传文件类型
     * 
     * @param fileName
     * @param type
     * @return
     */
    private String checkType(String fileName, String type) {
	      String[] fileNameArray = fileName.split("\\.");
	      if (fileNameArray.length < 2) {
	          return "上传文件类型错误，请重新选择文件上传";
	      }
	      String fileExt = "."+fileNameArray[fileNameArray.length-1];
	      if (type.equals("pic")) {
	          try {
	              if (!FileUtil.checkPicType(fileExt)) {
	                  return "上传文件不是图片类型，请重新选择文件上传!";
	              }
	          } catch (UnsupportedEncodingException e) {
	              e.printStackTrace();
	          }
	      } else if (type.equals("apk")) {
	          if (!FileUtil.checkPicType(fileExt, new String[] { ".apk" })) {
	              return "上传文件不是apk类型，请重新选择文件上传!";
	          }
	      }
	      return "";
    }
    
    /**
     * 定义跳转页面
     * 
     * @param model
     * @param message
     * @return
     */
    private String uploadAssert(Model model, String message) {
        if (StringUtils.isNotEmpty(message)) {
            model.addAttribute("message", message);
            model.addAttribute("submitFlag", "false");
        }else{
            model.addAttribute("message", "上传成功!");
            model.addAttribute("submitFlag", "true");
        }
        return "upload/frame";
    }

    /**
     * 检查图片的高和宽,如果不符合要求则将该图片重置大小
     * 
     * @param type
     * @param file
     * @param width
     * @param height
     * @throws IOException
     */
	public String checkPicSize(String type, File file, int width, int height)
            throws IOException {
        if (isImageType(type)) {
            BufferedImage bi = javax.imageio.ImageIO.read(file);
            if (width != bi.getWidth() && height != bi.getHeight()) { 	
                if(!file.getName().endsWith(".gif") && file.length() >= Constants.IMAGE_SIZE_LIMIT){
                	/*
                	try {
                		PictureUtil.resizePicture(file.getPath().replace("\\", "\\\\"),
                            file.getPath().replace("\\", "\\\\"), width, height,
                            true);
                	} catch (Exception e) {
                		return "图片的宽度或高度不符合要求";
                	}*/
                }
            }
        }
        return "";
    }
    
	public boolean isImageType(String type) {
		if(type.equals("pic") || type.equals("icon") || type.equals("icon_app")
				|| type.equals("icon_wap"))
			return true;
		else
			return false;
	}
}
