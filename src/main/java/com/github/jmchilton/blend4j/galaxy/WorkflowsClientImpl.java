package com.github.jmchilton.blend4j.galaxy;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.github.jmchilton.blend4j.galaxy.beans.InvocationResponse;
import com.github.jmchilton.blend4j.galaxy.beans.Workflow;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowDetails;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInputs;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInvocation;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInvocationStep;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowOutputs;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

class WorkflowsClientImpl extends Client implements WorkflowsClient {
  public WorkflowsClientImpl(GalaxyInstanceImpl galaxyInstance) {
    super(galaxyInstance, "workflows");
  }

  public List<Workflow> getWorkflows() {
    return get(new TypeReference<List<Workflow>>() {
    });
  }

  public ClientResponse showWorkflowResponse(final String id) {
    return super.show(id, ClientResponse.class);
  }

  public WorkflowDetails showWorkflow(final String id) {
    return super.show(id, WorkflowDetails.class);
  }

  @Override
  public WorkflowInvocation showInvocation(final String workflowId, final String invocationId) {
    return getGalaxyInstance().getWebResource().path("workflows").path(workflowId).path("invocations").path(invocationId).get(WorkflowInvocation.class);
  }

  @Override
  public WorkflowInvocationStep showInvocationStep(final String workflowId, final String invocationId, final String stepId) {
    return getGalaxyInstance().getWebResource()
            .path("workflows").path(workflowId)
            .path("invocations").path(invocationId)
            .path("steps").path(stepId).get(WorkflowInvocationStep.class);
  }

  public String exportWorkflow(final String id) {
    WebResource webResource = getWebResource().path("download").path(id);
    return webResource.get(String.class);
  }

  public ClientResponse runWorkflowResponse(WorkflowInputs workflowInputs) {
    return super.create(workflowInputs);
  }

  public WorkflowOutputs runWorkflow(final WorkflowInputs workflowInputs) {
    return runWorkflowResponse(workflowInputs).getEntity(WorkflowOutputs.class);
  }

  @Override
  public ClientResponse invokeWorkflowResponse(WorkflowInputs workflowInputs) {
    return super.create(getGalaxyInstance().getWebResource().path("workflows").path(workflowInputs.getWorkflowId()).path("invocations"), workflowInputs);
  }

  @Override
  public InvocationResponse invokeWorkflow(final WorkflowInputs workflowInputs) {
    return invokeWorkflowResponse(workflowInputs).getEntity(InvocationResponse.class);
  }

  public ClientResponse importWorkflowResponse(final String json) {
    final String payload = String.format("{\"workflow\": %s}", json);
    return create(getWebResource().path("upload"), payload);
  }

  public Workflow importWorkflow(String json) {
    return importWorkflowResponse(json).getEntity(Workflow.class);
  }

  @Override
  public ClientResponse deleteWorkflowRequest(String id) {
    return deleteResponse(getWebResource(id));
  }
}
