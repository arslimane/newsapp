package com.example.newsapp;

public class News {

    // Initialize strings.
    private final String sectionName;
    private final String webTitle;
    private final String webPublicationDate;
    private final String webUrl;
    private final String author;


    private final String image;

    /**
     * Defaut constructor {@link News} object.
     *
     * @param sectionName        is the sectionName name for news.
     * @param webTitle           is the webTitle of the news.
     * @param webPublicationDate is the publication's webPublicationDate of the news.
     * @param webUrl             is the webUrl's name of the news.
     * @param author             is the author's name of the news.
     */
    public News(String sectionName, String webTitle, String webPublicationDate, String webUrl,
                String author, String image) {

        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
        this.author = author;
        this.image = image;
    }

    /**
     * @return the string of the section's name.
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @return the string of the webTitle's name.
     */
    public String getWebTitle() {
        return webTitle;
    }

    /**
     * @return the string of the webPublication's date
     */
    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    /**
     * @return the string of the webUrl's name.
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * @return the string of the author's name
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return the string of the thumbnail's url
     */


    /**
     * Return the string representation of the (@link News) object
     */
    @Override
    public String toString() {
        return "News{" +
                "sectionName='" + sectionName + '\'' +
                "webTitle='" + webTitle + '\'' +
                "webPublicationDate='" + webPublicationDate + '\'' +
                "webUrl='" + webUrl + '\'' +
                "author='" + author + '\'' +

                '}';
    }

    public String getImage() {
        return image;
    }

}
