package com.mapbox.mapboxsdk.style.functions.stops;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.util.Map;

/**
 * TODO
 */
public class ExponentialStops<I, O> extends Stops<I, O> {
  public final float base;
  public final Stop<I, O>[] stops;
  //TODO: colorSpace

  @SafeVarargs
  public ExponentialStops(Float base, @NonNull @Size(min = 1) Stop<I, O>... stops) {
    this.base = base != null ? base : 1.0f;
    this.stops = stops;
  }

  @SafeVarargs
  public ExponentialStops(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    this(null, stops);
  }

  public Stop<I, O>[] getStops() {
    return stops;
  }

  public float getBase() {
    return base;
  }

  @Override
  public Map<String, Object> toValueObject() {
    Map<String, Object> map = super.toValueObject();
    map.put("base", base);
    map.put("stops", convert(stops));
    return map;
  }

  @Override
  protected String getTypeName() {
    return "exponential";
  }
}
