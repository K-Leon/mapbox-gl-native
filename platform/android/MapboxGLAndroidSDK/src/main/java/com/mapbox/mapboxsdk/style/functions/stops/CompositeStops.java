package com.mapbox.mapboxsdk.style.functions.stops;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Size;

import com.mapbox.mapboxsdk.style.functions.stops.Stop.CompositeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @param <Z> the zoom type (usually Float)
 * @param <I> the input type (the feature property type)
 * @param <O> the output type (the property type)
 * @param <S> the {@link Stops} implementation (eg CategoricalStops, {@link ExponentialStops} or {@link IntervalStops})
 */
public class CompositeStops<Z extends Number, I, O, S extends IterableStops<I, O, Stop<I, O>>>
  extends IterableStops<CompositeValue<Z, I>, O, Map.Entry<Z, S>> {

  public static <Z extends Number, I, O> CompositeStops<Z, I, O, ? extends Stops<I, O>> convert(
    IntervalStops<CompositeValue<Z, I>, O> stops) {

    return new CompositeStops<>(stops);
  }

  public static <Z extends Number, I, O> CompositeStops<Z, I, O, ? extends Stops<I, O>> convert(
    ExponentialStops<CompositeValue<Z, I>, O> stops) {

    return new CompositeStops<>(stops);
  }

  private final Map<Z, S> stops;

  /**
   * JNI Constructor
   *
   * @param stops the stops {@link Map}
   */
  @Keep
  private CompositeStops(@NonNull @Size(min = 1) Map<Z, S> stops) {
    this.stops = stops;
  }

  private CompositeStops(@NonNull ExponentialStops<Stop.CompositeValue<Z, I>, O> stops) {
    this.stops = new HashMap<>();

    for (Map.Entry<Z, List<Stop<I, O>>> entry : collect(stops).entrySet()) {
      //noinspection unchecked
      this.stops.put(entry.getKey(), (S) new ExponentialStops<>(stops.base, entry.getValue().toArray(new Stop[0])));
    }
  }

  private CompositeStops(@NonNull IntervalStops<Stop.CompositeValue<Z, I>, O> stops) {
    this.stops = new HashMap<>();

    for (Map.Entry<Z, List<Stop<I, O>>> entry : collect(stops).entrySet()) {
      //noinspection unchecked
      this.stops.put(entry.getKey(), (S) new IntervalStops<>(entry.getValue().toArray(new Stop[0])));
    }
  }

  @Override
  protected String getTypeName() {
    return stops.get(0).getTypeName();
  }

  @Override
  public Map<String, Object> toValueObject() {
    Map<String, Object> map = super.toValueObject();

    //Flatten and convert stops
    //noinspection unchecked
    map.put("stops", convert(flatten(this.stops).toArray(new Stop[0])));

    return map;
  }

  @NonNull
  private List<Stop<CompositeValue<Z, I>, O>> flatten(Map<Z, S> stops) {
    List<Stop<CompositeValue<Z, I>, O>> flattenedStops = new ArrayList<>();
    for (Map.Entry<Z, S> entry : stops.entrySet()) {
      for (Stop<I, O> stop : entry.getValue()) {
        flattenedStops.add(new Stop<>(new CompositeValue<>(entry.getKey(), stop.in), stop.out));
      }
    }
    return flattenedStops;
  }

  @NonNull
  private Map<Z, List<Stop<I, O>>> collect(
    @NonNull IterableStops<CompositeValue<Z, I>, O, Stop<CompositeValue<Z, I>, O>> stops) {
    Map<Z, List<Stop<I, O>>> converted = new HashMap<>();

    for (Stop<CompositeValue<Z, I>, O> stop : stops) {
      List<Stop<I, O>> stopsForZ = converted.get(stop.in.zoom);
      if (stopsForZ == null) {
        stopsForZ = new ArrayList<>();
        converted.put(stop.in.zoom, stopsForZ);
      }

      stopsForZ.add(new Stop<>(stop.in.value, stop.out));
    }

    return converted;
  }

  @Override
  public Iterator<Map.Entry<Z, S>> iterator() {
    return stops.entrySet().iterator();
  }

  @Override
  public int size() {
    return stops.size();
  }
}
