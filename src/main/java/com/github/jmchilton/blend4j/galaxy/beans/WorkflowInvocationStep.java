package com.github.jmchilton.blend4j.galaxy.beans;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowInvocationStep {

	private String id;
	private String workflowStepUUID;
	private String state;
	private Long orderIndex;
	private Map<String, StepOutput> outputs;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class StepOutput {
		private String id;
		private String uuid;

		public String getId() {
			return id;
		}

		@JsonProperty("id")
		public void setId(String id) {
			this.id = id;
		}

		public String getUuid() {
			return uuid;
		}

		@JsonProperty("uuid")
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
	}

	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public String getWorkflowStepUUID() {
		return workflowStepUUID;
	}

	@JsonProperty("workflow_step_uuid")
	public void setWorkflowStepUUID(String workflowStepUUID) {
		this.workflowStepUUID = workflowStepUUID;
	}

	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	public Long getOrderIndex() {
		return orderIndex;
	}

	@JsonProperty("order_index")
	public void setOrderIndex(Long orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Map<String, StepOutput> getOutputs() {
		return outputs;
	}

	@JsonProperty("outputs")
	public void setOutputs(Map<String, StepOutput> outputs) {
		this.outputs = outputs;
	}
}
