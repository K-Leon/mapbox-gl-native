// This file is generated. Edit android/platform/scripts/generate-style-code.js, then run `make android-style-code`.
package com.mapbox.mapboxsdk.testapp.style;

import android.graphics.Color;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import timber.log.Timber;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.functions.Function;
import com.mapbox.mapboxsdk.style.functions.CameraFunction;
import com.mapbox.mapboxsdk.style.functions.SourceFunction;
import com.mapbox.mapboxsdk.style.functions.stops.ExponentialStops;
import com.mapbox.mapboxsdk.style.functions.stops.IdentityStops;
import com.mapbox.mapboxsdk.style.functions.stops.IntervalStops;
import com.mapbox.mapboxsdk.style.functions.stops.Stops;
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
import static com.mapbox.mapboxsdk.style.functions.stops.Stop.stop;
import static com.mapbox.mapboxsdk.style.functions.stops.Stops.*;
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
  public void testLineCapAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-cap");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineCap(
        zoom(
          interval(
            stop(2, lineCap(LINE_CAP_BUTT))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineCap());
    assertNotNull(layer.getLineCap().getFunction());
    assertEquals(CameraFunction.class, layer.getLineCap().getFunction().getClass());
    assertEquals(IntervalStops.class, layer.getLineCap().getFunction().getStops().getClass());
    assertEquals(1, ((IntervalStops) layer.getLineCap().getFunction().getStops()).stops.length);
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
  public void testLineJoinAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-join");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineJoin(
        zoom(
          interval(
            stop(2, lineJoin(LINE_JOIN_BEVEL))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineJoin());
    assertNotNull(layer.getLineJoin().getFunction());
    assertEquals(CameraFunction.class, layer.getLineJoin().getFunction().getClass());
    assertEquals(IntervalStops.class, layer.getLineJoin().getFunction().getStops().getClass());
    assertEquals(1, ((IntervalStops) layer.getLineJoin().getFunction().getStops()).stops.length);
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
  public void testLineMiterLimitAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-miter-limit");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineMiterLimit(
        zoom(
          exponential(
            0.5f,
            stop(2, lineMiterLimit(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineMiterLimit());
    assertNotNull(layer.getLineMiterLimit().getFunction());
    assertEquals(CameraFunction.class, layer.getLineMiterLimit().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineMiterLimit().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineMiterLimit().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineMiterLimit().getFunction().getStops()).stops.length);
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
  public void testLineRoundLimitAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-round-limit");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineRoundLimit(
        zoom(
          exponential(
            0.5f,
            stop(2, lineRoundLimit(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineRoundLimit());
    assertNotNull(layer.getLineRoundLimit().getFunction());
    assertEquals(CameraFunction.class, layer.getLineRoundLimit().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineRoundLimit().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineRoundLimit().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineRoundLimit().getFunction().getStops()).stops.length);
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
  public void testLineOpacityAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOpacity(
        zoom(
          exponential(
            0.5f,
            stop(2, lineOpacity(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineOpacity());
    assertNotNull(layer.getLineOpacity().getFunction());
    assertEquals(CameraFunction.class, layer.getLineOpacity().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineOpacity().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineOpacity().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineOpacity().getFunction().getStops()).stops.length);
  }

  @Test
  public void testLineOpacityAsIdentitySourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOpacity(property("FeaturePropertyA", Stops.<Float>identity()))
    );

    //Verify
    assertNotNull(layer.getLineOpacity());
    assertNotNull(layer.getLineOpacity().getFunction());
    assertEquals(SourceFunction.class, layer.getLineOpacity().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineOpacity().getFunction()).getProperty());
    assertEquals(IdentityStops.class, layer.getLineOpacity().getFunction().getStops().getClass());
  }

  @Test
  public void testLineOpacityAsExponentialSourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOpacity(
        property(
          "FeaturePropertyA",
          exponential(
            0.5f,
            stop(0.3f, lineOpacity(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineOpacity());
    assertNotNull(layer.getLineOpacity().getFunction());
    assertEquals(SourceFunction.class, layer.getLineOpacity().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineOpacity().getFunction()).getProperty());
    assertEquals(ExponentialStops.class, layer.getLineOpacity().getFunction().getStops().getClass());
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
  public void testLineColorAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineColor(
        zoom(
          exponential(
            0.5f,
            stop(2, lineColor("rgba(0, 0, 0, 1)"))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineColor());
    assertNotNull(layer.getLineColor().getFunction());
    assertEquals(CameraFunction.class, layer.getLineColor().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineColor().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineColor().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineColor().getFunction().getStops()).stops.length);
  }

  @Test
  public void testLineColorAsIdentitySourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineColor(property("FeaturePropertyA", Stops.<String>identity()))
    );

    //Verify
    assertNotNull(layer.getLineColor());
    assertNotNull(layer.getLineColor().getFunction());
    assertEquals(SourceFunction.class, layer.getLineColor().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineColor().getFunction()).getProperty());
    assertEquals(IdentityStops.class, layer.getLineColor().getFunction().getStops().getClass());
  }

  @Test
  public void testLineColorAsExponentialSourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineColor(
        property(
          "FeaturePropertyA",
          exponential(
            0.5f,
            stop(Color.RED, lineColor(Color.RED))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineColor());
    assertNotNull(layer.getLineColor().getFunction());
    assertEquals(SourceFunction.class, layer.getLineColor().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineColor().getFunction()).getProperty());
    assertEquals(ExponentialStops.class, layer.getLineColor().getFunction().getStops().getClass());
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
  public void testLineTranslateAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-translate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineTranslate(
        zoom(
          exponential(
            0.5f,
            stop(2, lineTranslate(new Float[]{0f,0f}))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineTranslate());
    assertNotNull(layer.getLineTranslate().getFunction());
    assertEquals(CameraFunction.class, layer.getLineTranslate().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineTranslate().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineTranslate().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineTranslate().getFunction().getStops()).stops.length);
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
  public void testLineTranslateAnchorAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-translate-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineTranslateAnchor(
        zoom(
          interval(
            stop(2, lineTranslateAnchor(LINE_TRANSLATE_ANCHOR_MAP))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineTranslateAnchor());
    assertNotNull(layer.getLineTranslateAnchor().getFunction());
    assertEquals(CameraFunction.class, layer.getLineTranslateAnchor().getFunction().getClass());
    assertEquals(IntervalStops.class, layer.getLineTranslateAnchor().getFunction().getStops().getClass());
    assertEquals(1, ((IntervalStops) layer.getLineTranslateAnchor().getFunction().getStops()).stops.length);
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
  public void testLineWidthAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineWidth(
        zoom(
          exponential(
            0.5f,
            stop(2, lineWidth(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineWidth());
    assertNotNull(layer.getLineWidth().getFunction());
    assertEquals(CameraFunction.class, layer.getLineWidth().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineWidth().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineWidth().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineWidth().getFunction().getStops()).stops.length);
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
  public void testLineGapWidthAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-gap-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineGapWidth(
        zoom(
          exponential(
            0.5f,
            stop(2, lineGapWidth(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineGapWidth());
    assertNotNull(layer.getLineGapWidth().getFunction());
    assertEquals(CameraFunction.class, layer.getLineGapWidth().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineGapWidth().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineGapWidth().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineGapWidth().getFunction().getStops()).stops.length);
  }

  @Test
  public void testLineGapWidthAsIdentitySourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-gap-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineGapWidth(property("FeaturePropertyA", Stops.<Float>identity()))
    );

    //Verify
    assertNotNull(layer.getLineGapWidth());
    assertNotNull(layer.getLineGapWidth().getFunction());
    assertEquals(SourceFunction.class, layer.getLineGapWidth().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineGapWidth().getFunction()).getProperty());
    assertEquals(IdentityStops.class, layer.getLineGapWidth().getFunction().getStops().getClass());
  }

  @Test
  public void testLineGapWidthAsExponentialSourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-gap-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineGapWidth(
        property(
          "FeaturePropertyA",
          exponential(
            0.5f,
            stop(0.3f, lineGapWidth(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineGapWidth());
    assertNotNull(layer.getLineGapWidth().getFunction());
    assertEquals(SourceFunction.class, layer.getLineGapWidth().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineGapWidth().getFunction()).getProperty());
    assertEquals(ExponentialStops.class, layer.getLineGapWidth().getFunction().getStops().getClass());
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
  public void testLineOffsetAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOffset(
        zoom(
          exponential(
            0.5f,
            stop(2, lineOffset(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineOffset());
    assertNotNull(layer.getLineOffset().getFunction());
    assertEquals(CameraFunction.class, layer.getLineOffset().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineOffset().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineOffset().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineOffset().getFunction().getStops()).stops.length);
  }

  @Test
  public void testLineOffsetAsIdentitySourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOffset(property("FeaturePropertyA", Stops.<Float>identity()))
    );

    //Verify
    assertNotNull(layer.getLineOffset());
    assertNotNull(layer.getLineOffset().getFunction());
    assertEquals(SourceFunction.class, layer.getLineOffset().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineOffset().getFunction()).getProperty());
    assertEquals(IdentityStops.class, layer.getLineOffset().getFunction().getStops().getClass());
  }

  @Test
  public void testLineOffsetAsExponentialSourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineOffset(
        property(
          "FeaturePropertyA",
          exponential(
            0.5f,
            stop(0.3f, lineOffset(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineOffset());
    assertNotNull(layer.getLineOffset().getFunction());
    assertEquals(SourceFunction.class, layer.getLineOffset().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineOffset().getFunction()).getProperty());
    assertEquals(ExponentialStops.class, layer.getLineOffset().getFunction().getStops().getClass());
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
  public void testLineBlurAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineBlur(
        zoom(
          exponential(
            0.5f,
            stop(2, lineBlur(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineBlur());
    assertNotNull(layer.getLineBlur().getFunction());
    assertEquals(CameraFunction.class, layer.getLineBlur().getFunction().getClass());
    assertEquals(ExponentialStops.class, layer.getLineBlur().getFunction().getStops().getClass());
    assertEquals(0.5f, ((ExponentialStops) layer.getLineBlur().getFunction().getStops()).getBase(), 0.001);
    assertEquals(1, ((ExponentialStops) layer.getLineBlur().getFunction().getStops()).stops.length);
  }

  @Test
  public void testLineBlurAsIdentitySourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineBlur(property("FeaturePropertyA", Stops.<Float>identity()))
    );

    //Verify
    assertNotNull(layer.getLineBlur());
    assertNotNull(layer.getLineBlur().getFunction());
    assertEquals(SourceFunction.class, layer.getLineBlur().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineBlur().getFunction()).getProperty());
    assertEquals(IdentityStops.class, layer.getLineBlur().getFunction().getStops().getClass());
  }

  @Test
  public void testLineBlurAsExponentialSourceFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineBlur(
        property(
          "FeaturePropertyA",
          exponential(
            0.5f,
            stop(0.3f, lineBlur(0.3f))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineBlur());
    assertNotNull(layer.getLineBlur().getFunction());
    assertEquals(SourceFunction.class, layer.getLineBlur().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((SourceFunction) layer.getLineBlur().getFunction()).getProperty());
    assertEquals(ExponentialStops.class, layer.getLineBlur().getFunction().getStops().getClass());
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
  public void testLineDasharrayAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-dasharray");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      lineDasharray(
        zoom(
          interval(
            stop(2, lineDasharray(new Float[]{}))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLineDasharray());
    assertNotNull(layer.getLineDasharray().getFunction());
    assertEquals(CameraFunction.class, layer.getLineDasharray().getFunction().getClass());
    assertEquals(IntervalStops.class, layer.getLineDasharray().getFunction().getStops().getClass());
    assertEquals(1, ((IntervalStops) layer.getLineDasharray().getFunction().getStops()).stops.length);
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
  public void testLinePatternAsCameraFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("line-pattern");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      linePattern(
        zoom(
          interval(
            stop(2, linePattern("pedestrian-polygon"))
          )
        )
      )
    );

    //Verify
    assertNotNull(layer.getLinePattern());
    assertNotNull(layer.getLinePattern().getFunction());
    assertEquals(CameraFunction.class, layer.getLinePattern().getFunction().getClass());
    assertEquals(IntervalStops.class, layer.getLinePattern().getFunction().getStops().getClass());
    assertEquals(1, ((IntervalStops) layer.getLinePattern().getFunction().getStops()).stops.length);
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
