package com.github.jmchilton.blend4j.galaxy;

import com.github.jmchilton.blend4j.galaxy.beans.TabularToolDataTable;
import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

public class ToolDataClientImpl extends Client implements ToolDataClient {
    ToolDataClientImpl(GalaxyInstanceImpl galaxyInstance) {
        super(galaxyInstance, "tool_data");
    }

    public ClientResponse showDataTableRequest(String dataTableId) {
        return null;
    }

    public List<TabularToolDataTable> getDataTables() {
        return null;
    }

    public TabularToolDataTable showDataTable(final String dataTableId) {
        return null;
    }
}
