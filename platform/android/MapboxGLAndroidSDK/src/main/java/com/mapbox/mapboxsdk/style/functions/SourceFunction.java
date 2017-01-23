package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.style.functions.stops.Stops;

import java.util.Map;

/**
 * Source functions take Feature property names as input
 *
 * @param <V> the input type
 * @param <T> the output type
 */
public class SourceFunction<V, T> extends Function<V, T> {

  private final String property;

  SourceFunction(@NonNull String property, @NonNull Stops<V, T> stops) {
    super(stops);
    this.property = property;
  }

  /**
   * INTERNAL USAGE ONLY
   *
   * @return The feature property name
   */
  public String getProperty() {
    return property;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, Object> toValueObject() {
    Map<String, Object> valueObject = super.toValueObject();
    valueObject.put("property", property);
    return valueObject;
  }
}
