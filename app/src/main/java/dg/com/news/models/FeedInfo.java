package dg.com.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by dgalstya on 26.09.2017.
 */
@RealmClass
public class FeedInfo extends RealmObject  {
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("standfirst")
    @Expose
    private String standfirst;
    @SerializedName("trailText")
    @Expose
    private String trailText;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("wordcount")
    @Expose
    private String wordcount;
    @SerializedName("firstPublicationDate")
    @Expose
    private String firstPublicationDate;
    @SerializedName("isInappropriateForSponsorship")
    @Expose
    private String isInappropriateForSponsorship;
    @SerializedName("isPremoderated")
    @Expose
    private String isPremoderated;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("liveBloggingNow")
    @Expose
    private String liveBloggingNow;
    @SerializedName("productionOffice")
    @Expose
    private String productionOffice;
    @SerializedName("publication")
    @Expose
    private String publication;
    @SerializedName("shortUrl")
    @Expose
    private String shortUrl;
    @SerializedName("shouldHideAdverts")
    @Expose
    private String shouldHideAdverts;
    @SerializedName("showInRelatedContent")
    @Expose
    private String showInRelatedContent;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("legallySensitive")
    @Expose
    private String legallySensitive;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("bodyText")
    @Expose
    private String bodyText;
    @SerializedName("charCount")
    @Expose
    private String charCount;
    @SerializedName("shouldHideReaderRevenue")
    @Expose
    private String shouldHideReaderRevenue;

    /**
     * Gets headline.
     *
     * @return the headline
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Sets headline.
     *
     * @param headline the headline
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * Gets standfirst.
     *
     * @return the standfirst
     */
    public String getStandfirst() {
        return standfirst;
    }

    /**
     * Sets standfirst.
     *
     * @param standfirst the standfirst
     */
    public void setStandfirst(String standfirst) {
        this.standfirst = standfirst;
    }

    /**
     * Gets trail text.
     *
     * @return the trail text
     */
    public String getTrailText() {
        return trailText;
    }

    /**
     * Sets trail text.
     *
     * @param trailText the trail text
     */
    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }

    /**
     * Gets byline.
     *
     * @return the byline
     */
    public String getByline() {
        return byline;
    }

    /**
     * Sets byline.
     *
     * @param byline the byline
     */
    public void setByline(String byline) {
        this.byline = byline;
    }

    /**
     * Gets main.
     *
     * @return the main
     */
    public String getMain() {
        return main;
    }

    /**
     * Sets main.
     *
     * @param main the main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets wordcount.
     *
     * @return the wordcount
     */
    public String getWordcount() {
        return wordcount;
    }

    /**
     * Sets wordcount.
     *
     * @param wordcount the wordcount
     */
    public void setWordcount(String wordcount) {
        this.wordcount = wordcount;
    }

    /**
     * Gets first publication date.
     *
     * @return the first publication date
     */
    public String getFirstPublicationDate() {
        return firstPublicationDate;
    }

    /**
     * Sets first publication date.
     *
     * @param firstPublicationDate the first publication date
     */
    public void setFirstPublicationDate(String firstPublicationDate) {
        this.firstPublicationDate = firstPublicationDate;
    }

    /**
     * Gets is inappropriate for sponsorship.
     *
     * @return the is inappropriate for sponsorship
     */
    public String getIsInappropriateForSponsorship() {
        return isInappropriateForSponsorship;
    }

    /**
     * Sets is inappropriate for sponsorship.
     *
     * @param isInappropriateForSponsorship the is inappropriate for sponsorship
     */
    public void setIsInappropriateForSponsorship(String isInappropriateForSponsorship) {
        this.isInappropriateForSponsorship = isInappropriateForSponsorship;
    }

    /**
     * Gets is premoderated.
     *
     * @return the is premoderated
     */
    public String getIsPremoderated() {
        return isPremoderated;
    }

    /**
     * Sets is premoderated.
     *
     * @param isPremoderated the is premoderated
     */
    public void setIsPremoderated(String isPremoderated) {
        this.isPremoderated = isPremoderated;
    }

    /**
     * Gets last modified.
     *
     * @return the last modified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * Sets last modified.
     *
     * @param lastModified the last modified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets live blogging now.
     *
     * @return the live blogging now
     */
    public String getLiveBloggingNow() {
        return liveBloggingNow;
    }

    /**
     * Sets live blogging now.
     *
     * @param liveBloggingNow the live blogging now
     */
    public void setLiveBloggingNow(String liveBloggingNow) {
        this.liveBloggingNow = liveBloggingNow;
    }

    /**
     * Gets production office.
     *
     * @return the production office
     */
    public String getProductionOffice() {
        return productionOffice;
    }

    /**
     * Sets production office.
     *
     * @param productionOffice the production office
     */
    public void setProductionOffice(String productionOffice) {
        this.productionOffice = productionOffice;
    }

    /**
     * Gets publication.
     *
     * @return the publication
     */
    public String getPublication() {
        return publication;
    }

    /**
     * Sets publication.
     *
     * @param publication the publication
     */
    public void setPublication(String publication) {
        this.publication = publication;
    }

    /**
     * Gets short url.
     *
     * @return the short url
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Sets short url.
     *
     * @param shortUrl the short url
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * Gets should hide adverts.
     *
     * @return the should hide adverts
     */
    public String getShouldHideAdverts() {
        return shouldHideAdverts;
    }

    /**
     * Sets should hide adverts.
     *
     * @param shouldHideAdverts the should hide adverts
     */
    public void setShouldHideAdverts(String shouldHideAdverts) {
        this.shouldHideAdverts = shouldHideAdverts;
    }

    /**
     * Gets show in related content.
     *
     * @return the show in related content
     */
    public String getShowInRelatedContent() {
        return showInRelatedContent;
    }

    /**
     * Sets show in related content.
     *
     * @param showInRelatedContent the show in related content
     */
    public void setShowInRelatedContent(String showInRelatedContent) {
        this.showInRelatedContent = showInRelatedContent;
    }

    /**
     * Gets thumbnail.
     *
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Sets thumbnail.
     *
     * @param thumbnail the thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Gets legally sensitive.
     *
     * @return the legally sensitive
     */
    public String getLegallySensitive() {
        return legallySensitive;
    }

    /**
     * Sets legally sensitive.
     *
     * @param legallySensitive the legally sensitive
     */
    public void setLegallySensitive(String legallySensitive) {
        this.legallySensitive = legallySensitive;
    }

    /**
     * Gets lang.
     *
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets lang.
     *
     * @param lang the lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Gets body text.
     *
     * @return the body text
     */
    public String getBodyText() {
        return bodyText;
    }

    /**
     * Sets body text.
     *
     * @param bodyText the body text
     */
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    /**
     * Gets char count.
     *
     * @return the char count
     */
    public String getCharCount() {
        return charCount;
    }

    /**
     * Sets char count.
     *
     * @param charCount the char count
     */
    public void setCharCount(String charCount) {
        this.charCount = charCount;
    }

    /**
     * Gets should hide reader revenue.
     *
     * @return the should hide reader revenue
     */
    public String getShouldHideReaderRevenue() {
        return shouldHideReaderRevenue;
    }

    /**
     * Sets should hide reader revenue.
     *
     * @param shouldHideReaderRevenue the should hide reader revenue
     */
    public void setShouldHideReaderRevenue(String shouldHideReaderRevenue) {
        this.shouldHideReaderRevenue = shouldHideReaderRevenue;
    }

}
