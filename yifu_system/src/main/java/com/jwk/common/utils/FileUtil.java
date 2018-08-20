package com.jwk.common.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * 操作文件类
 */

public class FileUtil {



    //文件下载
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //单文件上传
    public static String fileInput(MultipartFile file){

        String type="upload/";
        if(file.getContentType().contains("image")){
            type="image/";
        }
        if(file.getContentType().contains("video")){
            type="video/";
        }
        String fileName=file.getOriginalFilename();

        String firstname= UUID.randomUUID().toString().replace("-","");
        String lastname=fileName.substring(fileName.lastIndexOf("."));
        fileName=firstname+lastname;

        try {
            QiniuUtils.upload(file.getBytes(), type + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QiniuUtils.publicURi + type + fileName;

    }


    //多文件上传的工具类
    public static List<String> uploadAttachment(HttpServletRequest request, String type) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles(type);
        System.out.println("数据长度========>>>>>>>>>>" + files.size());
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        // String realPath = MessageUtils.message("file.upload.path", null);
        // System.err.println("realpath=====>>>>>" + realPath);
        String savePath = request.getSession().getServletContext().getRealPath("/") + "p_image\\" + type + "\\" + year+ "\\" + month + "\\";
        //  String savePath = "government"+ File.separator + "image"+ File.separator + year+ File.separator + month + File.separator;
        System.out.println("保存路径=====>" + savePath);
        List<String> fileNames = new ArrayList<String>();
        for (MultipartFile multipartFile : files) {
            System.out.println("------" + multipartFile.getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("文件绝对路径名字=====>" + savePath);
            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String custName = "" + System.currentTimeMillis() + "." + prefix;
            System.out.println("**custName******"+custName);
            if (StringUtils.isNotNull(fileName)) {
                File targetFile = new File(savePath, custName);
                // fileName = year+"-"+month+"-"+fileName;
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                    System.out.println("********"+"保存文件");
                    multipartFile.transferTo(targetFile);


                }
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fileNames.add(savePath + custName);
            }
        }
        return fileNames;
    }
    public static String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();  String res=null;
        try(BufferedReader reader = req.getReader();) {
            char[]buff = new char[1024];
            int len=0;;
            while((len = reader.read(buff)) != -1) {
                // String res = new String(buff, 0, len,"UTF-8");
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            res = new String(sb.toString().getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public static String getRequestPayloadJsonObject(HttpServletRequest request ){
        InputStream inputStream=null;
        String res=null;
        try {
            inputStream = request.getInputStream();


            byte[] buff = new byte[1024];
            int len = -1;
            while (-1 != (len = inputStream.read(buff))) {
                // 将字节数组转换为字符串，并且设置为“UTF-8”格式编码（不然会出现乱码）
                res = new String(buff, 0, len,"UTF-8");
                System.out.println("**res***"+res);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;

    }

    public static Map<String, String> requestUtil(String request)
    {
        Map<String, String> map = new HashMap<String, String>();
        map=strRequest(map, request);
        return map;
    }

    public static Map<String, String>  strRequest(Map<String, String> map, String s)
    {
        int length = s.length();
        int index1 = s.indexOf("=");
        String parm1 = s.substring(0, index1);
        int index2 = s.indexOf("&");
        if (index2 == -1)
        {
            String parm2 = s.substring(index1 + 1);
            map.put(parm1, parm2);
            // return null;
            return map;
        }
        String parm2 = s.substring(index1 + 1, index2);
        map.put(parm1, parm2);
        return strRequest(map, s.substring(index2 + 1));
    }
}
