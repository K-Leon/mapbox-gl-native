// This file is generated. Edit android/platform/scripts/generate-style-code.js, then run `make android-style-code`.
package com.mapbox.mapboxsdk.testapp.style;

import android.graphics.Color;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import timber.log.Timber;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.functions.ZoomFunction;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
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
 * Basic smoke tests for FillLayer
 */
@RunWith(AndroidJUnit4.class)
public class FillLayerTest extends BaseStyleTest {

  @Rule
  public final ActivityTestRule<RuntimeStyleTestActivity> rule = new ActivityTestRule<>(RuntimeStyleTestActivity.class);

  private FillLayer layer;

  private OnMapReadyIdlingResource idlingResource;

  private MapboxMap mapboxMap;

  @Before
  public void setup() {
    idlingResource = new OnMapReadyIdlingResource(rule.getActivity());
    Espresso.registerIdlingResources(idlingResource);
    mapboxMap = rule.getActivity().getMapboxMap();

    if ((layer = mapboxMap.getLayerAs("my-layer")) == null) {
      Timber.i("Adding layer");
      layer = new FillLayer("my-layer", "composite");
      layer.setSourceLayer("composite");
      mapboxMap.addLayer(layer);
      //Layer reference is now stale, get new reference
      layer = mapboxMap.getLayerAs("my-layer");
    }
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
  public void testFillAntialiasAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-antialias");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillAntialias(true));
    assertEquals((Boolean) layer.getFillAntialias().getValue(), (Boolean) true);
  }

  @Test
  public void testFillAntialiasAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-antialias");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillAntialias(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, fillAntialias(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillAntialias());
    assertNotNull(layer.getFillAntialias().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillAntialias().getFunction().getClass());
    assertEquals(1, layer.getFillAntialias().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getFillAntialias().getFunction().getType());
  }

  @Test
  public void testFillOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillOpacity(0.3f));
    assertEquals((Float) layer.getFillOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testFillOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, fillOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillOpacity());
    assertNotNull(layer.getFillOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillOpacity().getFunction().getClass());
    assertEquals(1, layer.getFillOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getFillOpacity().getFunction().getBase());
  }

  @Test
  public void testFillColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getFillColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testFillColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, fillColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillColor());
    assertNotNull(layer.getFillColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillColor().getFunction().getClass());
    assertEquals(1, layer.getFillColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getFillColor().getFunction().getBase());
  }

  @Test
  public void testFillColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillColor(Color.RED));
    assertEquals(layer.getFillColorAsInt(), Color.RED);
  }

  @Test
  public void testFillOutlineColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-outline-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillOutlineColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getFillOutlineColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testFillOutlineColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-outline-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillOutlineColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, fillOutlineColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillOutlineColor());
    assertNotNull(layer.getFillOutlineColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillOutlineColor().getFunction().getClass());
    assertEquals(1, layer.getFillOutlineColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getFillOutlineColor().getFunction().getBase());
  }

  @Test
  public void testFillOutlineColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-outline-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillOutlineColor(Color.RED));
    assertEquals(layer.getFillOutlineColorAsInt(), Color.RED);
  }

  @Test
  public void testFillTranslateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-translate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillTranslate(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getFillTranslate().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testFillTranslateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-translate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillTranslate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, fillTranslate(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillTranslate());
    assertNotNull(layer.getFillTranslate().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillTranslate().getFunction().getClass());
    assertEquals(1, layer.getFillTranslate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getFillTranslate().getFunction().getBase());
  }

  @Test
  public void testFillTranslateAnchorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-translate-anchor");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillTranslateAnchor(FILL_TRANSLATE_ANCHOR_MAP));
    assertEquals((String) layer.getFillTranslateAnchor().getValue(), (String) FILL_TRANSLATE_ANCHOR_MAP);
  }

  @Test
  public void testFillTranslateAnchorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-translate-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillTranslateAnchor(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, fillTranslateAnchor(FILL_TRANSLATE_ANCHOR_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillTranslateAnchor());
    assertNotNull(layer.getFillTranslateAnchor().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillTranslateAnchor().getFunction().getClass());
    assertEquals(1, layer.getFillTranslateAnchor().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getFillTranslateAnchor().getFunction().getType());
  }

  @Test
  public void testFillPatternAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-pattern");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(fillPattern("pedestrian-polygon"));
    assertEquals((String) layer.getFillPattern().getValue(), (String) "pedestrian-polygon");
  }

  @Test
  public void testFillPatternAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("fill-pattern");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      fillPattern(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, fillPattern("pedestrian-polygon"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getFillPattern());
    assertNotNull(layer.getFillPattern().getFunction());
    assertEquals(ZoomFunction.class, layer.getFillPattern().getFunction().getClass());
    assertEquals(1, layer.getFillPattern().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getFillPattern().getFunction().getType());
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
