package com.mapbox.mapboxsdk.style.functions;

/**
 * A stop represents a certain point in the range of this function
 *
 * @param <I> input
 * @param <O> output
 */
public class Stop<I, O> {
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