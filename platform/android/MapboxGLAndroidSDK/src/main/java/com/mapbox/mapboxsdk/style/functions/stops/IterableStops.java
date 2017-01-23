package com.mapbox.mapboxsdk.style.functions.stops;

/**
 * TODO
 */
public abstract class IterableStops<I, O, S> extends Stops<I, O> implements Iterable<S> {

  public abstract int size();

}
