package com.github.jmchilton.blend4j.galaxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.jmchilton.blend4j.galaxy.beans.TabularToolDataTable;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ToolDataTest {
    private static final Logger logger = LoggerFactory.getLogger(ToolDataTest.class);

    private GalaxyInstance instance;
    private ToolDataClient client;

    @BeforeMethod
    public void init() {
        instance = TestGalaxyInstance.get();
        client = instance.getToolDataClient();
    }

    @Test
    public void testGetDataTables() {
        final List<TabularToolDataTable> toolDataTables = client.getDataTables();
        assert toolDataTables != null;
        assert ! toolDataTables.isEmpty();

        for (final TabularToolDataTable toolDataTable : toolDataTables) {
            assert toolDataTable.getName() != null;
        }
    }

    @Test
    public void testShowDataTable() {
        final List<TabularToolDataTable> toolDataTables = client.getDataTables();
        assert toolDataTables != null;
        assert ! toolDataTables.isEmpty();

        for (final TabularToolDataTable toolDataTable : toolDataTables) {
            assert toolDataTable.getName() != null;
            TabularToolDataTable dataTable = client.showDataTable(toolDataTable.getName());
            assert dataTable != null;
        }

    }

    @Test
    public void testShowDataTableRequest() {
        final List<TabularToolDataTable> toolDataTables = client.getDataTables();
        assert toolDataTables != null;
        assert ! toolDataTables.isEmpty();

        for (final TabularToolDataTable toolDataTable : toolDataTables) {
            assert toolDataTable.getName() != null;
            ClientResponse clientResponse = client.showDataTableRequest(toolDataTable.getName());
            assert clientResponse != null;
            assert 200 == clientResponse.getStatus();
        }

    }

    @Test
    public void testDeleteDataTableRequest() {
        TabularToolDataTable toolDataTable = client.showDataTable("igv_broad_genomes");
        assert toolDataTable != null;
        List<String> values = new ArrayList<String>(Arrays.asList("Human hg38", "http://s3.amazonaws.com/igv.broadinstitute.org/genomes/hg38.genome", "hg38"));
        ClientResponse clientResponse = client.deleteDataTableRequest(toolDataTable.getName(), values);
        assert clientResponse != null;
        assert 200 == clientResponse.getStatus();
        logger.debug(clientResponse.toString());
    }

    @Test
    public void testReloadDataTableRequest() {
        TabularToolDataTable toolDataTable = client.showDataTable("igv_broad_genomes");
        assert toolDataTable != null;
        ClientResponse clientResponse = client.reloadDataTableRequest(toolDataTable.getName());
        assert clientResponse != null;
        assert 200 == clientResponse.getStatus();
        logger.debug(clientResponse.toString());
    }

    @Test
    public void testGetFieldsForValue() {
        final TabularToolDataTable igvBroadGenomes = client.showDataTable("igv_broad_genomes");
        assert igvBroadGenomes != null;
        final List<String> fieldsList = igvBroadGenomes.getFieldsListForValue("hg38");
        assert fieldsList != null;
        final Map<String, String> fieldsMap = igvBroadGenomes.getFieldsMapForValue("hg38");
        assert fieldsMap != null;
        assert fieldsMap.get("value").equals("hg38");
    }

    @Test
    public void testGetValues() {
        final TabularToolDataTable igvBroadGenomes = client.showDataTable("igv_broad_genomes");
        assert igvBroadGenomes != null;
        List<String> values = igvBroadGenomes.getValues();
        assert values.size() == 156;
    }

    @Test
    public void testGetFieldsForColumn() {
        final TabularToolDataTable igvBroadGenomes = client.showDataTable("igv_broad_genomes");
        assert igvBroadGenomes != null;
        List<String> values = igvBroadGenomes.getFieldsForColumn("name");
        assert values.size() == 156;
    }

    @Test
    public void testGetField() {
        final TabularToolDataTable igvBroadGenomes = client.showDataTable("igv_broad_genomes");
        assert igvBroadGenomes != null;
        String field = igvBroadGenomes.getField("hg38", "name");
        assert field.equals("Human hg38");
    }
}
