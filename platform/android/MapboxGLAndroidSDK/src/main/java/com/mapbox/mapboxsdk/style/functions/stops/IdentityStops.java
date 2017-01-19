package com.mapbox.mapboxsdk.style.functions.stops;

/**
 * TODO
 */
public class IdentityStops<T> extends Stops<T, T> {

  @Override
  protected String getTypeName() {
    return "identity";
  }
}
