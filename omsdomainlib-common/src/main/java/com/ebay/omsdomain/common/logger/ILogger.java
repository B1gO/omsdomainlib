package com.ebay.omsdomain.common.logger;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 3:59 PM
 */
public interface ILogger {

    void warn(String message);

    void error(String message);

    void error(String message, Throwable t);
}
