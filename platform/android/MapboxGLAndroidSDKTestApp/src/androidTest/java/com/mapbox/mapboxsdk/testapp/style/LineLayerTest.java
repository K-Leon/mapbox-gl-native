// This file is generated. Edit android/platform/scripts/generate-style-code.js, then run `make android-style-code`.
package com.mapbox.mapboxsdk.testapp.style;

import android.graphics.Color;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import timber.log.Timber;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.functions.Function;
import com.mapbox.mapboxsdk.style.functions.ZoomFunction;
import com.mapbox.mapboxsdk.style.functions.PropertyFunction;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
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
 * Basic smoke tests for LineLayer
 */
@RunWith(AndroidJUnit4.class)
public class LineLayerTest extends BaseStyleTest {

  @Rule
  public final ActivityTestRule<RuntimeStyleTestActivity> rule = new ActivityTestRule<>(RuntimeStyleTestActivity.class);

  private LineLayer layer;

  private OnMapReadyIdlingResource idlingResource;

  private MapboxMap mapboxMap;

  @Before
  public void setup() {
    idlingResource = new OnMapReadyIdlingResource(rule.getActivity());
    Espresso.registerIdlingResources(idlingResource);
    mapboxMap = rule.getActivity().getMapboxMap();

    if ((layer = mapboxMap.getLayerAs("my-layer")) == null) {
      Timber.i("Adding layer");
      layer = new LineLayer("my-layer", "composite");
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
  public void testLineCapAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-cap");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineCap(LINE_CAP_BUTT));
    assertEquals((String) layer.getLineCap().getValue(), (String) LINE_CAP_BUTT);
  }

  @Test
  public void testLineCapAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-cap");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineCap(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, lineCap(LINE_CAP_BUTT))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineCap());
    assertNotNull(layer.getLineCap().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineCap().getFunction().getClass());
    assertEquals(1, layer.getLineCap().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getLineCap().getFunction().getType());
  }

