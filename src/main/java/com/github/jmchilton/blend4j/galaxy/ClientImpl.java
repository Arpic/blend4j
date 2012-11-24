package com.github.jmchilton.blend4j.galaxy;

import com.github.jmchilton.blend4j.galaxy.beans.HasGalaxyUrl;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

class ClientImpl {
  private final GalaxyInstanceImpl galaxyInstance;
  private final WebResource webResource;
  private final ObjectMapper mapper = new ObjectMapper();

  ClientImpl(final GalaxyInstanceImpl galaxyInstance, final String module) {
    this.galaxyInstance = galaxyInstance;
    this.webResource = buildWebResource(module);  
  }
  
  protected String write(final Object object) {
    try {
      return mapper.writer().writeValueAsString(object);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  GalaxyInstance getGalaxyInstance() {
    return galaxyInstance;
  }
  
  protected WebResource getWebResource() {
    return webResource;
  }
  
  protected WebResource getWebResource(final String id) {
    return getWebResource().path(id);
  }
  
  protected WebResource getWebResourceContents(final String id) {
    return getWebResource(id).path("contents");
  }
  
  private WebResource buildWebResource(final String module) {
    final WebResource webResource = galaxyInstance.getWebResource();
    return webResource.path(module);
  }
  
  protected <T> T readJson(final String json, final TypeReference<T> typeReference) {
    try {
      return mapper.readValue(json, typeReference);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  protected ClientResponse create(final Object object) {
    return create(getWebResource(), object);
  }
  
  protected ClientResponse create(final WebResource webResource, final Object object) {
    return webResource
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .post(ClientResponse.class, object);
  }
  
  protected <T> List<T> get(final WebResource webResource, 
                            final TypeReference<List<T>> typeReference) { 
    final String json = webResource
        .accept(MediaType.APPLICATION_JSON)
        .get(String.class);
    return readJson(json, typeReference);
  }
  
  protected <T> T show(final String id, Class<T> clazz) {
    return getWebResource().path(id).get(clazz);
  }

  protected <T> List<T> get(final TypeReference<List<T>> typeReference) {
    return get(getWebResource(), typeReference);
  }
  
  protected <T> TypeReference<List<T>> listTypeReference(final Class<T> clazz) {
    return new TypeReference<List<T>>() {};
  }
  
  protected <T extends HasGalaxyUrl> T setGalaxyUrl(final T bean) {
    bean.setGalaxyUrl(galaxyInstance.getGalaxyUrl());
    return bean;
  }
  
}
