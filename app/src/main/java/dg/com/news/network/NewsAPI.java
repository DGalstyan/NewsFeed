package dg.com.news.network;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by dgalstya on 26.09.2017.
 */
public interface NewsAPI {

    /**
     * Gets news.
     *
     * @param page             the page
     * @param pageSize         the page size
     * @param messagesCallBack the messages call back
     */
    @GET("/search?api-key=2a52aef6-2179-46a3-8db6-32229b878349&show-fields=all")
    void getNews (@Query("page") String page, @Query("page-size") String pageSize, Callback<String> messagesCallBack);
}
