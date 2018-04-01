package com.personal.dominykasviecas.popularmovies.di;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.personal.dominykasviecas.popularmovies.App;
import com.personal.dominykasviecas.popularmovies.BuildConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module(includes = {ViewModelModule.class, TMDBModule.class})
public class ApplicationModule {

    private static final String API_KEY = "api_key";

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return app.getResources();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            final Request original = chain.request();
            final HttpUrl originalHttpUrl = original.url();

            final HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.TmdbApiKey)
                    .build();

            final Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            final Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        httpClient.addInterceptor(chain -> {
            final Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody originalBody = response.body();

            if (originalBody == null) {
                return response;
            }

            try {
                JSONObject responseJson = new JSONObject(originalBody.string());
                MediaType contentType = originalBody.contentType();
                JSONArray jsonArray = responseJson.optJSONArray("results");
                if (jsonArray != null) {
                    ResponseBody body = ResponseBody.create(contentType, jsonArray.toString());
                    return response.newBuilder().body(body).build();
                } else {
                    ResponseBody body = ResponseBody.create(contentType, responseJson.toString());
                    return response.newBuilder().body(body).build();
                }
            } catch (Exception e) {
                Timber.e(e);
                return response;
            }
        });

        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TmdbApiUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
