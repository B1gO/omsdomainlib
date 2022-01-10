package com.ebay.omsdomainlib.audit.model;

import com.ebay.omsdomainlib.common.helper.FileHelper;
import com.ebay.omsdomainlib.common.logger.DummyLogger;
import com.ebay.omsdomainlib.common.logger.ILogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 5:31 PM
 * @design_pattern factory
 */
public class ReportPattern {

    private static final String OTHERS = "others";
    private static final ConcurrentHashMap<String, ReportPattern> cache = new ConcurrentHashMap<>();

    private final LinkedHashMap<String, Pattern> map = new LinkedHashMap<>();

    private ReportPattern() {}

    public static ReportPattern create(String path) {
        return create(path, DummyLogger.getInstance());
    }

    public static ReportPattern create(String path, ILogger logger) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }
        ReportPattern reportPattern = new ReportPattern();
        InputStream is = FileHelper.readResourceFileAsStream(path);
        if (is != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] lineArr = line.split(",");
                    if (lineArr.length < 2) {
                        logger.error("Bad pattern definition: " + line);
                        continue;
                    }

                    reportPattern.addGroupPattern(lineArr[0], Pattern.compile(lineArr[1]));
                }
                cache.put(path, reportPattern);
            } catch (IOException e) {
                logger.error("Error loading report pattern: " + path, e);
            }
        } else {
            logger.error("Could not find report pattern: " + path);
        }
        return reportPattern;
    }

    public String getMatchName(String path) {
        for (Entry<String, Pattern> entry : map.entrySet()) {
            if (entry.getValue().matcher(path).matches()) {
                return entry.getKey();
            }
        }
        return OTHERS;
    }

    private void addGroupPattern(String nameKey, Pattern pattern) {
        map.put(nameKey, pattern);
    }
}
