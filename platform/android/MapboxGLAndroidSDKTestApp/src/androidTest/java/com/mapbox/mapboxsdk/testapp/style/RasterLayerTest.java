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
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
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
 * Basic smoke tests for RasterLayer
 */
@RunWith(AndroidJUnit4.class)
public class RasterLayerTest extends BaseStyleTest {

  @Rule
  public final ActivityTestRule<RuntimeStyleTestActivity> rule = new ActivityTestRule<>(RuntimeStyleTestActivity.class);

  private RasterLayer layer;

  private OnMapReadyIdlingResource idlingResource;

  private MapboxMap mapboxMap;

  @Before
  public void setup() {
    idlingResource = new OnMapReadyIdlingResource(rule.getActivity());
    Espresso.registerIdlingResources(idlingResource);
    mapboxMap = rule.getActivity().getMapboxMap();

    if ((layer = mapboxMap.getLayerAs("my-layer")) == null) {
      Timber.i("Adding layer");
      layer = new RasterLayer("my-layer", "composite");
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
  public void testRasterOpacityAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-opacity");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterOpacity(0.3f));
    assertEquals((Float) layer.getRasterOpacity().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterOpacityAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-opacity");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterOpacity(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterOpacity(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterOpacity());
    assertNotNull(layer.getRasterOpacity().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterOpacity().getFunction().getClass());
    assertEquals(1, layer.getRasterOpacity().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterOpacity().getFunction().getBase());
  }

  @Test
  public void testRasterHueRotateAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-hue-rotate");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterHueRotate(0.3f));
    assertEquals((Float) layer.getRasterHueRotate().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterHueRotateAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-hue-rotate");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterHueRotate(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterHueRotate(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterHueRotate());
    assertNotNull(layer.getRasterHueRotate().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterHueRotate().getFunction().getClass());
    assertEquals(1, layer.getRasterHueRotate().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterHueRotate().getFunction().getBase());
  }

  @Test
  public void testRasterBrightnessMinAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-brightness-min");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterBrightnessMin(0.3f));
    assertEquals((Float) layer.getRasterBrightnessMin().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterBrightnessMinAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-brightness-min");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterBrightnessMin(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterBrightnessMin(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterBrightnessMin());
    assertNotNull(layer.getRasterBrightnessMin().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterBrightnessMin().getFunction().getClass());
    assertEquals(1, layer.getRasterBrightnessMin().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterBrightnessMin().getFunction().getBase());
  }

  @Test
  public void testRasterBrightnessMaxAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-brightness-max");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterBrightnessMax(0.3f));
    assertEquals((Float) layer.getRasterBrightnessMax().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterBrightnessMaxAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-brightness-max");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterBrightnessMax(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterBrightnessMax(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterBrightnessMax());
    assertNotNull(layer.getRasterBrightnessMax().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterBrightnessMax().getFunction().getClass());
    assertEquals(1, layer.getRasterBrightnessMax().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterBrightnessMax().getFunction().getBase());
  }

  @Test
  public void testRasterSaturationAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-saturation");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterSaturation(0.3f));
    assertEquals((Float) layer.getRasterSaturation().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterSaturationAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-saturation");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterSaturation(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterSaturation(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterSaturation());
    assertNotNull(layer.getRasterSaturation().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterSaturation().getFunction().getClass());
    assertEquals(1, layer.getRasterSaturation().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterSaturation().getFunction().getBase());
  }

  @Test
  public void testRasterContrastAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-contrast");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterContrast(0.3f));
    assertEquals((Float) layer.getRasterContrast().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterContrastAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-contrast");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterContrast(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterContrast(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterContrast());
    assertNotNull(layer.getRasterContrast().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterContrast().getFunction().getClass());
    assertEquals(1, layer.getRasterContrast().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterContrast().getFunction().getBase());
  }

  @Test
  public void testRasterFadeDurationAsConstant() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-fade-duration");
    assertNotNull(layer);

    //Set and Get
    layer.setProperties(rasterFadeDuration(0.3f));
    assertEquals((Float) layer.getRasterFadeDuration().getValue(), (Float) 0.3f);
  }

  @Test
  public void testRasterFadeDurationAsZoomFunction() {
    checkViewIsDisplayed(R.id.mapView);
    Timber.i("raster-fade-duration");
    assertNotNull(layer);

    //Set
    layer.setProperties(
      rasterFadeDuration(
        zoom(
          //Exponential stops (implicit)
          0.5f,
          stop(2, rasterFadeDuration(0.3f))
        )
      )
    );

    //Verify
    assertNotNull(layer.getRasterFadeDuration());
    assertNotNull(layer.getRasterFadeDuration().getFunction());
    assertEquals(ZoomFunction.class, layer.getRasterFadeDuration().getFunction().getClass());
    assertEquals(1, layer.getRasterFadeDuration().getFunction().getStops().length);
    assertEquals((Float) 0.5f, layer.getRasterFadeDuration().getFunction().getBase());
  }


  @After
  public void unregisterIntentServiceIdlingResource() {
    Espresso.unregisterIdlingResources(idlingResource);
  }
}
