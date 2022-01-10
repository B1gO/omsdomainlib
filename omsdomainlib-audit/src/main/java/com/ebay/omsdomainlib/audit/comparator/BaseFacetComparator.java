package com.ebay.omsdomainlib.audit.comparator;

import com.ebay.omsdomainlib.audit.comparator.facet.IFacetComparator;
import com.ebay.omsdomainlib.audit.report.IReport;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 11:01 PM
 */
public class BaseFacetComparator implements IFacetComparator {
    @Override
    public boolean qualified(JsonNode org, JsonNode tar, String path) {
        return false;
    }

    @Override
    public boolean compare(JsonNode org, JsonNode tar, String path, JsonNode pattern, String key, IReport report) {
        return false;
    }
}
