package com.umeng.core.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.bson.types.ObjectId;

public class FileUtil {

    public static final String[] LEGALEXCEL = new String[] { ".xsl", ".xslx" };

    public static final String[] LEGALPICEXTARR = new String[] { ".jpg", ".jpeg", ".png", ".gif" };

    public static boolean checkPicType(String rawFileExt) throws UnsupportedEncodingException {
        return checkPicType(rawFileExt, LEGALPICEXTARR);
    }

    public static boolean checkPicType(String rawFileExt, String[] legals) {
        boolean isExtLegal = false;
        for (String legalPicExt : legals) {
            if (legalPicExt.equalsIgnoreCase(rawFileExt)) {
                isExtLegal = true;
                break;
            }
        }
        return isExtLegal;
    }

    // 文件后缀名改成jpg
    public static String fileNameConvertToJpg(String fileName) {
        if (fileName.toLowerCase().endsWith(".jpg")) {
            return fileName;
        } else {
            return fileName.substring(0, fileName.lastIndexOf(".")) + ".jpg";
        }
    }

    public static String generateFileName(String fileExt, String osSymbol, String localPath, String uploadDir,
            String uploadTmpDir, String originFileName) {
        StringBuffer path = new StringBuffer();
        
        StringBuffer finalPath = new StringBuffer(uploadDir).append(path);
        File dir = new File(localPath);
        if (!dir.exists())             dir.mkdirs();

        //TODO: verify is chinese?
        String fileName = ObjectId.get().toString() + fileExt;
        String fullPathFileName = finalPath + fileName;

        for (int i = 0; i < 3; i++) {
            File fileTest = new File(fullPathFileName);
            if (!fileTest.exists()) {
                break;
            } else {
                if (i == 2) {
                    throw new RuntimeException("文件名连续重复超过3次，也太倒霉了");
                } else {
                    fileName = ObjectId.get().toString() + fileExt;
                    fullPathFileName = finalPath + fileName;
                }
            }
        }

        return path + fileName;
    }

    public static long getFileSize(String fileName) throws Exception {
        File f = new File(fileName);
        if (f.exists()) {
            return f.length();
        } else {
            throw new Exception("文件不存在");
        }
    }
}
