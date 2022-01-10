package com.ebay.omsdomainlib.audit.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

import com.ebay.omsdomainlib.common.helper.FileHelper;
import com.ebay.omsdomainlib.common.logger.ILogger;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 4:20 PM
 * @design_pattern factory
 */
public class Excludes {

    private static final String NULL = "null";
    private final HashMap<String, HashSet<String>> map = new HashMap<>();

    private Excludes() {}

    public static Excludes create(String excludeFile, ILogger logger) {
        InputStream inputStream = FileHelper.readResourceFileAsStream(excludeFile);
        Excludes excludes = new Excludes();
        if (inputStream != null) {
            // will call br.close() when try block is end.
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty() || line.startsWith("//")) {
                        continue;
                    }
                    if (line.contains("//")) {
                        line = line.split("//")[0].trim();
                    }
                    if (line.contains(":")) {
                        String[] arr = line.split(":");
                        HashSet<String> set = excludes.map.get(arr[0].trim());
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(arr[1].trim());
                        excludes.map.put(arr[0].trim(), set);
                    } else {
                        excludes.map.put(line.trim(), null);
                    }
                }
            } catch (IOException e) {
                logger.error("Error loading exclude: " + excludeFile, e);
            }
        } else {
            logger.error("Could not find exclude: " + excludeFile);
        }
        return excludes;
    }

    public boolean contains(String path, String key) {
        path = path.replaceAll("\\[\\d+", "[*]");
        if (map.containsKey(path)) {
            String keyVal = key == null ? NULL : key;
            return map.get(path).contains(keyVal);
        }
        return false;
    }

    public boolean contains(String path) {
        path = path.replaceAll("\\[\\d+", "[*]");
        return map.containsKey(path);
    }
}
