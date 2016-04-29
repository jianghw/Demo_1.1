package com.jianghw.music.xutil;

import android.support.annotation.Nullable;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br/>
 * @Since: </b>2016/4/28<br/>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class NullExUtils {

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param errorMessage the exception message to use if the check fails; will be converted to a
     *     string using {@link String#valueOf(Object)}
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
