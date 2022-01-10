package com.ebay.omsdomain.common.helper;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 3:49 PM
 */
public class FileHelper {

    public static File readResourceFile(String path) throws URISyntaxException {
        URL resource = FileHelper.class.getClassLoader().getResource(path);
        return new File(resource.toURI());
    }

    public static InputStream readResourceFileAsStream(String path) {
        return FileHelper.class.getClassLoader().getResourceAsStream(path);
    }
}