  @Test
  public void testLineJoinAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-join");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineJoin(LINE_JOIN_BEVEL));
    assertEquals((String) layer.getLineJoin().getValue(), (String) LINE_JOIN_BEVEL);
  }

  @Test
  public void testLineJoinAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-join");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineJoin(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, lineJoin(LINE_JOIN_BEVEL))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineJoin());
    assertNotNull(layer.getLineJoin().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineJoin().getFunction().getClass());
    assertEquals(1, layer.getLineJoin().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getLineJoin().getFunction().getType());
  }

  @Test
  public void testLineMiterLimitAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-miter-limit");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineMiterLimit(0.3f));
    assertEquals((Float) layer.getLineMiterLimit().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineMiterLimitAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-miter-limit");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineMiterLimit(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineMiterLimit(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineMiterLimit());
    assertNotNull(layer.getLineMiterLimit().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineMiterLimit().getFunction().getClass());
    assertEquals(1, layer.getLineMiterLimit().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineMiterLimit().getFunction().getBase());
  }

  @Test
  public void testLineRoundLimitAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-round-limit");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineRoundLimit(0.3f));
    assertEquals((Float) layer.getLineRoundLimit().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineRoundLimitAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-round-limit");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineRoundLimit(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineRoundLimit(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineRoundLimit());
    assertNotNull(layer.getLineRoundLimit().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineRoundLimit().getFunction().getClass());
    assertEquals(1, layer.getLineRoundLimit().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineRoundLimit().getFunction().getBase());
  }

  @Test
  public void testLineOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineOpacity(0.3f));
    assertEquals((Float) layer.getLineOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineOpacity());
    assertNotNull(layer.getLineOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineOpacity().getFunction().getClass());
    assertEquals(1, layer.getLineOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineOpacity().getFunction().getBase());
  }

  @Test
  public void testLineOpacityAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOpacity(Function.<Float>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getLineOpacity());
    assertNotNull(layer.getLineOpacity().getFunction());
    assertEquals(PropertyFunction.class, layer.getLineOpacity().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getLineOpacity().getFunction()).getProperty());
    assertNull(layer.getLineOpacity().getFunction().getStops());
  }

  @Test
  public void testLineColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getLineColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testLineColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineColor());
    assertNotNull(layer.getLineColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineColor().getFunction().getClass());
    assertEquals(1, layer.getLineColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineColor().getFunction().getBase());
  }

  @Test
  public void testLineColorAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineColor(Function.<String>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getLineColor());
    assertNotNull(layer.getLineColor().getFunction());
    assertEquals(PropertyFunction.class, layer.getLineColor().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getLineColor().getFunction()).getProperty());
    assertNull(layer.getLineColor().getFunction().getStops());
  }

  @Test
  public void testLineColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineColor(Color.RED));
    assertEquals(layer.getLineColorAsInt(), Color.RED);
  }

  @Test
  public void testLineTranslateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-translate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineTranslate(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getLineTranslate().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testLineTranslateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-translate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineTranslate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineTranslate(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineTranslate());
    assertNotNull(layer.getLineTranslate().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineTranslate().getFunction().getClass());
    assertEquals(1, layer.getLineTranslate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineTranslate().getFunction().getBase());
  }

  @Test
  public void testLineTranslateAnchorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-translate-anchor");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineTranslateAnchor(LINE_TRANSLATE_ANCHOR_MAP));
    assertEquals((String) layer.getLineTranslateAnchor().getValue(), (String) LINE_TRANSLATE_ANCHOR_MAP);
  }

  @Test
  public void testLineTranslateAnchorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-translate-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineTranslateAnchor(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, lineTranslateAnchor(LINE_TRANSLATE_ANCHOR_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineTranslateAnchor());
    assertNotNull(layer.getLineTranslateAnchor().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineTranslateAnchor().getFunction().getClass());
    assertEquals(1, layer.getLineTranslateAnchor().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getLineTranslateAnchor().getFunction().getType());
  }

  @Test
  public void testLineWidthAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-width");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineWidth(0.3f));
    assertEquals((Float) layer.getLineWidth().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineWidthAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineWidth(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineWidth(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineWidth());
    assertNotNull(layer.getLineWidth().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineWidth().getFunction().getClass());
    assertEquals(1, layer.getLineWidth().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineWidth().getFunction().getBase());
  }

  @Test
  public void testLineGapWidthAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-gap-width");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineGapWidth(0.3f));
    assertEquals((Float) layer.getLineGapWidth().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineGapWidthAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-gap-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineGapWidth(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineGapWidth(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineGapWidth());
    assertNotNull(layer.getLineGapWidth().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineGapWidth().getFunction().getClass());
    assertEquals(1, layer.getLineGapWidth().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineGapWidth().getFunction().getBase());
  }

  @Test
  public void testLineGapWidthAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-gap-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineGapWidth(Function.<Float>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getLineGapWidth());
    assertNotNull(layer.getLineGapWidth().getFunction());
    assertEquals(PropertyFunction.class, layer.getLineGapWidth().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getLineGapWidth().getFunction()).getProperty());
    assertNull(layer.getLineGapWidth().getFunction().getStops());
  }

  @Test
  public void testLineOffsetAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-offset");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineOffset(0.3f));
    assertEquals((Float) layer.getLineOffset().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineOffsetAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOffset(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineOffset(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineOffset());
    assertNotNull(layer.getLineOffset().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineOffset().getFunction().getClass());
    assertEquals(1, layer.getLineOffset().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineOffset().getFunction().getBase());
  }

  @Test
  public void testLineOffsetAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOffset(Function.<Float>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getLineOffset());
    assertNotNull(layer.getLineOffset().getFunction());
    assertEquals(PropertyFunction.class, layer.getLineOffset().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getLineOffset().getFunction()).getProperty());
    assertNull(layer.getLineOffset().getFunction().getStops());
  }

  @Test
  public void testLineBlurAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-blur");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineBlur(0.3f));
    assertEquals((Float) layer.getLineBlur().getValue(), (Float) 0.3f);
  }

  @Test
  public void testLineBlurAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineBlur(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, lineBlur(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineBlur());
    assertNotNull(layer.getLineBlur().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineBlur().getFunction().getClass());
    assertEquals(1, layer.getLineBlur().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getLineBlur().getFunction().getBase());
  }

  @Test
  public void testLineBlurAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineBlur(Function.<Float>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getLineBlur());
    assertNotNull(layer.getLineBlur().getFunction());
    assertEquals(PropertyFunction.class, layer.getLineBlur().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getLineBlur().getFunction()).getProperty());
    assertNull(layer.getLineBlur().getFunction().getStops());
  }

  @Test
  public void testLineDasharrayAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-dasharray");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(lineDasharray(new Float[]{}));
    assertEquals((Float[]) layer.getLineDasharray().getValue(), (Float[]) new Float[]{});
  }

  @Test
  public void testLineDasharrayAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-dasharray");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineDasharray(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, lineDasharray(new Float[]{}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineDasharray());
    assertNotNull(layer.getLineDasharray().getFunction());
    assertEquals(ZoomFunction.class, layer.getLineDasharray().getFunction().getClass());
    assertEquals(1, layer.getLineDasharray().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getLineDasharray().getFunction().getType());
  }

  @Test
  public void testLinePatternAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-pattern");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(linePattern("pedestrian-polygon"));
    assertEquals((String) layer.getLinePattern().getValue(), (String) "pedestrian-polygon");
  }

  @Test
  public void testLinePatternAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-pattern");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      linePattern(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, linePattern("pedestrian-polygon"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getLinePattern());
    assertNotNull(layer.getLinePattern().getFunction());
    assertEquals(ZoomFunction.class, layer.getLinePattern().getFunction().getClass());
    assertEquals(1, layer.getLinePattern().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getLinePattern().getFunction().getType());
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
