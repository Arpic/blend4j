package com.github.jmchilton.blend4j.galaxy;

import com.github.jmchilton.blend4j.toolshed.RepositoriesClient;
import com.sun.jersey.api.client.WebResource;

public interface GalaxyInstance {
  HistoriesClient getHistoriesClient();

  LibrariesClient getLibrariesClient();

  UsersClient getUsersClient();

  WorkflowsClient getWorkflowsClient();

  RolesClient getRolesClient();

  ToolsClient getToolsClient();

  ConfigurationClient getConfigurationClient();
  
  ToolShedRepositoriesClient getRepositoriesClient();
    
  WebResource getWebResource();

  String getGalaxyUrl();
  
  String getApiKey();
}
