package com.xjh.tank.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Author: XJH
 * @Date: 2022/12/28 1:26 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class DateUtils {

    public static String getDateTimeNow() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }
}
