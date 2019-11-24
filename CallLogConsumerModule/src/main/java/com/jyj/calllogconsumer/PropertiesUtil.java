package com.jyj.calllogconsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Classname PropertiesUtil
 * @Description TODO
 * @Date 2019/11/23 10:10 下午
 * @Created by lipeijing
 */
public class PropertiesUtil {

    private static Properties properties;
    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("gendata.conf");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return properties.getProperty(key);
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}