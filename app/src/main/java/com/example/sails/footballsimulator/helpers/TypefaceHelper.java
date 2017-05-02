package com.example.sails.footballsimulator.helpers;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by sails on 26.01.2017.
 */

public class TypefaceHelper {

    static Typeface tfRoboto;
    static Typeface tfRobotoBold;
    static Typeface tfOpenSans;

    public static Typeface getRobotoTf(Context context){
        if(null == tfRoboto){
            tfRoboto = Typeface.createFromAsset(context.getAssets(), "roboto_light.ttf");
        }
        return tfRoboto;
    }

    public static Typeface getRobotoBoldTf(Context context){
        if(null == tfRobotoBold){
            tfRobotoBold = Typeface.createFromAsset(context.getAssets(), "roboto_bold.ttf");
        }
        return tfRobotoBold;
    }

    public static Typeface getOpenSansTf(Context context){
        if(null == tfOpenSans){
            tfOpenSans = Typeface.createFromAsset(context.getAssets(), "open_sans_light.ttf");
        }
        return tfOpenSans;
    }

}
