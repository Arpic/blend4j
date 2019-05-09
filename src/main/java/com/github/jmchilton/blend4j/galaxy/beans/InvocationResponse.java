package com.github.jmchilton.blend4j.galaxy.beans;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvocationResponse {

	private String id;
	private String uuid;
	private String historyId;
	private String workflowId;

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

	public String getHistoryId() {
		return historyId;
	}

	@JsonProperty("history_id")
	public void setHistoryId(final String historyId) {
		this.historyId = historyId;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	@JsonProperty("workflow_id")
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
}
