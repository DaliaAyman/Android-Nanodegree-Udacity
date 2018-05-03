package com.android.dalia.popularmovies.data.remote;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Dalia on 11/6/2017.
 */

public class EnvelopeConverter<T> implements Converter<ResponseBody, T> {
    final Converter<ResponseBody, EnvelopeMovies<T>> delegate;

    public EnvelopeConverter(Converter<ResponseBody, EnvelopeMovies<T>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        EnvelopeMovies<T> envelopeMovies = delegate.convert(responseBody);
        return envelopeMovies.results;
    }
}
