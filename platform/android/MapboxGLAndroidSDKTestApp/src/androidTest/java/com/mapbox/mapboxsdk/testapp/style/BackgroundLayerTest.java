// This file is generated. Edit android/platform/scripts/generate-style-code.js, then run `make android-style-code`.
package com.mapbox.mapboxsdk.testapp.style;

import android.graphics.Color;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import timber.log.Timber;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.functions.ZoomFunction;
import com.mapbox.mapboxsdk.style.layers.BackgroundLayer;
import com.mapbox.mapboxsdk.testapp.R;
import com.mapbox.mapboxsdk.testapp.activity.style.RuntimeStyleTestActivity;
import com.mapbox.mapboxsdk.testapp.utils.OnMapReadyIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mapbox.mapboxsdk.style.functions.Function.*;
import static org.junit.Assert.*;
import static com.mapbox.mapboxsdk.style.layers.Property.*;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.*;

/**
 * Basic smoke tests for BackgroundLayer
 */
@RunWith(AndroidJUnit4.class)
public class BackgroundLayerTest extends BaseStyleTest {

  @Rule
  public final ActivityTestRule<RuntimeStyleTestActivity> rule = new ActivityTestRule<>(RuntimeStyleTestActivity.class);

  private BackgroundLayer layer;

  private OnMapReadyIdlingResource idlingResource;

  private MapboxMap mapboxMap;

  @Before
  public void setup() {
    idlingResource = new OnMapReadyIdlingResource(rule.getActivity());
    Espresso.registerIdlingResources(idlingResource);
    mapboxMap = rule.getActivity().getMapboxMap();

    Timber.i("Retrieving layer");
    layer = mapboxMap.getLayerAs("background");
  }

  @Test
  public void testSetVisibility() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("Visibility");
    assertNotNull(layer);

    //Get initial
    assertEquals(layer.getVisibility().getValue(), VISIBLE);

    //Set
    layer.setProperties(visibility(NONE));
    assertEquals(layer.getVisibility().getValue(), NONE);
  }

  @Test
  public void testBackgroundColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(backgroundColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getBackgroundColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testBackgroundColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      backgroundColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, backgroundColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getBackgroundColor());
    assertNotNull(layer.getBackgroundColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getBackgroundColor().getFunction().getClass());
    assertEquals(1, layer.getBackgroundColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getBackgroundColor().getFunction().getBase());
  }

  @Test
  public void testBackgroundColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(backgroundColor(Color.RED));
    assertEquals(layer.getBackgroundColorAsInt(), Color.RED);
  }

  @Test
  public void testBackgroundPatternAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-pattern");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(backgroundPattern("pedestrian-polygon"));
    assertEquals((String) layer.getBackgroundPattern().getValue(), (String) "pedestrian-polygon");
  }

  @Test
  public void testBackgroundPatternAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-pattern");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      backgroundPattern(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, backgroundPattern("pedestrian-polygon"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getBackgroundPattern());
    assertNotNull(layer.getBackgroundPattern().getFunction());
    assertEquals(ZoomFunction.class, layer.getBackgroundPattern().getFunction().getClass());
    assertEquals(1, layer.getBackgroundPattern().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getBackgroundPattern().getFunction().getType());
  }

  @Test
  public void testBackgroundOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(backgroundOpacity(0.3f));
    assertEquals((Float) layer.getBackgroundOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testBackgroundOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("background-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      backgroundOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, backgroundOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getBackgroundOpacity());
    assertNotNull(layer.getBackgroundOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getBackgroundOpacity().getFunction().getClass());
    assertEquals(1, layer.getBackgroundOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getBackgroundOpacity().getFunction().getBase());
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
