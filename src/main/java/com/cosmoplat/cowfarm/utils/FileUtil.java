package com.cosmoplat.cowfarm.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 */
public class FileUtil {


    /**
     * 将多个文本文件合并为一个文本文件
     *
     * @param outFileName
     * @param inFileNames
     * @throws IOException
     */
    public static void merge(String outFileName, List<String> inFileNames) throws IOException {

        FileWriter writer = new FileWriter(outFileName, true);

        for (String inFileName : inFileNames) {
            try {
                String txt = readToString(inFileName);
                writer.write(txt);
                System.out.println(txt);
            } catch (Exception e) {
            }
        }
        writer.close();
    }


    /**
     * 查找某目录下的所有文件名称
     *
     * @param path
     * @return
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {//如果是文件
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {//如果是文件夹
                files.addAll(getFiles(tempList[i].toString()));
            }
        }
        return files;
    }


    /**
     * 读取文本文件内容到字符串
     *
     * @param fileName
     * @return
     */
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

}
