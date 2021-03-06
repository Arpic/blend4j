package com.github.jmchilton.blend4j.galaxy;

import java.util.List;

import com.github.jmchilton.blend4j.galaxy.beans.InvocationResponse;
import com.github.jmchilton.blend4j.galaxy.beans.Workflow;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowDetails;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInputs;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInvocation;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInvocationStep;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowOutputs;
import com.sun.jersey.api.client.ClientResponse;

public interface WorkflowsClient {
  List<Workflow> getWorkflows();

  WorkflowDetails showWorkflow(String workflowId);

  WorkflowInvocation showInvocation(String workflowId, String invocationId);

  WorkflowInvocationStep showInvocationStep(String workflowId, String invocationId, String stepId);

  String exportWorkflow(String id);

  Workflow importWorkflow(String json);

  ClientResponse importWorkflowResponse(String json);

  @Deprecated
  ClientResponse runWorkflowResponse(WorkflowInputs workflowInputs);

  @Deprecated
  WorkflowOutputs runWorkflow(WorkflowInputs workflowInputs);

  ClientResponse invokeWorkflowResponse(WorkflowInputs workflowInputs);

  InvocationResponse invokeWorkflow(WorkflowInputs workflowInputs);
  
  /**
   * Deletes the workflow with the given id (this is irreversible). This will
   * return a {@link ClientResponse} object providing access to the status code
   * and the non-serialized body of the response.
   * 
   * @param id
   *          The id of the workflow to delete.
   * @return A {@link ClientResponse} for this request. The status code provided
   *         by {@link ClientResponse#getClientResponseStatus()} should be
   *         verified for success.
   */
  ClientResponse deleteWorkflowRequest(String id);
}
