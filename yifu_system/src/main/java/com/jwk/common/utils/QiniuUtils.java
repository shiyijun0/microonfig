package com.jwk.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public final class QiniuUtils {
    private static String ak = "uHxkx7JOuLsiMSdhu7ZapJdji1sCHQyg8T7AtkIY";
    private static String sk = "SF1mcUqXFSF5ziy-eh-1zhl-3TFzC-SjpETN4CJd";
    private static String bucketName = "jwkjeans";
    //public static String publicURi = "http://p7ueylgbo.bkt.clouddn.com/";
    public static String publicURi = "http://192.168.253.132/";
    private static Auth auth = Auth.create(ak, sk);
    private static Configuration c = new Configuration(Zone.autoZone());
    private static UploadManager uploadManager = new UploadManager(c);
    private static BucketManager bucketManager = new BucketManager(auth, c);

    private QiniuUtils() {
    }

    /**
     * 获取上传token
     *
     * @param fileName 文件名
     * @return token
     */
    public static String getUploadToken(String fileName) {
        return auth.uploadToken(bucketName, fileName);
    }

    /**
     * 上传 路径上传
     *
     * @param path     上传路径
     * @param fileName 文件名
     * @return 返回信息
     */
    public static Response upload(String path, String fileName) {
        try {
            return uploadManager.put(path, fileName, getUploadToken(fileName));
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 上传 文件上传
     *
     * @param file     上传文件
     * @param fileName 文件名
     * @return 返回信息
     */
    public static Response upload(File file, String fileName) {
        try {
            return uploadManager.put(file, fileName, getUploadToken(fileName));
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 上传 字节流上传
     *
     * @param data     上传字节流
     * @param fileName 文件名
     * @return 返回信息
     */
    public static Response upload(byte[] data, String fileName) {
        try {
            return uploadManager.put(data, fileName,  auth.uploadToken(bucketName));
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 下载
     *
     * @param fileName 文件名
     * @return
     */
    @Deprecated
    public static String downLoad(String fileName) {
        try {
            String encodedFileName = URLEncoder.encode(fileName, "utf-8");
            return  publicURi+encodedFileName;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读文件内容
     *
     * @param fileName 文件名
     * @return
     */
    public static byte[] read(String fileName) {
        try {
            bucketManager.stat(bucketName, fileName);

            String url = publicURi + fileName;
            URL u = new URL(url);
            InputStream in = u.openStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            IOUtils.copy(in, output);
            byte[] bytes =output.toByteArray();

            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(output);

            return bytes;
        } catch (QiniuException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取文件hash
     *
     * @param fileName 文件名
     * @return 文件hash
     */
    public static String info(String fileName) {
        try {
            FileInfo info = bucketManager.stat(bucketName, fileName);
            return info.hash;
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    public static void delete(String fileName) {
        try {
            bucketManager.delete(bucketName, fileName);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传并下载接口，返回下载地址信息
     *
     * @param data
     * @return 下载地址
     */
    public static String upAndDownload(byte[] data){
        try {
            Response response = QiniuUtils.upload(data,null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return QiniuUtils.downLoad(putRet.hash);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }
}
