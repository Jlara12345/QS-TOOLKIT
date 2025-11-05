package com.qs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.qs.model.ServicioRespuesta;

public final class QsRestClient {
  private final Client client = ClientBuilder.newClient().register(new TimingFilter());

  public ServicioRespuesta get(String url){
    long t0 = System.nanoTime();
    try(Response r = client.target(url).request().get()){
      long ms = (System.nanoTime()-t0)/1_000_000;
      String payload = r.hasEntity() ? r.readEntity(String.class) : null;
      return new ServicioRespuesta(r.getHeaderString("X-Transaction-Id"), r.getStatus(), ms, payload);
    }
  }
}
