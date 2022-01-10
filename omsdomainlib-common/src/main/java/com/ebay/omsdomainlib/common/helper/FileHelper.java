package com.ebay.omsdomainlib.common.helper;

import com.ebay.omsdomainlib.common.logger.DummyLogger;
import com.ebay.omsdomainlib.common.logger.ILogger;

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

    private static final ILogger logger = DummyLogger.getInstance();

    public static File readResourceFile(String path) throws URISyntaxException {
        URL resource = FileHelper.class.getClassLoader().getResource(path);
        if (resource != null) {
            return new File(resource.toURI());
        }

        logger.error("Error loading report pattern: " + path, new Throwable("url is null"));
        return null;
    }

    public static InputStream readResourceFileAsStream(String path) {
        return FileHelper.class.getClassLoader().getResourceAsStream(path);
    }
}
