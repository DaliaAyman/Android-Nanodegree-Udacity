package com.android.dalia.popularmovies.data.remote;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Dalia on 11/6/2017.
 */

public class EnvelopeConverterFactory extends Converter.Factory{

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            final Type type, Annotation[] annotations, Retrofit retrofit) {

        Type envelopeType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {type};
            }

            @Override
            public Type getRawType() {
                return EnvelopeMovies.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this,
                envelopeType, annotations);

        return new EnvelopeConverter(delegate);
    }
}
