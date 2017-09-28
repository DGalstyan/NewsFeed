package dg.com.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by dgalstya on 26.09.2017.
 */
@RealmClass
public class Feed extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("webPublicationDate")
    @Expose
    private String webPublicationDate;
    @SerializedName("webTitle")
    @Expose
    private String webTitle;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("apiUrl")
    @Expose
    private String apiUrl;
    @SerializedName("fields")
    @Expose
    private FeedInfo feedInfo;
    @SerializedName("isHosted")
    @Expose
    private Boolean isHosted;



    private boolean isLiked = false;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets section id.
     *
     * @return the section id
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * Sets section id.
     *
     * @param sectionId the section id
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * Gets section name.
     *
     * @return the section name
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * Sets section name.
     *
     * @param sectionName the section name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * Gets web publication date.
     *
     * @return the web publication date
     */
    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    /**
     * Sets web publication date.
     *
     * @param webPublicationDate the web publication date
     */
    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    /**
     * Gets web title.
     *
     * @return the web title
     */
    public String getWebTitle() {
        return webTitle;
    }

    /**
     * Sets web title.
     *
     * @param webTitle the web title
     */
    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    /**
     * Gets web url.
     *
     * @return the web url
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * Sets web url.
     *
     * @param webUrl the web url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * Gets api url.
     *
     * @return the api url
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Sets api url.
     *
     * @param apiUrl the api url
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Gets feed info.
     *
     * @return the feed info
     */
    public FeedInfo getFeedInfo() {
        if(feedInfo.isValid()){
            return feedInfo;
        }
        return null;
    }

    /**
     * Sets feed info.
     *
     * @param feedInfo the feed info
     */
    public void setFeedInfo(FeedInfo feedInfo) {
        this.feedInfo = feedInfo;
    }

    /**
     * Gets is hosted.
     *
     * @return the is hosted
     */
    public Boolean getIsHosted() {
        return isHosted;
    }

    /**
     * Sets is hosted.
     *
     * @param isHosted the is hosted
     */
    public void setIsHosted(Boolean isHosted) {
        this.isHosted = isHosted;
    }

    /**
     * Is liked boolean.
     *
     * @return the boolean
     */
    public boolean isLiked() {
        return isLiked;
    }

    /**
     * Sets liked.
     *
     * @param liked the liked
     */
    public void setLiked(boolean liked) {
        isLiked = liked;
    }

}