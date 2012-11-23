package com.github.jmchilton.blend4j.galaxy.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HistoryDetails extends History {
  private String state;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
}
