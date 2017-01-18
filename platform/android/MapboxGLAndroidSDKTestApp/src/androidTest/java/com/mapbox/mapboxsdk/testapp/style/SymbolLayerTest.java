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
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
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
 * Basic smoke tests for SymbolLayer
 */
@RunWith(AndroidJUnit4.class)
public class SymbolLayerTest extends BaseStyleTest {

  @Rule
  public final ActivityTestRule<RuntimeStyleTestActivity> rule = new ActivityTestRule<>(RuntimeStyleTestActivity.class);

  private SymbolLayer layer;

  private OnMapReadyIdlingResource idlingResource;

  private MapboxMap mapboxMap;

  @Before
  public void setup() {
    idlingResource = new OnMapReadyIdlingResource(rule.getActivity());
    Espresso.registerIdlingResources(idlingResource);
    mapboxMap = rule.getActivity().getMapboxMap();

    if ((layer = mapboxMap.getLayerAs("my-layer")) == null) {
      Timber.i("Adding layer");
      layer = new SymbolLayer("my-layer", "composite");
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
  public void testSymbolPlacementAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("symbol-placement");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(symbolPlacement(SYMBOL_PLACEMENT_POINT));
    assertEquals((String) layer.getSymbolPlacement().getValue(), (String) SYMBOL_PLACEMENT_POINT);
  }

  @Test
  public void testSymbolPlacementAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("symbol-placement");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      symbolPlacement(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, symbolPlacement(SYMBOL_PLACEMENT_POINT))
        )
      )
    );

    //Verify
    assertNotNull(layer.getSymbolPlacement());
    assertNotNull(layer.getSymbolPlacement().getFunction());
    assertEquals(ZoomFunction.class, layer.getSymbolPlacement().getFunction().getClass());
    assertEquals(1, layer.getSymbolPlacement().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getSymbolPlacement().getFunction().getType());
  }

  @Test
  public void testSymbolSpacingAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("symbol-spacing");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(symbolSpacing(0.3f));
    assertEquals((Float) layer.getSymbolSpacing().getValue(), (Float) 0.3f);
  }

  @Test
  public void testSymbolSpacingAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("symbol-spacing");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      symbolSpacing(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, symbolSpacing(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getSymbolSpacing());
    assertNotNull(layer.getSymbolSpacing().getFunction());
    assertEquals(ZoomFunction.class, layer.getSymbolSpacing().getFunction().getClass());
    assertEquals(1, layer.getSymbolSpacing().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getSymbolSpacing().getFunction().getBase());
  }

  @Test
  public void testSymbolAvoidEdgesAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("symbol-avoid-edges");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(symbolAvoidEdges(true));
    assertEquals((Boolean) layer.getSymbolAvoidEdges().getValue(), (Boolean) true);
  }

  @Test
  public void testSymbolAvoidEdgesAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("symbol-avoid-edges");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      symbolAvoidEdges(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, symbolAvoidEdges(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getSymbolAvoidEdges());
    assertNotNull(layer.getSymbolAvoidEdges().getFunction());
    assertEquals(ZoomFunction.class, layer.getSymbolAvoidEdges().getFunction().getClass());
    assertEquals(1, layer.getSymbolAvoidEdges().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getSymbolAvoidEdges().getFunction().getType());
  }

  @Test
  public void testIconAllowOverlapAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-allow-overlap");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconAllowOverlap(true));
    assertEquals((Boolean) layer.getIconAllowOverlap().getValue(), (Boolean) true);
  }

  @Test
  public void testIconAllowOverlapAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-allow-overlap");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconAllowOverlap(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconAllowOverlap(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconAllowOverlap());
    assertNotNull(layer.getIconAllowOverlap().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconAllowOverlap().getFunction().getClass());
    assertEquals(1, layer.getIconAllowOverlap().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconAllowOverlap().getFunction().getType());
  }

  @Test
  public void testIconIgnorePlacementAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-ignore-placement");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconIgnorePlacement(true));
    assertEquals((Boolean) layer.getIconIgnorePlacement().getValue(), (Boolean) true);
  }

  @Test
  public void testIconIgnorePlacementAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-ignore-placement");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconIgnorePlacement(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconIgnorePlacement(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconIgnorePlacement());
    assertNotNull(layer.getIconIgnorePlacement().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconIgnorePlacement().getFunction().getClass());
    assertEquals(1, layer.getIconIgnorePlacement().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconIgnorePlacement().getFunction().getType());
  }

  @Test
  public void testIconOptionalAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-optional");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconOptional(true));
    assertEquals((Boolean) layer.getIconOptional().getValue(), (Boolean) true);
  }

  @Test
  public void testIconOptionalAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-optional");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconOptional(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconOptional(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconOptional());
    assertNotNull(layer.getIconOptional().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconOptional().getFunction().getClass());
    assertEquals(1, layer.getIconOptional().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconOptional().getFunction().getType());
  }

  @Test
  public void testIconRotationAlignmentAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-rotation-alignment");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconRotationAlignment(ICON_ROTATION_ALIGNMENT_MAP));
    assertEquals((String) layer.getIconRotationAlignment().getValue(), (String) ICON_ROTATION_ALIGNMENT_MAP);
  }

  @Test
  public void testIconRotationAlignmentAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-rotation-alignment");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconRotationAlignment(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconRotationAlignment(ICON_ROTATION_ALIGNMENT_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconRotationAlignment());
    assertNotNull(layer.getIconRotationAlignment().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconRotationAlignment().getFunction().getClass());
    assertEquals(1, layer.getIconRotationAlignment().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconRotationAlignment().getFunction().getType());
  }

  @Test
  public void testIconSizeAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-size");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconSize(0.3f));
    assertEquals((Float) layer.getIconSize().getValue(), (Float) 0.3f);
  }

  @Test
  public void testIconSizeAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-size");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconSize(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconSize(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconSize());
    assertNotNull(layer.getIconSize().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconSize().getFunction().getClass());
    assertEquals(1, layer.getIconSize().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconSize().getFunction().getBase());
  }

  @Test
  public void testIconTextFitAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-text-fit");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconTextFit(ICON_TEXT_FIT_NONE));
    assertEquals((String) layer.getIconTextFit().getValue(), (String) ICON_TEXT_FIT_NONE);
  }

  @Test
  public void testIconTextFitAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-text-fit");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconTextFit(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconTextFit(ICON_TEXT_FIT_NONE))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconTextFit());
    assertNotNull(layer.getIconTextFit().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconTextFit().getFunction().getClass());
    assertEquals(1, layer.getIconTextFit().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconTextFit().getFunction().getType());
  }

  @Test
  public void testIconTextFitPaddingAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-text-fit-padding");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconTextFitPadding(new Float[]{0f,0f,0f,0f}));
    assertEquals((Float[]) layer.getIconTextFitPadding().getValue(), (Float[]) new Float[]{0f,0f,0f,0f});
  }

  @Test
  public void testIconTextFitPaddingAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-text-fit-padding");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconTextFitPadding(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconTextFitPadding(new Float[]{0f,0f,0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconTextFitPadding());
    assertNotNull(layer.getIconTextFitPadding().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconTextFitPadding().getFunction().getClass());
    assertEquals(1, layer.getIconTextFitPadding().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconTextFitPadding().getFunction().getBase());
  }

  @Test
  public void testIconImageAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-image");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconImage("undefined"));
    assertEquals((String) layer.getIconImage().getValue(), (String) "undefined");
  }

  @Test
  public void testIconImageAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-image");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconImage(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconImage("undefined"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconImage());
    assertNotNull(layer.getIconImage().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconImage().getFunction().getClass());
    assertEquals(1, layer.getIconImage().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconImage().getFunction().getType());
  }

  @Test
  public void testIconRotateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-rotate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconRotate(0.3f));
    assertEquals((Float) layer.getIconRotate().getValue(), (Float) 0.3f);
  }

  @Test
  public void testIconRotateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-rotate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconRotate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconRotate(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconRotate());
    assertNotNull(layer.getIconRotate().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconRotate().getFunction().getClass());
    assertEquals(1, layer.getIconRotate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconRotate().getFunction().getBase());
  }

  @Test
  public void testIconRotateAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-rotate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconRotate(Function.<Float>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getIconRotate());
    assertNotNull(layer.getIconRotate().getFunction());
    assertEquals(PropertyFunction.class, layer.getIconRotate().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getIconRotate().getFunction()).getProperty());
    assertNull(layer.getIconRotate().getFunction().getStops());
  }

  @Test
  public void testIconPaddingAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-padding");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconPadding(0.3f));
    assertEquals((Float) layer.getIconPadding().getValue(), (Float) 0.3f);
  }

  @Test
  public void testIconPaddingAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-padding");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconPadding(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconPadding(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconPadding());
    assertNotNull(layer.getIconPadding().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconPadding().getFunction().getClass());
    assertEquals(1, layer.getIconPadding().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconPadding().getFunction().getBase());
  }

  @Test
  public void testIconKeepUprightAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-keep-upright");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconKeepUpright(true));
    assertEquals((Boolean) layer.getIconKeepUpright().getValue(), (Boolean) true);
  }

  @Test
  public void testIconKeepUprightAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-keep-upright");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconKeepUpright(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconKeepUpright(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconKeepUpright());
    assertNotNull(layer.getIconKeepUpright().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconKeepUpright().getFunction().getClass());
    assertEquals(1, layer.getIconKeepUpright().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconKeepUpright().getFunction().getType());
  }

  @Test
  public void testIconOffsetAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-offset");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconOffset(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getIconOffset().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testIconOffsetAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconOffset(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconOffset(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconOffset());
    assertNotNull(layer.getIconOffset().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconOffset().getFunction().getClass());
    assertEquals(1, layer.getIconOffset().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconOffset().getFunction().getBase());
  }

  @Test
  public void testIconOffsetAsIdentityPropertyFunction() {
    //Supports property function: true - true
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconOffset(Function.<Float[]>property("FeaturePropertyA"))
    );

    //Verify
    assertNotNull(layer.getIconOffset());
    assertNotNull(layer.getIconOffset().getFunction());
    assertEquals(PropertyFunction.class, layer.getIconOffset().getFunction().getClass());
    assertEquals("FeaturePropertyA", ((PropertyFunction) layer.getIconOffset().getFunction()).getProperty());
    assertNull(layer.getIconOffset().getFunction().getStops());
  }

  @Test
  public void testTextPitchAlignmentAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-pitch-alignment");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textPitchAlignment(TEXT_PITCH_ALIGNMENT_MAP));
    assertEquals((String) layer.getTextPitchAlignment().getValue(), (String) TEXT_PITCH_ALIGNMENT_MAP);
  }

  @Test
  public void testTextPitchAlignmentAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-pitch-alignment");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textPitchAlignment(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textPitchAlignment(TEXT_PITCH_ALIGNMENT_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextPitchAlignment());
    assertNotNull(layer.getTextPitchAlignment().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextPitchAlignment().getFunction().getClass());
    assertEquals(1, layer.getTextPitchAlignment().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextPitchAlignment().getFunction().getType());
  }

  @Test
  public void testTextRotationAlignmentAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-rotation-alignment");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textRotationAlignment(TEXT_ROTATION_ALIGNMENT_MAP));
    assertEquals((String) layer.getTextRotationAlignment().getValue(), (String) TEXT_ROTATION_ALIGNMENT_MAP);
  }

  @Test
  public void testTextRotationAlignmentAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-rotation-alignment");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textRotationAlignment(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textRotationAlignment(TEXT_ROTATION_ALIGNMENT_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextRotationAlignment());
    assertNotNull(layer.getTextRotationAlignment().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextRotationAlignment().getFunction().getClass());
    assertEquals(1, layer.getTextRotationAlignment().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextRotationAlignment().getFunction().getType());
  }

  @Test
  public void testTextFieldAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-field");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textField(""));
    assertEquals((String) layer.getTextField().getValue(), (String) "");
  }

  @Test
  public void testTextFieldAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-field");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textField(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textField(""))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextField());
    assertNotNull(layer.getTextField().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextField().getFunction().getClass());
    assertEquals(1, layer.getTextField().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextField().getFunction().getType());
  }

  @Test
  public void testTextFontAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-font");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textFont(new String[]{"Open Sans Regular", "Arial Unicode MS Regular"}));
    assertEquals((String[]) layer.getTextFont().getValue(), (String[]) new String[]{"Open Sans Regular", "Arial Unicode MS Regular"});
  }

  @Test
  public void testTextFontAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-font");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textFont(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textFont(new String[]{"Open Sans Regular", "Arial Unicode MS Regular"}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextFont());
    assertNotNull(layer.getTextFont().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextFont().getFunction().getClass());
    assertEquals(1, layer.getTextFont().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextFont().getFunction().getType());
  }

  @Test
  public void testTextSizeAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-size");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textSize(0.3f));
    assertEquals((Float) layer.getTextSize().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextSizeAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-size");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textSize(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textSize(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextSize());
    assertNotNull(layer.getTextSize().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextSize().getFunction().getClass());
    assertEquals(1, layer.getTextSize().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextSize().getFunction().getBase());
  }

  @Test
  public void testTextMaxWidthAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-max-width");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textMaxWidth(0.3f));
    assertEquals((Float) layer.getTextMaxWidth().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextMaxWidthAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-max-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textMaxWidth(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textMaxWidth(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextMaxWidth());
    assertNotNull(layer.getTextMaxWidth().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextMaxWidth().getFunction().getClass());
    assertEquals(1, layer.getTextMaxWidth().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextMaxWidth().getFunction().getBase());
  }

  @Test
  public void testTextLineHeightAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-line-height");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textLineHeight(0.3f));
    assertEquals((Float) layer.getTextLineHeight().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextLineHeightAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-line-height");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textLineHeight(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textLineHeight(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextLineHeight());
    assertNotNull(layer.getTextLineHeight().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextLineHeight().getFunction().getClass());
    assertEquals(1, layer.getTextLineHeight().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextLineHeight().getFunction().getBase());
  }

  @Test
  public void testTextLetterSpacingAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-letter-spacing");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textLetterSpacing(0.3f));
    assertEquals((Float) layer.getTextLetterSpacing().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextLetterSpacingAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-letter-spacing");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textLetterSpacing(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textLetterSpacing(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextLetterSpacing());
    assertNotNull(layer.getTextLetterSpacing().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextLetterSpacing().getFunction().getClass());
    assertEquals(1, layer.getTextLetterSpacing().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextLetterSpacing().getFunction().getBase());
  }

  @Test
  public void testTextJustifyAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-justify");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textJustify(TEXT_JUSTIFY_LEFT));
    assertEquals((String) layer.getTextJustify().getValue(), (String) TEXT_JUSTIFY_LEFT);
  }

  @Test
  public void testTextJustifyAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-justify");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textJustify(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textJustify(TEXT_JUSTIFY_LEFT))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextJustify());
    assertNotNull(layer.getTextJustify().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextJustify().getFunction().getClass());
    assertEquals(1, layer.getTextJustify().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextJustify().getFunction().getType());
  }

  @Test
  public void testTextAnchorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-anchor");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textAnchor(TEXT_ANCHOR_CENTER));
    assertEquals((String) layer.getTextAnchor().getValue(), (String) TEXT_ANCHOR_CENTER);
  }

  @Test
  public void testTextAnchorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textAnchor(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textAnchor(TEXT_ANCHOR_CENTER))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextAnchor());
    assertNotNull(layer.getTextAnchor().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextAnchor().getFunction().getClass());
    assertEquals(1, layer.getTextAnchor().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextAnchor().getFunction().getType());
  }

  @Test
  public void testTextMaxAngleAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-max-angle");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textMaxAngle(0.3f));
    assertEquals((Float) layer.getTextMaxAngle().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextMaxAngleAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-max-angle");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textMaxAngle(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textMaxAngle(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextMaxAngle());
    assertNotNull(layer.getTextMaxAngle().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextMaxAngle().getFunction().getClass());
    assertEquals(1, layer.getTextMaxAngle().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextMaxAngle().getFunction().getBase());
  }

  @Test
  public void testTextRotateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-rotate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textRotate(0.3f));
    assertEquals((Float) layer.getTextRotate().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextRotateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-rotate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textRotate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textRotate(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextRotate());
    assertNotNull(layer.getTextRotate().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextRotate().getFunction().getClass());
    assertEquals(1, layer.getTextRotate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextRotate().getFunction().getBase());
  }

  @Test
  public void testTextPaddingAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-padding");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textPadding(0.3f));
    assertEquals((Float) layer.getTextPadding().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextPaddingAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-padding");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textPadding(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textPadding(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextPadding());
    assertNotNull(layer.getTextPadding().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextPadding().getFunction().getClass());
    assertEquals(1, layer.getTextPadding().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextPadding().getFunction().getBase());
  }

  @Test
  public void testTextKeepUprightAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-keep-upright");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textKeepUpright(true));
    assertEquals((Boolean) layer.getTextKeepUpright().getValue(), (Boolean) true);
  }

  @Test
  public void testTextKeepUprightAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-keep-upright");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textKeepUpright(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textKeepUpright(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextKeepUpright());
    assertNotNull(layer.getTextKeepUpright().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextKeepUpright().getFunction().getClass());
    assertEquals(1, layer.getTextKeepUpright().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextKeepUpright().getFunction().getType());
  }

  @Test
  public void testTextTransformAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-transform");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textTransform(TEXT_TRANSFORM_NONE));
    assertEquals((String) layer.getTextTransform().getValue(), (String) TEXT_TRANSFORM_NONE);
  }

  @Test
  public void testTextTransformAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-transform");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textTransform(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textTransform(TEXT_TRANSFORM_NONE))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextTransform());
    assertNotNull(layer.getTextTransform().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextTransform().getFunction().getClass());
    assertEquals(1, layer.getTextTransform().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextTransform().getFunction().getType());
  }

  @Test
  public void testTextOffsetAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-offset");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textOffset(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getTextOffset().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testTextOffsetAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-offset");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textOffset(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textOffset(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextOffset());
    assertNotNull(layer.getTextOffset().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextOffset().getFunction().getClass());
    assertEquals(1, layer.getTextOffset().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextOffset().getFunction().getBase());
  }

  @Test
  public void testTextAllowOverlapAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-allow-overlap");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textAllowOverlap(true));
    assertEquals((Boolean) layer.getTextAllowOverlap().getValue(), (Boolean) true);
  }

  @Test
  public void testTextAllowOverlapAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-allow-overlap");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textAllowOverlap(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textAllowOverlap(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextAllowOverlap());
    assertNotNull(layer.getTextAllowOverlap().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextAllowOverlap().getFunction().getClass());
    assertEquals(1, layer.getTextAllowOverlap().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextAllowOverlap().getFunction().getType());
  }

  @Test
  public void testTextIgnorePlacementAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-ignore-placement");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textIgnorePlacement(true));
    assertEquals((Boolean) layer.getTextIgnorePlacement().getValue(), (Boolean) true);
  }

  @Test
  public void testTextIgnorePlacementAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-ignore-placement");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textIgnorePlacement(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textIgnorePlacement(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextIgnorePlacement());
    assertNotNull(layer.getTextIgnorePlacement().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextIgnorePlacement().getFunction().getClass());
    assertEquals(1, layer.getTextIgnorePlacement().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextIgnorePlacement().getFunction().getType());
  }

  @Test
  public void testTextOptionalAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-optional");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textOptional(true));
    assertEquals((Boolean) layer.getTextOptional().getValue(), (Boolean) true);
  }

  @Test
  public void testTextOptionalAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-optional");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textOptional(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textOptional(true))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextOptional());
    assertNotNull(layer.getTextOptional().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextOptional().getFunction().getClass());
    assertEquals(1, layer.getTextOptional().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextOptional().getFunction().getType());
  }

  @Test
  public void testIconOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconOpacity(0.3f));
    assertEquals((Float) layer.getIconOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testIconOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconOpacity());
    assertNotNull(layer.getIconOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconOpacity().getFunction().getClass());
    assertEquals(1, layer.getIconOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconOpacity().getFunction().getBase());
  }

  @Test
  public void testIconColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getIconColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testIconColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconColor());
    assertNotNull(layer.getIconColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconColor().getFunction().getClass());
    assertEquals(1, layer.getIconColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconColor().getFunction().getBase());
  }

  @Test
  public void testIconColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconColor(Color.RED));
    assertEquals(layer.getIconColorAsInt(), Color.RED);
  }

  @Test
  public void testIconHaloColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconHaloColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getIconHaloColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testIconHaloColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconHaloColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconHaloColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconHaloColor());
    assertNotNull(layer.getIconHaloColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconHaloColor().getFunction().getClass());
    assertEquals(1, layer.getIconHaloColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconHaloColor().getFunction().getBase());
  }

  @Test
  public void testIconHaloColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconHaloColor(Color.RED));
    assertEquals(layer.getIconHaloColorAsInt(), Color.RED);
  }

  @Test
  public void testIconHaloWidthAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-width");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconHaloWidth(0.3f));
    assertEquals((Float) layer.getIconHaloWidth().getValue(), (Float) 0.3f);
  }

  @Test
  public void testIconHaloWidthAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconHaloWidth(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconHaloWidth(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconHaloWidth());
    assertNotNull(layer.getIconHaloWidth().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconHaloWidth().getFunction().getClass());
    assertEquals(1, layer.getIconHaloWidth().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconHaloWidth().getFunction().getBase());
  }

  @Test
  public void testIconHaloBlurAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-blur");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconHaloBlur(0.3f));
    assertEquals((Float) layer.getIconHaloBlur().getValue(), (Float) 0.3f);
  }

  @Test
  public void testIconHaloBlurAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-halo-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconHaloBlur(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconHaloBlur(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconHaloBlur());
    assertNotNull(layer.getIconHaloBlur().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconHaloBlur().getFunction().getClass());
    assertEquals(1, layer.getIconHaloBlur().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconHaloBlur().getFunction().getBase());
  }

  @Test
  public void testIconTranslateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-translate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconTranslate(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getIconTranslate().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testIconTranslateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-translate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconTranslate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, iconTranslate(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconTranslate());
    assertNotNull(layer.getIconTranslate().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconTranslate().getFunction().getClass());
    assertEquals(1, layer.getIconTranslate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getIconTranslate().getFunction().getBase());
  }

  @Test
  public void testIconTranslateAnchorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-translate-anchor");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(iconTranslateAnchor(ICON_TRANSLATE_ANCHOR_MAP));
    assertEquals((String) layer.getIconTranslateAnchor().getValue(), (String) ICON_TRANSLATE_ANCHOR_MAP);
  }

  @Test
  public void testIconTranslateAnchorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("icon-translate-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      iconTranslateAnchor(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, iconTranslateAnchor(ICON_TRANSLATE_ANCHOR_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getIconTranslateAnchor());
    assertNotNull(layer.getIconTranslateAnchor().getFunction());
    assertEquals(ZoomFunction.class, layer.getIconTranslateAnchor().getFunction().getClass());
    assertEquals(1, layer.getIconTranslateAnchor().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getIconTranslateAnchor().getFunction().getType());
  }

  @Test
  public void testTextOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textOpacity(0.3f));
    assertEquals((Float) layer.getTextOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextOpacity());
    assertNotNull(layer.getTextOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextOpacity().getFunction().getClass());
    assertEquals(1, layer.getTextOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextOpacity().getFunction().getBase());
  }

  @Test
  public void testTextColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getTextColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testTextColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextColor());
    assertNotNull(layer.getTextColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextColor().getFunction().getClass());
    assertEquals(1, layer.getTextColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextColor().getFunction().getBase());
  }

  @Test
  public void testTextColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textColor(Color.RED));
    assertEquals(layer.getTextColorAsInt(), Color.RED);
  }

  @Test
  public void testTextHaloColorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textHaloColor("rgba(0, 0, 0, 1)"));
    assertEquals((String) layer.getTextHaloColor().getValue(), (String) "rgba(0, 0, 0, 1)");
  }

  @Test
  public void testTextHaloColorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-color");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textHaloColor(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textHaloColor("rgba(0, 0, 0, 1)"))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextHaloColor());
    assertNotNull(layer.getTextHaloColor().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextHaloColor().getFunction().getClass());
    assertEquals(1, layer.getTextHaloColor().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextHaloColor().getFunction().getBase());
  }

  @Test
  public void testTextHaloColorAsIntConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-color");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textHaloColor(Color.RED));
    assertEquals(layer.getTextHaloColorAsInt(), Color.RED);
  }

  @Test
  public void testTextHaloWidthAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-width");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textHaloWidth(0.3f));
    assertEquals((Float) layer.getTextHaloWidth().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextHaloWidthAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-width");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textHaloWidth(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textHaloWidth(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextHaloWidth());
    assertNotNull(layer.getTextHaloWidth().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextHaloWidth().getFunction().getClass());
    assertEquals(1, layer.getTextHaloWidth().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextHaloWidth().getFunction().getBase());
  }

  @Test
  public void testTextHaloBlurAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-blur");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textHaloBlur(0.3f));
    assertEquals((Float) layer.getTextHaloBlur().getValue(), (Float) 0.3f);
  }

  @Test
  public void testTextHaloBlurAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-halo-blur");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textHaloBlur(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textHaloBlur(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextHaloBlur());
    assertNotNull(layer.getTextHaloBlur().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextHaloBlur().getFunction().getClass());
    assertEquals(1, layer.getTextHaloBlur().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextHaloBlur().getFunction().getBase());
  }

  @Test
  public void testTextTranslateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-translate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textTranslate(new Float[]{0f,0f}));
    assertEquals((Float[]) layer.getTextTranslate().getValue(), (Float[]) new Float[]{0f,0f});
  }

  @Test
  public void testTextTranslateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-translate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textTranslate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, textTranslate(new Float[]{0f,0f}))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextTranslate());
    assertNotNull(layer.getTextTranslate().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextTranslate().getFunction().getClass());
    assertEquals(1, layer.getTextTranslate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getTextTranslate().getFunction().getBase());
  }

  @Test
  public void testTextTranslateAnchorAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-translate-anchor");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(textTranslateAnchor(TEXT_TRANSLATE_ANCHOR_MAP));
    assertEquals((String) layer.getTextTranslateAnchor().getValue(), (String) TEXT_TRANSLATE_ANCHOR_MAP);
  }

  @Test
  public void testTextTranslateAnchorAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("text-translate-anchor");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      textTranslateAnchor(
        zoom(
          //Interval stops (explicit)
          INTERVAL,
          stop(2, textTranslateAnchor(TEXT_TRANSLATE_ANCHOR_MAP))
        )
      )
    );

    //Verify
    assertNotNull(layer.getTextTranslateAnchor());
    assertNotNull(layer.getTextTranslateAnchor().getFunction());
    assertEquals(ZoomFunction.class, layer.getTextTranslateAnchor().getFunction().getClass());
    assertEquals(1, layer.getTextTranslateAnchor().getFunction().getStops().length);
    assertEquals(INTERVAL, layer.getTextTranslateAnchor().getFunction().getType());
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
