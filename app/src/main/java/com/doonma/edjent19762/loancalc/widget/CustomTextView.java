/*package com.doonma.edjent19762.loancalc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.doonma.edjent19762.loancalc.R;

/**
 * Created by edjent1976 on 1/25/15.

public class CustomTextView extends TextView {
    private final static int ROBOTO = 0;
    private final static int ROBOTO_CONDENSED = 1;

    public CustomTextView(Context context){
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs){
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);

        int typeface = values.getInt(R.styleable.CustomTextView_typeface, 0);

        switch (typeface) {
            case ROBOTO:default:

                //setTypeface();
                break;
            case ROBOTO_CONDENSED:
                //setTypeface(ROBOTO_CONDENSED);
                break;
        }

        values.recycle();;
    }
}
*/