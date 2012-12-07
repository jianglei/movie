package com.wuxianmeihao.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wuxianmeihao.utils.FileConfig;
import com.wuxianmeihao.utils.FileUtil;
import com.wuxianmeihao.utils.PictureUtil;
import com.wuxianmeihao.utils.StringUtil;

@Controller
// 声明该类为控制器类
@RequestMapping(value = "/upload")
public class FileUploadController extends MultiActionController {

	
	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("uploadType",request.getAttribute("uploadType"));
		return "upload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	// 将文件上传请求映射到该方法
	public String handleFormUpload(
			HttpServletRequest request,
			HttpServletResponse response,
		    Model model) { // 请求参数一定要与form中的参数名对应
		
		String uploadType = "pic";
		String fileObject = "banner_File";
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

	    CommonsMultipartFile mFile = (CommonsMultipartFile) multipartRequest.getFile(fileObject);
	    model.addAttribute("uploadType", uploadType);
	    model.addAttribute("feedback", multipartRequest.getParameter("feedback"));
	    
		// 在上传过程中出现问题时返回客户端的信息
		String message = "";

		if (!((mFile == null) || mFile.isEmpty())) {
		    String fileName = mFile.getFileItem().getName();

            // 检查文件类型
            message = checkType(fileName, uploadType);
            if (StringUtil.isNotEmpty(message)) {
                return uploadAssert(model, message);
            }

            //String localPath = fileConfig.getFilePath();//本地保存路径
            String localPath = this.getClass().getClassLoader().getResource("/").getPath()+"../../"+"uploadFloder";
            System.out.println(localPath);

            // 生成文件名同时创建不存在的目录
            String fileRealName = getRealFileName(fileName, localPath);

            // 创建文件
            File file = new File(localPath + "/" + fileRealName);

            try {
                // 将上传的文件写入新建的文件中
                mFile.getFileItem().write(file);
                
                // 检查图片文件的高度和宽度
//                message = this.checkPicSize(uploadType, file, width, height);
                
               
            } catch (Exception e) {
                e.printStackTrace();
                message = "在服务器上创建文件失败";
                return uploadAssert(model, message);
            }
            String url = ("" + fileRealName);
            model.addAttribute("url", url.replace("\\\\", "/"));
        } else {
            message = "文件不能为空";
        }
        return uploadAssert(model, message);
    }

    /**
     * 对文件名进行修改
     * 
     * @param fileName
     * @return
     */
    private String getRealFileName(String fileName, String dir) {
        String[] fileNameArray = fileName.split("\\.");
        if (fileNameArray.length < 2) {
            return null;
        }
        return FileUtil.generateFileName(
                "."+fileNameArray[fileNameArray.length - 1],
                "/", dir, dir);
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
        return "/ad/frame";
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
    private String checkPicSize(String type, File file, int width, int height)
            throws IOException {
        if (type.equals("pic")) {
            BufferedImage bi = javax.imageio.ImageIO.read(file);
            if (width != bi.getWidth() && height != bi.getHeight()) {
                if(!file.getName().endsWith(".gif")){
                    PictureUtil.resizePicture(file.getPath().replace("\\", "\\\\"),
                            file.getPath().replace("\\", "\\\\"), width, height,
                            true);
                }
                return "图片的宽度或高度不符合要求";
            }
        }
        return "";
    }
}
