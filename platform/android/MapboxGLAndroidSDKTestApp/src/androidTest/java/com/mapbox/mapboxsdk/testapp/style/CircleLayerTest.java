// This file is generated. Edit android/platform/scripts/generate-style-code.js, then run `make android-style-code`.
package com.mapbox.mapboxsdk.testapp.style;

import android.graphics.Color;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import timber.log.Timber;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.functions.ZoomFunction;
import com.mapbox.mapboxsdk.style.layers.CircleLayer;
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
 * Basic smoke tests for CircleLayer
 */
@RunWith(AndroidJUnit4.class)
public class CircleLayerTest extends BaseStyleTest {

  @Rule
  public final ActivityTestRule<RuntimeStyleTestActivity> rule = new ActivityTestRule<>(RuntimeStyleTestActivity.class);

  private CircleLayer layer;

  private OnMapReadyIdlingResource idlingResource;

  private MapboxMap mapboxMap;

  @Before
  public void setup() {
    idlingResource = new OnMapReadyIdlingResource(rule.getActivity());
    Espresso.registerIdlingResources(idlingResource);
    mapboxMap = rule.getActivity().getMapboxMap();

    if ((layer = mapboxMap.getLayerAs("my-layer")) == null) {
      Timber.i("Adding layer");
      layer = new CircleLayer("my-layer", "composite");
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
  public void testCircleRadiusAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-radius");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleRadius(0.3f));
    assertEquals((Float) layer.getCircleRadius().getValue(), (Float) 0.3f);
  }

  @Test
  public void testCircleRadiusAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-radius");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleRadius(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleRadius(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleRadius());
    assertNotNull(layer.getCircleRadius().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleRadius().getFunction().getClass());
    assertEquals(1, layer.getCircleRadius().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleRadius().getFunction().getBase());
  }

  @Test
  public void testCircleColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getCircleColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testCircleColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleColor());
    assertNotNull(layer.getCircleColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleColor().getFunction().getClass());
    assertEquals(1, layer.getCircleColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleColor().getFunction().getBase());
  }

  @Test
  public void testCircleColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleColor(Color.RED));
    assertEquals(layer.getCircleColorAsInt(), Color.RED);
  }

  @Test
  public void testCircleBlurAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-blur");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleBlur(0.3f));
    assertEquals((Float) layer.getCircleBlur().getValue(), (Float) 0.3f);
  }

  @Test
  public void testCircleBlurAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleBlur(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleBlur(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleBlur());
    assertNotNull(layer.getCircleBlur().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleBlur().getFunction().getClass());
    assertEquals(1, layer.getCircleBlur().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleBlur().getFunction().getBase());
  }

  @Test
  public void testCircleOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleOpacity(0.3f));
    assertEquals((Float) layer.getCircleOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testCircleOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleOpacity());
    assertNotNull(layer.getCircleOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleOpacity().getFunction().getClass());
    assertEquals(1, layer.getCircleOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleOpacity().getFunction().getBase());
  }

  @Test
  public void testCircleTranslateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-translate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleTranslate(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getCircleTranslate().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testCircleTranslateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-translate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleTranslate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleTranslate(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleTranslate());
    assertNotNull(layer.getCircleTranslate().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleTranslate().getFunction().getClass());
    assertEquals(1, layer.getCircleTranslate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleTranslate().getFunction().getBase());
  }

  @Test
  public void testCircleTranslateAnchorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-translate-anchor");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleTranslateAnchor(CIRCLE_TRANSLATE_ANCHOR_MAP));
    assertEquals((String) layer.getCircleTranslateAnchor().getValue(), (String) CIRCLE_TRANSLATE_ANCHOR_MAP);
  }

  @Test
  public void testCircleTranslateAnchorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-translate-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleTranslateAnchor(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, circleTranslateAnchor(CIRCLE_TRANSLATE_ANCHOR_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleTranslateAnchor());
    assertNotNull(layer.getCircleTranslateAnchor().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleTranslateAnchor().getFunction().getClass());
    assertEquals(1, layer.getCircleTranslateAnchor().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getCircleTranslateAnchor().getFunction().getType());
  }

  @Test
  public void testCirclePitchScaleAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-pitch-scale");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circlePitchScale(CIRCLE_PITCH_SCALE_MAP));
    assertEquals((String) layer.getCirclePitchScale().getValue(), (String) CIRCLE_PITCH_SCALE_MAP);
  }

  @Test
  public void testCirclePitchScaleAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-pitch-scale");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circlePitchScale(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, circlePitchScale(CIRCLE_PITCH_SCALE_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCirclePitchScale());
    assertNotNull(layer.getCirclePitchScale().getFunction());
    assertEquals(ZoomFunction.class, layer.getCirclePitchScale().getFunction().getClass());
    assertEquals(1, layer.getCirclePitchScale().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getCirclePitchScale().getFunction().getType());
  }

  @Test
  public void testCircleStrokeWidthAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-width");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleStrokeWidth(0.3f));
    assertEquals((Float) layer.getCircleStrokeWidth().getValue(), (Float) 0.3f);
  }

  @Test
  public void testCircleStrokeWidthAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleStrokeWidth(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleStrokeWidth(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleStrokeWidth());
    assertNotNull(layer.getCircleStrokeWidth().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleStrokeWidth().getFunction().getClass());
    assertEquals(1, layer.getCircleStrokeWidth().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleStrokeWidth().getFunction().getBase());
  }

  @Test
  public void testCircleStrokeColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleStrokeColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getCircleStrokeColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testCircleStrokeColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleStrokeColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleStrokeColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleStrokeColor());
    assertNotNull(layer.getCircleStrokeColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleStrokeColor().getFunction().getClass());
    assertEquals(1, layer.getCircleStrokeColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleStrokeColor().getFunction().getBase());
  }

  @Test
  public void testCircleStrokeColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleStrokeColor(Color.RED));
    assertEquals(layer.getCircleStrokeColorAsInt(), Color.RED);
  }

  @Test
  public void testCircleStrokeOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(circleStrokeOpacity(0.3f));
    assertEquals((Float) layer.getCircleStrokeOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testCircleStrokeOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("circle-stroke-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      circleStrokeOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, circleStrokeOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getCircleStrokeOpacity());
    assertNotNull(layer.getCircleStrokeOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getCircleStrokeOpacity().getFunction().getClass());
    assertEquals(1, layer.getCircleStrokeOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getCircleStrokeOpacity().getFunction().getBase());
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
