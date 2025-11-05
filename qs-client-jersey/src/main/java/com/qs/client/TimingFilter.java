package com.qs.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

final class TimingFilter implements ClientRequestFilter, ClientResponseFilter {
  private static final String START = "qs.startNanos";

  @Override public void filter(ClientRequestContext req) throws IOException {
    req.setProperty(START, System.nanoTime());
  }

  @Override public void filter(ClientRequestContext req, ClientResponseContext res) throws IOException {
    Object s = req.getProperty(START);
    if (s instanceof Long start) {
      long ms = (System.nanoTime() - start) / 1_000_000;
      res.getHeaders().add("X-QS-Response-Time-Ms", Long.toString(ms));
    }
  }
}
