package com.personal.dominykasviecas.popularmovies.util;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

public final class ViewUtils {

    public static final ButterKnife.Setter<View, Boolean> VISIBLE =
            (view, value, index) -> view.setVisibility(value ? View.VISIBLE : View.GONE);

    public static void tintTextViewDrawable(
            @NonNull Resources resources,
            @NonNull TextView textView,
            @ColorRes int color) {
        Drawable[] drawables = textView.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setColorFilter(resources.getColor(color), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
}
