package com.mapbox.mapboxsdk.style.functions.stops;

import com.mapbox.mapboxsdk.style.functions.Function;
import com.mapbox.mapboxsdk.style.layers.Property;

/**
 * A stop represents a certain point in the range of this function
 *
 * @param <I> input
 * @param <O> output
 */
public class Stop<I, O> {
  /**
   * Creates a stop to use in a {@link Function}
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
   * TODO
   *
   * @param zoom
   * @param value
   * @param output
   * @param <V>
   * @param <T>
   * @return
   */
  public static <V, T> Stop<Stop.CompositeValue<V>, T> stop(Number zoom, V value, Property<T> output) {
    return new Stop<>(new Stop.CompositeValue<>(zoom, value), output.value);
  }

  public static class CompositeValue<V> {
    Number zoom;
    V value;

    CompositeValue(Number zoom, V value) {
      this.zoom = zoom;
      this.value = value;
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
    return new Object[] {in, out};
  }

  @Override
  public String toString() {
    return String.format("[%s, %s]", in, out);
  }
}