package com.ebay.omsdomain.common.logger;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 4:00 PM
 * @description Singleton Pattern
 */
public class DummyLogger implements ILogger{

    private static final DummyLogger instance = new DummyLogger();

    private DummyLogger() {}

    @Override
    public void warn(String message) {
        System.out.println("WARN: " + message);
    }

    @Override
    public void error(String message) {
        System.out.println("ERROR: " + message);
    }

    @Override
    public void error(String message, Throwable t) {
        System.out.println("ERROR: " + message + " e: " + t.getMessage());
    }
}
