package com.xjh.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: XJH
 * @Date: 2022/9/15 12:52 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class PropertyMgr {
    static PropertyMgr propertyMgr;
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PropertyMgr() {

    }

    public PropertyMgr getInstance() {
        synchronized (this) {
            if (propertyMgr == null) {
                synchronized (this) {
                    return new PropertyMgr();
                }
            }
            return propertyMgr;
        }
    }


    public static Object get(Object key) {
        if (properties == null) return null;
        return properties.get(key);
    }

    public static Object getInt(Object key) {
        if (properties == null) return null;
        return Integer.parseInt((String) properties.get(key));
    }

    public static Object getString(Object key) {
        if (properties == null) return null;
        return (String) properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
