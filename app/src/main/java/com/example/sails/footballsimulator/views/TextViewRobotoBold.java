package com.example.sails.footballsimulator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.sails.footballsimulator.helpers.TypefaceHelper;

/**
 * Created by sails on 26.01.2017.
 */

public class TextViewRobotoBold extends TextView {
    public TextViewRobotoBold(Context context) {
        super(context);
        setFont(context);
    }

    public TextViewRobotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);

    }

    public TextViewRobotoBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context){
        this.setTypeface(TypefaceHelper.getOpenSansTf(context));
    }
}
