package com.wuxianmeihao.utils;

import java.io.*;
import java.text.*;
import java.util.*;

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

    public static String generateFileName(String imageExt, String osSymbol, String uploadDir,
            String uploadTmpDir) {

        SimpleDateFormat fmtYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat fmtMonth = new SimpleDateFormat("MM");
        SimpleDateFormat fmtDay = new SimpleDateFormat("dd");

        Date currentTime = new Date();
        String strYear = fmtYear.format(currentTime);
        String strMonth = fmtMonth.format(currentTime);
        String strDay = fmtDay.format(currentTime);

        StringBuffer path = new StringBuffer();
        path.append(osSymbol).append(strYear).append(osSymbol).append(strMonth).append(osSymbol).append(strDay).append(osSymbol);
        StringBuffer finalPath = new StringBuffer(uploadDir).append(path);
        StringBuffer tmpPath = new StringBuffer(uploadTmpDir).append(path);
        File dir = new File(finalPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dir = new File(tmpPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + imageExt;
        String fullPathFileName = finalPath + fileName;

        for (int i = 0; i < 3; i++) {
            File fileTest = new File(fullPathFileName);
            if (!fileTest.exists()) {
                break;
            } else {
                if (i == 2) {
                    throw new RuntimeException("文件名连续重复超过3次，也太倒霉了");
                } else {
                    fileName = UUID.randomUUID().toString() + imageExt;
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
