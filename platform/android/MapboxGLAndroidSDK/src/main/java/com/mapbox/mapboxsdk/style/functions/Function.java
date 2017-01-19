package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.style.functions.stops.ExponentialStops;
import com.mapbox.mapboxsdk.style.functions.stops.IdentityStops;
import com.mapbox.mapboxsdk.style.functions.stops.IntervalStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stop;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

import java.util.Map;

/**
 * Functions are used to change properties in relation to the state of the map.
 *
 * @param <I> the function's input type
 * @param <O> the target property's value type. Make sure it matches.
 * @see <a href="https://www.mapbox.com/mapbox-gl-style-spec/#types-function">The online documentation</a>
 */
public class Function<I, O> {

  /**
   * Zoom functions allow the appearance of a map feature to change with map’s zoom.
   * Zoom functions can be used to create the illusion of depth and control data density.
   * Each stop is an array with two elements, the first is a zoom and the second is a function output value.
   *
   * @param <Z>   the zoom level type (Float, Integer)
   * @param <O>   the property type
   * @param stops the stops implementation that define the function. @see {@link Stops#exponential(float, Stop[])}
   * @return the {@link CameraFunction}
   */
  public static <Z extends Number, O> CameraFunction<Z, O> zoom(@NonNull ExponentialStops<Z, O> stops) {
    return new CameraFunction<Z, O>(stops);
  }

  /**
   * Zoom functions allow the appearance of a map feature to change with map’s zoom.
   * Zoom functions can be used to create the illusion of depth and control data density.
   * Each stop is an array with two elements, the first is a zoom and the second is a function output value.
   *
   * @param <Z>   the zoom level type (Float, Integer)
   * @param <O>   the property type
   * @param stops the stops implementation that define the function. @see {@link Stops#interval(Stop[])}
   * @return the {@link CameraFunction}
   */
  public static <Z extends Number, O> CameraFunction<Z, O> zoom(@NonNull IntervalStops<Z, O> stops) {
    return new CameraFunction<Z, O>(stops);
  }

  public static <I, O> SourceFunction<I, O> property(@NonNull String property, @NonNull ExponentialStops<I, O> stops) {
    return new SourceFunction<>(property, stops);
  }

  public static <T> SourceFunction<T, T> property(@NonNull String property, @NonNull IdentityStops<T> stops) {
    return new SourceFunction<>(property, stops);
  }

  public static <I, O> SourceFunction<I, O> property(@NonNull String property, @NonNull IntervalStops<I, O> stops) {
    return new SourceFunction<>(property, stops);
  }

  //TODO: Categorical SourceFunction

  //TODO: Composite Functions

  private final Stops<I, O> stops;

  Function(@NonNull Stops stops) {
    this.stops = stops;
  }

  /**
   * @return the stops in this function
   */
  public Stops getStops() {
    return stops;
  }

  public Map<String, Object> toValueObject() {
    return stops.toValueObject();
  }
}
