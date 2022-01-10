package com.ebay.omsdomainlib.common.constant;

import java.text.SimpleDateFormat;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 3:30 PM
 */
public class DateFormat {

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static final SimpleDateFormat simpleDateFormatWithoutMs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    public static final SimpleDateFormat simpleDateFormatZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat simpleDateFormatZWithoutMs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

}
