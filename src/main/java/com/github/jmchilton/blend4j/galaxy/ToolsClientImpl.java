package com.github.jmchilton.blend4j.galaxy;

import com.github.jmchilton.blend4j.galaxy.beans.Dataset;
import com.github.jmchilton.blend4j.galaxy.beans.History;
import com.github.jmchilton.blend4j.galaxy.beans.ToolInputs;
import com.sun.jersey.api.client.ClientResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ToolsClientImpl extends ClientImpl implements ToolsClient {
  ToolsClientImpl(GalaxyInstanceImpl galaxyInstance) {
    super(galaxyInstance, "tools");
  }

  public List<Dataset> create(History history, ToolInputs inputs) {
    inputs.setHistoryId(history.getId());
    super.create(inputs);
    // XXX Datasets not yet properly returned from Tool creation
    return new ArrayList();
  }
  
  public ClientResponse fileUploadRequest(final String historyId,
                                          final String fileType,
                                          final String dbKey,
                                          final File file) {
    final FileUploadRequest request = new FileUploadRequest(historyId, file);
    request.setFileType(fileType);
    request.setDbKey(dbKey);
    return uploadRequest(request);
  }
  
  public ClientResponse uploadRequest(final FileUploadRequest request) {
    final Map<String, String> uploadParameters = new HashMap<String, String>();
    final String datasetName = request.getDatasetName();
    if(datasetName != null) {
      uploadParameters.put("NAME", datasetName);
    }
    uploadParameters.put("dbkey", request.getDbKey());
    uploadParameters.put("file_type", request.getFileType());
    int index = 0;
    for(final UploadFile file : request.getFiles()) {  
      uploadParameters.put(String.format("files_%d|NAME", index), file.getName());
      uploadParameters.put(String.format("files_%d|type", index), "upload_dataset");
      index++;
    }
    final Map<String, Object> requestParameters = new HashMap<String, Object>();
    requestParameters.put("tool_id", request.getToolId());
    requestParameters.put("history_id", request.getHistoryId());
    requestParameters.put("inputs", write(uploadParameters));
    requestParameters.put("type", "upload_dataset");
    return multipartPost(getWebResource(), requestParameters, prepareUploads(request.getFileObjects()));
  }
          
  
}
