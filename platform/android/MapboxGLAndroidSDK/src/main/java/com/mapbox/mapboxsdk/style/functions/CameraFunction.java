package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.style.functions.stops.ExponentialStops;
import com.mapbox.mapboxsdk.style.functions.stops.IntervalStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stop;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

/**
 * TODO
 */
public class CameraFunction<I extends Number, O> extends Function<I, O> {

  /**
   * TODO
   *
   * @param stops @see {@link com.mapbox.mapboxsdk.style.functions.stops.Stops#exponential(float, Stop[])}
   */
  CameraFunction(@NonNull ExponentialStops<I, O> stops) {
    super(stops);
  }

  /**
   * TODO
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
