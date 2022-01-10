package com.ebay.omsdomainlib.audit.report;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 4:08 PM
 */
public interface IReport<T> {

    void report(String key, String path, String org, String tar, boolean success);

    T collect();
}
