package com.mapbox.mapboxsdk.style.functions.stops;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.util.Map;

/**
 * TODO
 */
public class IntervalStops<I, O> extends Stops<I, O> {

  public final Stop<I, O>[] stops;

  @SafeVarargs
  public IntervalStops(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    this.stops = stops;
  }

  @Override
  protected String getTypeName() {
    return "interval";
  }

  @Override
  public Map<String, Object> toValueObject() {
    Map<String, Object> map = super.toValueObject();
    map.put("stops", convert(stops));
    return map;
  }
}
