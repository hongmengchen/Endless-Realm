package cn.hmck.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件名生成工具类
 *
 * @author 陈亮
 * @since 2024-12-14
 */

public class FileNameUtil {
    /**
     * 生成当前时间的文件名（格式：yyyyMMddHHmmss）
     *
     * @return 时间戳字符串
     */
    public static String generateTimeBasedFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
