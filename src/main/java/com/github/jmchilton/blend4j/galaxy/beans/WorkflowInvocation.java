package com.github.jmchilton.blend4j.galaxy.beans;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowInvocation {

	private String id;
	private String state;
	private String workflowId;
	List<WorkflowInvocationStep> steps;

	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	@JsonProperty("workflow_id")
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public List<WorkflowInvocationStep> getSteps() {
		return steps;
	}

	@JsonProperty("steps")
	public void setSteps(List<WorkflowInvocationStep> steps) {
		this.steps = steps;
	}
}
