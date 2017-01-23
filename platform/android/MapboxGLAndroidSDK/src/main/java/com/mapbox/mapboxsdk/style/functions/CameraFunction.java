package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.style.functions.stops.ExponentialStops;
import com.mapbox.mapboxsdk.style.functions.stops.IntervalStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stop;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

/**
 * Camera function. Functions that take camera properties as input (zoom for now)
 *
 * @param <I> the input type
 * @param <O> the output type
 */
public class CameraFunction<I extends Number, O> extends Function<I, O> {

  /**
   * Create an exponential camera function
   *
   * @param stops @see {@link com.mapbox.mapboxsdk.style.functions.stops.Stops#exponential(float, Stop[])}
   */
  CameraFunction(@NonNull ExponentialStops<I, O> stops) {
    super(stops);
  }

  /**
   * Create an interval camera function
   *
   * @param stops @see {@link com.mapbox.mapboxsdk.style.functions.stops.Stops#interval(Stop[])}
   */
  CameraFunction(@NonNull IntervalStops<I, O> stops) {
    super(stops);
  }

  /**
   * JNI constructor
   */
  @Keep
  private CameraFunction(Stops<I, O> stops) {
    super(stops);
  }
}
