package dg.com.news;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dg.com.news.helpers.RealmController;
import dg.com.news.models.Feed;
import dg.com.news.network.NewsHttpClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * The type Find items interactor.
 */
public class FindItemsInteractorImpl implements FindItemsInteractor {

    @Override
    public void getFeeds(final OnFinishedListener listener){
        NewsHttpClient.getApiService().getNews("1", "20", new Callback<String>() {
            @Override
            public void success (String s, Response response) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject responseJson =jsonObject.getJSONObject("response");
                    JSONArray resultsArray = responseJson.getJSONArray("results");
                    int maxItems = responseJson.getInt("total");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject feedJson = resultsArray.getJSONObject(i);
                        Feed feed = NewsHttpClient.getDefaultBuilder().create().fromJson(feedJson.toString(), Feed.class);
                        RealmController.with().saveFeed(feed);
                    }
                    listener.setTotalItemsCount(maxItems);
                    listener.onFinished();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure (RetrofitError error) {
                listener.onFinished();
            }
        });
    }

    @Override
    public void getMoreFeeds(final OnMoreItemsFinishedListener listener, final int page){

            NewsHttpClient.getApiService().getNews(String.valueOf(page), "10", new Callback<String>() {
                @Override
                public void success (String s, Response response) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        JSONObject responseJson =jsonObject.getJSONObject("response");
                        JSONArray resultsArray = responseJson.getJSONArray("results");

                        for (int i = 0; i < resultsArray.length(); i++) {
                            JSONObject feedJson = resultsArray.getJSONObject(i);
                            Feed feed = NewsHttpClient.getDefaultBuilder().create().fromJson(feedJson.toString(), Feed.class);
                            RealmController.with().saveFeed(feed);
                        }
                        listener.onSuccess();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure (RetrofitError error) {
                    listener.onError();
                }
            });
    }
}
