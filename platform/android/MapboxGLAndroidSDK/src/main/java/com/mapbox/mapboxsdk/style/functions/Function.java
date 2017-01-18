package com.mapbox.mapboxsdk.style.functions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringDef;

import com.mapbox.mapboxsdk.style.layers.Property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Functions are used to change properties in relation to the state of the map.
 * <p>
 * Currently, only zoom functions are supported.
 * </p>
 *
 * @param <T> the target property's value type. Make sure it matches.
 * @see <a href="https://www.mapbox.com/mapbox-gl-style-spec/#types-function">The online documentation</a>
 */
public class Function<V, T> {

  public static final String CATEGORICAL = "categorical";
  public static final String EXPONENTIAL = "exponential";
  public static final String IDENTITY = "identity";
  public static final String INTERVAL = "interval";

  @StringDef( {
    CATEGORICAL,
    EXPONENTIAL,
    IDENTITY,
    INTERVAL
  })
  @Retention(RetentionPolicy.SOURCE)
  public @interface TYPE {
  }

  /**
   * Creates a stop to use in a {@link Function}
   *
   * @param in     the input for the stop
   * @param output the output for the stop
   * @param <T>    the output property type
   * @return the {@link Stop}
   */
  public static <T> Stop<Float, T> stop(float in, Property<T> output) {
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


  /**
   * Zoom functions allow the appearance of a map feature to change with map’s zoom.
   * Zoom functions can be used to create the illusion of depth and control data density.
   * Each stop is an array with two elements, the first is a zoom and the second is a function output value.
   *
   * @param <ZOOM> the zoom level type
   * @param <T>    the property type
   * @param stops  the stops that define the function
   * @return the {@link ZoomFunction}
   */
  @SafeVarargs
  public static <ZOOM extends Number, T> ZoomFunction<ZOOM, T> zoom(@NonNull @Size(min = 1) Stop<ZOOM, T>... stops) {
    return new ZoomFunction<>(stops);
  }

  /**
   * Zoom functions allow the appearance of a map feature to change with map’s zoom.
   * Zoom functions can be used to create the illusion of depth and control data density.
   * Each stop is an array with two elements, the first is a zoom and the second is a function output value.
   *
   * @param <ZOOM> the zoom level type
   * @param <T>    the property type
   * @param base   the exponential base of the interpolation curve - Default 1
   * @param stops  the stops that define the function
   * @return the {@link ZoomFunction}
   */
  @SafeVarargs
  public static <ZOOM extends Number, T> ZoomFunction<ZOOM, T> zoom(
    //TODO: FloatRange?
    float base,
    @NonNull @Size(min = 1) Stop<ZOOM, T>... stops) {
    return new ZoomFunction<ZOOM, T>(base, stops);
  }

  @SafeVarargs
  public static <ZOOM extends Number, T> ZoomFunction<ZOOM, T> zoom(
    @TYPE String type,
    @NonNull @Size(min = 1) Stop<ZOOM, T>... stops) {
    return new ZoomFunction<>(type, stops);
  }

  public static <T> PropertyFunction<T, T> property(@NonNull String property) {
    return new PropertyFunction<>(property);
  }

  @SafeVarargs
  public static <V, T> PropertyFunction<V, T> property(
    @NonNull String property,
    @NonNull @Size(min = 1) Stop<V, T>... stops) {
    return new PropertyFunction<>(property, stops);
  }

  @SafeVarargs
  public static <V, T> PropertyFunction<V, T> property(
    @NonNull String property,
    //TODO: FloatRange?
    float base,
    @NonNull @Size(min = 1) Stop<V, T>... stops) {
    return new PropertyFunction<>(property, base, stops);
  }

  @SafeVarargs
  public static <V, T> PropertyFunction<V, T> property(
    @NonNull String property,
    //TODO: FloatRange?
    @TYPE String type,
    @NonNull @Size(min = 1) Stop<V, T>... stops) {
    return new PropertyFunction<>(property, type, stops);
  }

  @SafeVarargs
  public static <V, T> Function<V, T> zoomAndProperty(
    @NonNull String property,
    @NonNull @Size(min = 1) Stop<Stop.CompositeValue<V>, T>... stops) {
    //TODO
    return null;
  }

  @SafeVarargs
  public static <V, T> Function<V, T> zoomAndProperty(
    @NonNull String property,
    //TODO: FloatRange?
    float base,
    @NonNull @Size(min = 1) Stop<Stop.CompositeValue<V>, T>... stops) {
    //TODO
    return null;
  }

  @SafeVarargs
  public static <V, T> Function<V, T> zoomAndProperty(
    @NonNull String property,
    //TODO: FloatRange?
    @TYPE String type,
    @NonNull @Size(min = 1) Stop<Stop.CompositeValue<V>, T>... stops) {
    //TODO
    return null;
  }

  private final Stop<V, T>[] stops;
  private final String type;
  private final Float base;

  Function(@NonNull @Size(min = 1) Stop<V, T>[] stops) {
    this(null, stops);
  }

  Function(float base, @NonNull @Size(min = 1) Stop<V, T>[] stops) {
    this.stops = stops;
    this.base = base;
    this.type = EXPONENTIAL;
  }

  Function(@TYPE String type, @NonNull @Size(min = 1) Stop<V, T>[] stops) {
    this(type, null, stops);
  }

  protected Function(String type, Float base, Stop<V, T>[] stops) {
    this.type = type;
    this.base = base;
    this.stops = stops;
  }

  /**
   * @return the base
   */
  @Nullable
  public Float getBase() {
    return base;
  }

  /**
   * @return the stops in this function
   */
  public Stop<V, T>[] getStops() {
    return stops;
  }


  /**
   * @return return the {@link TYPE} String
   */
  @TYPE
  public String getType() {
    return type;
  }

  public Map<String, Object> toValueObject() {
    Map<String, Object> value = new HashMap<>();

    if (stops != null) {
      Object[] stopsValue = new Object[stops.length];

      for (int i = 0; i < stopsValue.length; i++) {
        Stop stop = stops[i];
        stopsValue[i] = stop.toValueObject();
      }

      value.put("stops", stopsValue);
    }

    if (type != null) {
      value.put("type", type);
    }

    if (base != null) {
      value.put("base", base);
    }

    return value;
  }
}
