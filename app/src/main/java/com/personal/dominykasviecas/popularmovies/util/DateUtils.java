package com.personal.dominykasviecas.popularmovies.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public final class DateUtils {

    @NonNull
    public static String getYearFromDate(@NonNull Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
