package com.mapbox.mapboxsdk.style.functions.stops;

import com.mapbox.mapboxsdk.style.functions.Function;
import com.mapbox.mapboxsdk.style.layers.Property;

import java.util.HashMap;
import java.util.Map;

/**
 * A stop represents a certain point in the range of this function
 *
 * @param <I> input the stop (function) input type
 * @param <O> output the stop (function) output type
 */
public class Stop<I, O> {
  /**
   * Creates a {@link Stop} to use in a {@link Function}
   *
   * @param in     the input for the stop
   * @param output the output for the stop
   * @param <T>    the output property type
   * @return the {@link Stop}
   */
  public static <I, T> Stop<I, T> stop(I in, Property<T> output) {
    return new Stop<>(in, output.value);
  }

  /**
   * Create a composite {@link Stop} for use in a {@link com.mapbox.mapboxsdk.style.functions.CompositeFunction}
   *
   * @param zoom   the zoom input
   * @param value  the feature property input
   * @param output the output for the stop
   * @param <Z>    the zoom type
   * @param <I>    the feature property input type
   * @param <O>    the output property type
   * @return the {@link Stop}
   * @see Function#composite(String, ExponentialStops)
   */
  public static <Z extends Number, I, O> Stop<Stop.CompositeValue<Z, I>, O> stop(Z zoom, I value, Property<O> output) {
    return new Stop<>(new Stop.CompositeValue<>(zoom, value), output.value);
  }

  /**
   * Represents a composite input value for composite functions (eg zoom and feature property value)
   *
   * @param <Z> the zoom input type (typically Float)
   * @param <V> the feature property input type
   */
  public static class CompositeValue<Z extends Number, V> {
    Z zoom;
    V value;

    CompositeValue(Z zoom, V value) {
      this.zoom = zoom;
      this.value = value;
    }

    Map<String, Object> toValueObject() {
      HashMap<String, Object> map = new HashMap<>();
      map.put("zoom", zoom);
      map.put("value", value);
      return map;
    }

    @Override
    public String toString() {
      return String.format("[zoom: %s, value: %s]", zoom, value);
    }
  }

  public final I in;
  public final O out;

  Stop(I in, O out) {
    this.in = in;
    this.out = out;
  }

  /**
   * @return an array representation of the Stop
   */
  Object[] toValueObject() {
    return new Object[] {in instanceof CompositeValue ? ((CompositeValue) in).toValueObject() : in, out};
  }

  @Override
  public String toString() {
    return String.format("[%s, %s]", in, out);
  }
}