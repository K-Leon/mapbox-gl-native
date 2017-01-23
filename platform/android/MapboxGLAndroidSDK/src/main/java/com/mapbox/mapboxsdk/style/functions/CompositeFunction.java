package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.style.functions.stops.CompositeStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

import java.util.Map;

/**
 * TODO
 *
 * @param <Z>
 * @param <I>
 * @param <O>
 */
public class CompositeFunction<Z extends Number, I, O> extends Function<I, O> {

  private final String property;

  CompositeFunction(@NonNull String property, @NonNull CompositeStops<Z, I, O, ? extends Stops<I, O>> stops) {
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
