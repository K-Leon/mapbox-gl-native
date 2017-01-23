package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mapbox.mapboxsdk.style.functions.stops.CategoricalStops;
import com.mapbox.mapboxsdk.style.functions.stops.CompositeStops;
import com.mapbox.mapboxsdk.style.functions.stops.ExponentialStops;
import com.mapbox.mapboxsdk.style.functions.stops.IdentityStops;
import com.mapbox.mapboxsdk.style.functions.stops.IntervalStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stop;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;

import java.util.Map;

import timber.log.Timber;

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
   * @see Stops#exponential(float, Stop[])
   * @see Stops#exponential(Stop[])
   * @see Stop#stop
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
   * @see Stops#interval(Stop[])
   * @see Stop#stop
   */
  public static <Z extends Number, O> CameraFunction<Z, O> zoom(@NonNull IntervalStops<Z, O> stops) {
    return new CameraFunction<Z, O>(stops);
  }

  /**
   * Create an exponential {@link SourceFunction]}
   *
   * @param property the feature property name
   * @param stops    the stops
   * @param <I>      the function input type
   * @param <O>      the function output type
   * @return the {@link SourceFunction}
   * @see Stops#exponential(float, Stop[])
   * @see Stop#stop
   */
  public static <I, O> SourceFunction<I, O> property(@NonNull String property, @NonNull ExponentialStops<I, O> stops) {
    return new SourceFunction<>(property, stops);
  }

  /**
   * Create an identity {@link SourceFunction]}
   *
   * @param property the feature property name
   * @param stops    the stops
   * @param <T>      the function input/output type
   * @return the {@link SourceFunction}
   * @see Stops#identity()
   * @see Stop#stop
   */
  public static <T> SourceFunction<T, T> property(@NonNull String property, @NonNull IdentityStops<T> stops) {
    return new SourceFunction<>(property, stops);
  }

  /**
   * Create an interval {@link SourceFunction]}
   *
   * @param property the feature property name
   * @param stops    the stops
   * @param <I>      the function input type
   * @param <O>      the function output type
   * @return the {@link SourceFunction}
   * @see Stops#interval(Stop[])
   * @see Stop#stop
   */
  public static <I, O> SourceFunction<I, O> property(@NonNull String property, @NonNull IntervalStops<I, O> stops) {
    return new SourceFunction<>(property, stops);
  }

  /**
   * Create an categorical {@link SourceFunction]}
   *
   * @param property the feature property name
   * @param stops    the stops
   * @param <I>      the function input type
   * @param <O>      the function output type
   * @return the {@link SourceFunction}
   * @see Stops#categorical(Stop[])
   * @see Stop#stop
   */
  public static <I, O> SourceFunction<I, O> property(@NonNull String property, @NonNull CategoricalStops<I, O> stops) {
    return new SourceFunction<>(property, stops);
  }

  /**
   * Create a composite, categorical function.
   *
   * @param property the feature property name for the source part of the function
   * @param stops    the stops
   * @param <Z>      the zoom function input type (Float usually)
   * @param <I>      the function input type for the source part of the function
   * @param <O>      the function output type
   * @return the {@link CompositeFunction}
   * @see Stops#categorical(Stop[])
   * @see Stop#stop
   */
  public static <Z extends Number, I, O> CompositeFunction<Z, I, O> composite(
    @NonNull String property,
    @NonNull CategoricalStops<Stop.CompositeValue<Z, I>, O> stops) {

    return new CompositeFunction<>(property, new CompositeStops<>(stops));
  }

  /**
   * Create a composite, exponential function.
   *
   * @param property the feature property name for the source part of the function
   * @param stops    the stops
   * @param <Z>      the zoom function input type (Float usually)
   * @param <I>      the function input type for the source part of the function
   * @param <O>      the function output type
   * @return the {@link CompositeFunction}
   * @see Stops#exponential(float, Stop[])
   * @see Stop#stop
   */
  public static <Z extends Number, I, O> CompositeFunction<Z, I, O> composite(
    @NonNull String property,
    @NonNull ExponentialStops<Stop.CompositeValue<Z, I>, O> stops) {

    return new CompositeFunction<>(property, new CompositeStops<>(stops));
  }

  /**
   * Create a composite, interval function.
   *
   * @param property the feature property name for the source part of the function
   * @param stops    the stops
   * @param <Z>      the zoom function input type (Float usually)
   * @param <I>      the function input type for the source part of the function
   * @param <O>      the function output type
   * @return the {@link CompositeFunction}
   * @see Stops#interval(Stop[])
   * @see Stop#stop
   */
  public static <Z extends Number, I, O> CompositeFunction<Z, I, O> composite(
    @NonNull String property,
    @NonNull IntervalStops<Stop.CompositeValue<Z, I>, O> stops) {

    return new CompositeFunction<>(property, new CompositeStops<>(stops));
  }

  // Class definition //

  private final Stops<I, O> stops;

  /**
   * JNI Cosntructor for implementation classes
   *
   * @param stops the stops
   */
  Function(@NonNull Stops stops) {
    this.stops = stops;
  }

  /**
   * @return the stops in this function
   */
  public Stops getStops() {
    return stops;
  }

  /**
   * Convenience method
   *
   * @param <S> the Stops implementation type
   * @return the Stops implementation or null when the wrong type is specified
   */
  @Nullable
  public <S extends Stops> S getStopsAs() {
    try {
      //noinspection unchecked
      return (S) stops;
    } catch (ClassCastException exception) {
      Timber.e(String.format("Stops: %s is a different type: %s", stops.getClass(), exception));
      return null;
    }
  }

  /**
   * INTERNAL USAGE ONLY
   *
   * @return a value object representation for core conversion
   */
  public Map<String, Object> toValueObject() {
    return stops.toValueObject();
  }
}
