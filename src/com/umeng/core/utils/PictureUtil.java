package com.umeng.core.utils;

import java.io.*;

public class PictureUtil {
    public static String getPictureSize(String filename) {
        String line = "";
        try {
            Process process = Runtime.getRuntime()
                    .exec("identify -format \"%wx%h\\n\" " + filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                return line.replaceAll("\"", "");
            }
        } catch (IOException e) {
            throw new RuntimeException("获取图片大小时发生异常", e);
        }
        return "0x0";
    }

    public static boolean isPictureTooSmall(String filePicSize, String size) {
        String[] fsizes = filePicSize.split("x");
        int fwid = Integer.parseInt(fsizes[0]);
        int fhei = Integer.parseInt(fsizes[1]);
        String[] fsizes2 = size.split("x");
        int wid = Integer.parseInt(fsizes2[0]);
        int hei = Integer.parseInt(fsizes2[1]);
        if (fwid < wid && fhei < hei) {
            return true;
        }
        return false;
    }

    public static void resizePicture(String filename, String aimfilename, int width, int height,
            boolean compress) {

        Process process = null;
        try {
            if (compress) {
                process = Runtime.getRuntime().exec(
                        "convert -filter Box -resize " + width + "x" + height + " " + filename + " "
                                + aimfilename);
            } else {
                process = Runtime.getRuntime().exec("cp " + filename + " " + aimfilename);
            }
        } catch (IOException e) {
            throw new RuntimeException("图片转换异常", e);
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException("图片转换异常", e);
        }
    }
    
    public static void compress(String fileName) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("jpegoptim -m80 --strip-all " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("图片压缩异常", e);
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException("图片压缩异常", e);
        }
    }
}
