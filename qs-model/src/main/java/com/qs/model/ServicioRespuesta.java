package com.qs.model;

public record ServicioRespuesta(String idTransaccion, int httpCode, long responseTimeMs, String payload) {}
