package com.alita.common.utils;

import cn.hutool.http.HttpDownloader;
import lombok.SneakyThrows;

import java.io.FileOutputStream;

/**
 * @description: 文件下载工具类
 * @author: Luancx
 * @date: 2022/6/30
 */
public class DownloadUtil {
    /**
     * @param url      文件url
     * @param filePath 保存路径
     * @description: 下载文件
     */
    @SneakyThrows
    public static void downloadFile(String url, String filePath) {
        FileOutputStream fos = null;
        try {
            byte[] bytes = HttpDownloader.downloadBytes(url);
            // 创建文件输出流
            fos = new FileOutputStream(filePath);
            fos.write(bytes);
        } finally {
            if (null != fos) {
                fos.close();
            }
        }
    }
}
