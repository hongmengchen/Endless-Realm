package cn.hmck.util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * JSON文件操作工具类
 *
 * @author 陈亮
 * @since 2024-12-14
 */

public class JSONFileUtil {
    public static String readFile(String filepath) throws Exception {
        FileInputStream fis = new FileInputStream(filepath);
        return IOUtils.toString(fis);
    }

    public static void writeFile(String data, String filepath)
            throws Exception {
        FileOutputStream fos = new FileOutputStream(filepath);
        IOUtils.write(data, fos);
    }
}
