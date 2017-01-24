package com.mapbox.mapboxsdk.utils;

import java.io.OutputStream;
import java.io.IOException;

public class NativeOutputStream extends OutputStream {
  //
  // Native peer management
  //
  static {
    System.loadLibrary("mapbox-gl");
  }

  // Pointer to the native peer
  private long peer;

  // Initializes the native peer object
  private native void initialize();

  @Override
  protected native void finalize() throws Throwable;

  //
  // Constructors
  //

  public NativeOutputStream() {
    initialize();
  }

  //
  // Methods
  //
  public final native void write(byte[] b, int off, int len) throws IOException;

  public final native void write(int b) throws IOException;
}