package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import com.mapbox.mapboxsdk.style.functions.stops.ExponentialStops;
import com.mapbox.mapboxsdk.style.functions.stops.IntervalStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

import java.util.Map;

/**
 * TODO
 */
public class SourceFunction<V, T> extends Function<V, T> {

  private final String property;

  SourceFunction(@NonNull String property, @NonNull Stops<V, T> stops) {
    super(stops);
    this.property = property;
  }

  public String getProperty() {
    return property;
  }

  @Override
  public Map<String, Object> toValueObject() {
    Map<String, Object> valueObject = super.toValueObject();
    valueObject.put("property", property);
    return valueObject;
  }
}
