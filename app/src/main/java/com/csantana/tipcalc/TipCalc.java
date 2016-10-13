package com.csantana.tipcalc;

import android.app.Application;

/**
 * Created by CLsantana on 12/10/16.
 */
public class TipCalc extends Application {
    private final static String ABOUT_URL = "http://www.google.com";

                public String getAboutUrl() {
                return ABOUT_URL;
            }
}
