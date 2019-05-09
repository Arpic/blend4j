package com.github.jmchilton.blend4j.galaxy;

import com.github.jmchilton.blend4j.galaxy.ToolsClient.FileUploadRequest;
import com.github.jmchilton.blend4j.galaxy.ToolsClient.FtpUploadRequest;
import com.github.jmchilton.blend4j.galaxy.beans.OutputDataset;
import com.github.jmchilton.blend4j.galaxy.beans.Tool;
import com.github.jmchilton.blend4j.galaxy.beans.ToolExecution;
import com.github.jmchilton.blend4j.galaxy.beans.ToolSection;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class ToolsTest {
  private GalaxyInstance instance;
  private ToolsClient client;

  @BeforeMethod
  public void init() {
    instance = TestGalaxyInstance.get();
    client = instance.getToolsClient();
  }
  
  @Test
  public void testUploadRequest() {
    final FileUploadRequest request = testRequest();
    final ClientResponse clientResponse = client.uploadRequest(request);
    assert clientResponse.getStatus() == 200;
    //final String reply = clientResponse.getEntity(String.class);
    //assert false: reply;
  }

  @Test
  public void testFtpUploadRequest() {
    final FtpUploadRequest request = testFtpRequest();
    final ClientResponse clientResponse = client.uploadRequest(request);
    assert clientResponse.getStatus() == 200;
  }
  
  @Test
  public void testUpload() {
    final FileUploadRequest request = testRequest();
    final ToolExecution toolExecution = client.upload(request);
    final List<OutputDataset> datasets = toolExecution.getOutputs();
    assert ! datasets.isEmpty();
    assert datasets.get(0).getOutputName().equals("output0");
    
    //final ClientResponse clientResponse = client.uploadRequest(request);
    //assert clientResponse.getStatus() == 200;
    //final String reply = clientResponse.getEntity(String.class);
    //assert false: reply;
  }

  @Test
  public void testFtpUpload() {
    final FtpUploadRequest request = testFtpRequest();
    final ToolExecution toolExecution = client.upload(request);
    final List<OutputDataset> datasets = toolExecution.getOutputs();
    assert !datasets.isEmpty();
    assert datasets.get(0).getOutputName().equals("output0");
  }
    
  private FileUploadRequest testRequest() {
    final String historyId = TestHelpers.getTestHistoryId(instance);
    final File testFile = TestHelpers.getTestFile();
    final FileUploadRequest request = new FileUploadRequest(historyId, testFile);
    return request;
  }

  private FtpUploadRequest testFtpRequest() {
    final String historyId = TestHelpers.getTestHistoryId(instance);
    final String ftpUrl = "ftp://ftp.ensembl.org/pub/release-86/fasta/homo_sapiens/dna/Homo_sapiens.GRCh38.dna.chromosome.MT.fa.gz";
    final FtpUploadRequest request = new FtpUploadRequest(historyId, ftpUrl);
    return request;
  }
  
  @Test
  public void testGetTools() {
	final List<ToolSection> tools = client.getTools();
	assert tools != null;
	assert ! tools.isEmpty();
	
	for (final ToolSection tool : tools) {
		assert tool.getId() != null;
	}
  }
  
  @Test
  public void testGetToolDetails() {
	  final List<ToolSection> tools = client.getTools();
	  // pick an arbitrary tool to get details about.
	  final Tool toolDetails = client.showTool(tools.iterator().next().getElems().iterator().next().getId());
	  
	  assert toolDetails != null;
	  assert toolDetails.getName() != null;
	  assert toolDetails.getVersion() != null;
  }
}
