package dg.com.news.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dg.com.news.helpers.Utils;
import retrofit.RestAdapter;

/**
 * Created by dgalstya on 26.09.2017.
 *
 * @param <T> the type parameter
 */
public class NewsHttpClient<T> {

    private static Gson baseGson;
    private static GsonBuilder builder;
    private final T mApiService;

    /**
     * Instantiates a new News http client.
     *
     * @param apiService the api service
     * @param targetLink the target link
     */
    private NewsHttpClient (Class<T> apiService, String targetLink) {

        builder = new GsonBuilder()
                .serializeNulls();

        baseGson = builder.create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(targetLink)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new EntityEscapedStringConverter(baseGson))
                .build();
        mApiService = restAdapter.create(apiService);

    }



    /**
     * Gets api service.
     *
     * @return the api service
     */
    public static NewsAPI getApiService () {
        return new NewsHttpClient<>(NewsAPI.class, Utils.LIVE_URL).mApiService;
    }

    public static GsonBuilder getDefaultBuilder() {
        return builder;
    }
}
