package com.mapbox.mapboxsdk.style.functions.stops;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO
 */
public class IntervalStops<I, O> extends IterableStops<I, O, Stop<I, O>> {

  private final Stop<I, O>[] stops;

  @SafeVarargs
  public IntervalStops(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    this.stops = stops;
  }

  @Override
  public String getTypeName() {
    return "interval";
  }

  @Override
  public Map<String, Object> toValueObject() {
    Map<String, Object> map = super.toValueObject();
    map.put("stops", convert(stops));
    return map;
  }

  @Override
  public Iterator<Stop<I, O>> iterator() {
    return Arrays.asList(stops).iterator();
  }

  @Override
  public int size() {
    return stops.length;
  }
}
