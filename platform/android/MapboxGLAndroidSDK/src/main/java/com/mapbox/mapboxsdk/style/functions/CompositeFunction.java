package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.style.functions.stops.CompositeStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

import java.util.Map;

/**
 * Composite functions combine {@link android.graphics.Camera} and {@link SourceFunction}s
 *
 * @param <Z> the zoom type (usually Float)
 * @param <I> the input type (the feature property type)
 * @param <O> the output type (the property type)
 */
public class CompositeFunction<Z extends Number, I, O> extends Function<I, O> {

  private final String property;

  CompositeFunction(@NonNull String property,
                    @NonNull CompositeStops<Z, I, O, ? extends Stops<I, O>> stops) {
    super(stops);
    this.property = property;
  }

  /**
   * INTERNAL USAGE ONLY
   *
   * @return the feature property name
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
