// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2016-2018 AppyBuilder.com, All Rights Reserved - Info@AppyBuilder.com
// https://www.gnu.org/licenses/gpl-3.0.en.html

// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime.util;

import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import com.google.appinventor.components.runtime.Component;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TextView;
import android.text.Html;
import android.content.Context;

/**
 * Helper methods for manipulating {@link TextView} objects.
 *
 */
public class TextViewUtil {

  private TextViewUtil() {
  }

  /**
   * TextView alignment setter.
   *
   * @param alignment  one of {@link Component#ALIGNMENT_NORMAL},
   *                   {@link Component#ALIGNMENT_CENTER} or
   *                   {@link Component#ALIGNMENT_OPPOSITE}
   * @param centerVertically whether the text should be centered vertically
   */
  public static void setAlignment(TextView textview, int alignment, boolean centerVertically) {
    int horizontalGravity;
    switch (alignment) {
      default:
        throw new IllegalArgumentException();

      case Component.ALIGNMENT_NORMAL:
        horizontalGravity = Gravity.LEFT;
        break;

      case Component.ALIGNMENT_CENTER:
        horizontalGravity = Gravity.CENTER_HORIZONTAL;
        break;

      case Component.ALIGNMENT_OPPOSITE:
        horizontalGravity = Gravity.RIGHT;
        break;
    }
    int verticalGravity = centerVertically ? Gravity.CENTER_VERTICAL : Gravity.TOP;
    textview.setGravity(horizontalGravity | verticalGravity);
    textview.invalidate();
  }

  /**
   * {@link TextView} background color setter.  Generally, the caller will
   * not pass {@link Component#COLOR_DEFAULT}, instead substituting in the
   * appropriate color.
   *
   * @param textview   text view instance
   * @param argb  background RGB color with alpha
   */
  public static void setBackgroundColor(TextView textview, int argb) {
    textview.setBackgroundColor(argb);
    textview.invalidate();
  }

  public static void setShadow(TextView view, float dx, float dy, float radius, int color) {
    view.setShadowLayer(radius, dx, dy, color);
    view.invalidate();
  }


  /**
   * Returns the enabled state a {@link TextView}.
   *
   * @param textview   text view instance
   * @return  {@code true} for enabled, {@code false} disabled
   */
  public static boolean isEnabled(TextView textview) {
    return textview.isEnabled();
  }

  /**
   * Enables a {@link TextView}.
   *
   * @param textview   text view instance
   * @param enabled  {@code true} for enabled, {@code false} disabled
   */
  public static void setEnabled(TextView textview, boolean enabled) {
    textview.setEnabled(enabled);
    textview.invalidate();
  }

  /**
   * Returns the font size for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param context   Context in the screen to get the density of
   * @return  font size in pixel
   */
  public static float getFontSize(TextView textview, Context context) {
    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
    return textview.getTextSize()/scaledDensity;
  }

  /**
   * Returns the font size for a {@link TextView}.
   *
   * @param textview   text view instance
   * @return  font size in pixel
   */
  public static float getFontSize(TextView textview) {
    return textview.getTextSize();
  }

  /**
   * Sets the font size for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param size  font size in pixel
   */
  public static void setFontSize(TextView textview, float size) {
    textview.setTextSize(size);
    textview.requestLayout();
  }

  /**
   * Sets the font typeface for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param typeface  one of @link Component#TYPEFACE_DEFAULT},
   *                  {@link Component#TYPEFACE_SERIF},
   *                  {@link Component#TYPEFACE_SANSSERIF} or
   *                  {@link Component#TYPEFACE_MONOSPACE}
   * @param bold true for bold, false for not bold
   * @param italic true for italic, false for not italic
   */
  public static void setFontTypeface(TextView textview, int typeface,
      boolean bold, boolean italic) {
    Typeface tf;
    switch (typeface) {
      default:
        throw new IllegalArgumentException();

      case Component.TYPEFACE_DEFAULT:
        tf = Typeface.DEFAULT;
        break;

      case Component.TYPEFACE_SERIF:
        tf = Typeface.SERIF;
        break;

      case Component.TYPEFACE_SANSSERIF:
        tf = Typeface.SANS_SERIF;
        break;

      case Component.TYPEFACE_MONOSPACE:
        tf = Typeface.MONOSPACE;
        break;
    }

    int style = 0;
    if (bold) {
      style |= Typeface.BOLD;
    }
    if (italic) {
      style |= Typeface.ITALIC;
    }
    textview.setTypeface(Typeface.create(tf, style));
    textview.requestLayout();
  }

  /**
   * Returns the text for a {@link TextView}.
   *
   * @param textview   text view instance
   * @return  text shown in text view
   */
  public static String getText(TextView textview) {
    return textview.getText().toString();
  }

  /**
   * Sets the text for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param text  new text to be shown
   */
  public static void setTextHTML(TextView textview, String text) {
    textview.setText(Html.fromHtml(text));
    textview.requestLayout();
  }

  /**
   * Sets the text for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param text  new text to be shown
   */
  public static void setText(TextView textview, String text) {
    textview.setText(text);
    textview.requestLayout();
  }

  /**
   * Creates linkable text for the component.
   * NOTE: DO NOT use this method for TextBoxes. Should only be used for Labels
   * @param textView
   * @param text
   */
  public static void setTextHyperlinked(TextView textView, String text)
  {
    SpannableString spannableString = new SpannableString(text);
    Linkify.addLinks(spannableString, Linkify.ALL);
    textView.setText(spannableString);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
    textView.requestLayout();
  }

  /**
   * Sets the padding for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param padding  left and right padding to be set
   */
  public static void setPadding(TextView textview, int padding) {
    textview.setPadding(padding, padding, 0, 0);
    textview.requestLayout();
  }

  /**
   * Returns the text for a {@link TextView}.
   *
   * @param textview   text view instance
   * @return  text shown in text view
   */
  public static String getTextHyperlinked(TextView textview)
  {
    return getText(textview);
  }


  /**
   * Enables marquee on textview
   * @param textview
   */
  public static void setMarque(TextView textview, boolean enabled) {
    if (enabled) {
      textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    textview.setHorizontallyScrolling(enabled);
    textview.setMarqueeRepeatLimit(enabled?-1:0); //-1 = foreever, 0=stop
    textview.setFocusable(enabled);
    textview.setFocusableInTouchMode(enabled);
    textview.setHorizontallyScrolling(enabled);
    textview.setSingleLine(enabled);  //this is needed to get it working on the emulator
    textview.setSelected(enabled); // this is to start the marquee
  }

  /**
   * Sets the text color for a {@link TextView}.
   *
   * @param textview   text view instance
   * @param argb  text RGB color with alpha
   */
  public static void setTextColor(TextView textview, int argb) {
    textview.setTextColor(argb);
    textview.invalidate();
  }

  public static void setTextColors(TextView textview, ColorStateList colorStateList) {
    textview.setTextColor(colorStateList);
  }
}
