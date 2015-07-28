package com.example.trunghoang.picasso;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by trunghoang on 7/28/15.
 */
public class BlurTransformation implements Transformation {
    private Context context;

    public BlurTransformation(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        // Source from : https://raw.githubusercontent.com/PomepuyN/BlurEffectForAndroidDesign/master/BlurEffect/src/com/npi/blureffect/Blur.java
        // Copyright: PomepuyN
        Bitmap result = Blur.fastblur(context, source, 10);

        // If the image was changed, it deallocates the memory occupied by the old Bitmap
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return BlurTransformation.class.getSimpleName();
    }
}
