package com.alita.weibo.imagewall;

import cn.hutool.core.util.StrUtil;
import com.alita.common.utils.DownloadUtil;
import com.alita.common.utils.ThreadPoolUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description: 微博相册下载
 * @author: Luancx
 * @date: 2022/6/30
 */
@Slf4j
public class ImageDownload {
    private static final Map<String, ConcurrentLinkedQueue<ImageWallResponses.ImageWallDetail>> QUEUE_MAP = new HashMap<>(4);
    /**
     * 原图
     */
    private static final String IMAGE_URL = "https://wx4.sinaimg.cn/mw2000/";
    /**
     * 缩略图
     */
    private static final String IMAGE_THUMBNAIL_URL = "https://wx2.sinaimg.cn/orj360/";

    /**
     * 下载图片
     *
     * @param uid      用户id
     * @param filePath 保存路径
     * @param cookie   cookie
     */
    @SneakyThrows
    public static void downloadImage(String uid, final String filePath, String cookie) {
        ThreadPoolUtils.execute(() -> {
            // 开始下载
            download(uid, filePath);
        });

        String sinceId = "0";
        do {
            sinceId = getImageUrl(uid, sinceId, cookie);
        } while (!StrUtil.isBlank(sinceId));
    }

    @SneakyThrows
    private static void download(String uid, String filePath) {
        while (true) {
            ConcurrentLinkedQueue<ImageWallResponses.ImageWallDetail> queue = QUEUE_MAP.get(uid);
            if (null == queue || queue.isEmpty()) {
                continue;
            }
            ImageWallResponses.ImageWallDetail imageWallDetail = queue.poll();
            if (imageWallDetail != null) {
                String fileName = imageWallDetail.getPid() + ".jpg";
                String url = IMAGE_URL + fileName;

                ThreadPoolUtils.execute(() -> {
                    try {
                        log.info("url:{}", url);
                        DownloadUtil.downloadFile(url, filePath + fileName);
                    } catch (Exception e) {
                        log.warn("下载失败：{}", url);
                    }
                });
            }
        }
    }

    /**
     * 获取所有图片url
     */
    private static String getImageUrl(String uid, String sinceId, String cookie) {
        ImageWallResponses imageWall = ImageWall.getImageWall(uid, sinceId, cookie);
        if (null != imageWall && imageWall.getOk() == 1) {
            ImageWallResponses.ImageWallData data = imageWall.getData();
            List<ImageWallResponses.ImageWallDetail> list = data.getList();
            if (!list.isEmpty()) {
                ConcurrentLinkedQueue<ImageWallResponses.ImageWallDetail> queue = QUEUE_MAP.get(uid);
                if (null == queue) {
                    queue = new ConcurrentLinkedQueue<>();
                    QUEUE_MAP.put(uid, queue);
                }
                queue.addAll(list);
            }
            if (!imageWall.getBottomTipsVisible()) {
                return data.getSinceId();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String cookie = "SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9W59v61WPWDCSzVXxQYNo7M.5JpX5KMhUgL.FoMp1hMEeo5Re0-2dJLoIE5LxKnLB--LBo5LxKqL1KnL1KBLxK-LBo.LBoBEeoz0Sntt; SINAGLOBAL=5799848886875.479.1655443332879; UOR=,,cn.bing.com; WBPSESS=VHyaQQ5jmkhozlMQtSPGutIuJa39yZWReHu69iDhZPYAoC6RLglCQ7-McM4U71q5F6hKni3tN_Cq0y_dS9PD15xhgcDJE9TANrbx7elb3MMtN1ljWLPAFHz8Q3lsCmK6Fj11KK13tpGTVDY_CgboHg==; ULV=1656573494464:7:7:5:715894935737.1313.1656573494448:1656569806563; PC_TOKEN=2542f02941; ALF=1688114299; SSOLoginState=1656578300; SCF=Apx-Pp2cfATdPnEQlsWGKhUc2uJz6Q3vvgsYuGg8NZ7CoTmIHHpwXKaymEUUK_liyQCdv0s97inuCk_XzkXJNco.; SUB=_2A25PuRCsDeRhGeFP41UT8i7EyDmIHXVszwVkrDV8PUNbmtB-LRXFkW9NQReOSXD71hHks9HiswGE0gw_6iwPDiXi; XSRF-TOKEN=KCRyd227XpZsXu7JSStOtJ40";
        downloadImage("2732448712", "C:\\software\\aliDrive\\微博相册\\嘉泽\\", cookie);
    }
}
