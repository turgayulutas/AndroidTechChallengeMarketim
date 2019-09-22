package com.turgayulutas.marketim.View.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TextView;

import com.turgayulutas.marketim.R;

/**
 * Created by turgayulutas on 22/09/2019.
 */

public class CustomTextView extends TextView {

    private final static int PRODUCT_BLACK = 0;
    private final static int PRODUCT_BLACK_ITALIC = 1;
    private final static int PRODUCT_BOLD = 2;
    private final static int PRODUCT_BOLD_ITALIC = 3;
    private final static int PRODUCT_ITALIC = 4;
    private final static int PRODUCT_LIGHT = 5;
    private final static int PRODUCT_LIGHT_ITALIC = 6;
    private final static int PRODUCT_MEDIUM = 7;
    private final static int PRODUCT_MEDIUM_ITALIC = 8;
    private final static int PRODUCT_REGULAR = 9;
    private final static int PRODUCT_THIN = 10;
    private final static int PRODUCT_THIN_ITALIC = 11;

    private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>(16);

    public CustomTextView(Context context) {
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

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomViews);
        int typefaceValue = values.getInt(R.styleable.CustomViews_typeface, 0);
        values.recycle();
        setTypeface(obtaintTypeface(context, typefaceValue));
    }

    private Typeface obtaintTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }

    private Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface;
        switch (typefaceValue) {

            case PRODUCT_BLACK:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Black.ttf");
                break;

            case PRODUCT_BLACK_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_BlackItalic.ttf");
                break;

            case PRODUCT_BOLD:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Bold.ttf");
                break;

            case PRODUCT_BOLD_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_BoldItalic.ttf");
                break;

            case PRODUCT_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Italic.ttf");
                break;

            case PRODUCT_LIGHT:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Light.ttf");
                break;

            case PRODUCT_LIGHT_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_LightItalic.ttf");
                break;

            case PRODUCT_MEDIUM:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Medium.ttf");
                break;

            case PRODUCT_MEDIUM_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_MediumItalic.ttf");
                break;

            case PRODUCT_REGULAR:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Regular.ttf");
                break;

            case PRODUCT_THIN:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_Thin.ttf");
                break;

            case PRODUCT_THIN_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans_ThinItalic.ttf");
                break;

            default:

                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }
        return typeface;
    }
}