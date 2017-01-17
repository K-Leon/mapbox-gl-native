package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

/**
 * TODO
 */
public class ZoomFunction<V extends Number, T> extends Function<V, T> {

  ZoomFunction(@NonNull @Size(min = 1) Stop<V, T>[] stops) {
    super(stops);
  }

  @SafeVarargs
  ZoomFunction(float base, Stop<V, T>... stops) {
    super(base, stops);
  }

  @SafeVarargs
  ZoomFunction(String type, Stop<V, T>... stops) {
    this(type, null, stops);
  }

  private ZoomFunction(String type, Float base, Stop<V, T>[] stops) {
    super(type, base, stops);
    if (type != null && !(type.equals(EXPONENTIAL) || type.equals(INTERVAL))) {
      //TODO exception
    }
  }


}
