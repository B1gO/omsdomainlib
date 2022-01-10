package com.ebay.omsdomain.common.helper;

import com.ebay.omsdomain.common.constant.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 3:34 PM
 */
public class DateHelper {

    private static final List<SimpleDateFormat> sdfs = Stream.of(
            DateFormat.simpleDateFormat,
            DateFormat.simpleDateFormatWithoutMs,
            DateFormat.simpleDateFormatZ,
            DateFormat.simpleDateFormatZWithoutMs
    ).collect(Collectors.toList());

    public static Date parseDateString(String str) {
        for (SimpleDateFormat sdf : sdfs) {
            try {
                return sdf.parse(str);
            } catch (ParseException e) {
                System.out.println("try parsing date failed, falling back... " + e.getMessage());
            }
        }

        throw new RuntimeException("Error parsing data: " + str);
    }
}
