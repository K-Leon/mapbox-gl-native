package com.mapbox.mapboxsdk.style.functions.stops;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.util.HashMap;
import java.util.Map;

/**
 * The base class for different stops implementations
 *
 * @param <I> the input type
 * @param <O> the output type
 */
public abstract class Stops<I, O> {

  /**
   * Convenience method for use in function declarations
   *
   * @param stops the collections of discrete stops
   * @param <I>   the Stops input type
   * @param <O>   the Stops output type
   * @return the {@link Stops} implementation
   */
  @SafeVarargs
  public static <I, O> CategoricalStops<I, O> categorical(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    return new CategoricalStops<>(stops);
  }

  /**
   * Convenience method for use in function declarations
   *
   * @param base  the exponential base
   * @param stops the collections of discrete stops
   * @param <I>   the Stops input type
   * @param <O>   the Stops output type
   * @return the {@link Stops} implementation
   */
  @SafeVarargs
  public static <I, O> ExponentialStops<I, O> exponential(float base, @NonNull @Size(min = 1) Stop<I, O>... stops) {
    return new ExponentialStops<>(base, stops);
  }

  /**
   * Convenience method for use in function declarations
   *
   * @param stops the collections of discrete stops
   * @param <I>   the Stops input type
   * @param <O>   the Stops output type
   * @return the {@link Stops} implementation
   */
  @SafeVarargs
  public static <I, O> ExponentialStops<I, O> exponential(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    return new ExponentialStops<>(stops);
  }

  /**
   * Convenience method for use in function declarations
   *
   * @param <T> the Stops input/output type
   * @return the {@link IdentityStops} implementation
   */
  public static <T> IdentityStops<T> identity() {
    return new IdentityStops<>();
  }

  /**
   * Convenience method for use in function declarations
   *
   * @param stops the collections of discrete stops
   * @param <I>   the Stops input type
   * @param <O>   the Stops output type
   * @return the {@link Stops} implementation
   */
  @SafeVarargs
  public static <I extends Number, O> IntervalStops interval(@NonNull @Size(min = 1) Stop<I, O>... stops) {
    return new IntervalStops<>(stops);
  }

  /**
   * INTERNAL USAGE ONLY
   *
   * @return the value object representation for conversion to core
   */
  public Map<String, Object> toValueObject() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("type", getTypeName());
    return map;
  }

  /**
   * INTERNAL USAGE ONLY
   *
   * @return the unique type name as a string according to the style specification
   */
  protected abstract String getTypeName();

}
