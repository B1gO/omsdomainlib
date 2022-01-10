package com.ebay.omsdomainlib.audit.comparator.facet;

import com.ebay.omsdomainlib.audit.report.IReport;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 10:55 PM
 */
public interface IFacetComparator {

    boolean qualified(JsonNode org, JsonNode tar, String path);

    boolean compare(JsonNode org, JsonNode tar, String path, JsonNode pattern, String key, IReport report);
}
