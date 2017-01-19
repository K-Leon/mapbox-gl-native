package com.mapbox.mapboxsdk.style.functions.stops;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 */
public abstract class Stops<I, O> {

  @SafeVarargs
  public static <I, O> ExponentialStops<I, O> exponential(float base, @NonNull @Size(min = 1) Stop<I, O>... stops) {
    return new ExponentialStops<>(base, stops);
  }

  public static <T> IdentityStops<T> identity() {
    return new IdentityStops<>();
  }

  @SafeVarargs
  public static <I, O> IntervalStops interval(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    return new IntervalStops<>(stops);
  }

  public Map<String, Object> toValueObject() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("type", getTypeName());
    return map;
  }

  protected abstract String getTypeName();

  public <T> T as(Class<T> implementation) {
    return (T) this;
  }

  protected <I, O> Object[] convert(Stop<I, O>[] stops) {
    if (stops != null) {
      Object[] stopsValue = new Object[stops.length];

      for (int i = 0; i < stopsValue.length; i++) {
        Stop stop = stops[i];
        stopsValue[i] = stop.toValueObject();
      }
      return stopsValue;
    }

    return null;
  }
}
