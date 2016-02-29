package net.levinh.hasbarintest.datastore.Model;

import java.util.Date;

/**
 * Created by Levin on 29/02/2016.
 */
public class Movie {
    private int id;
    private String originalTitle;
    private String overview;
    private Date releaseDate;
    private String posterPath;
    private String title;
    private double voteAverage;
    private boolean TagClick = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean getTagClick() {
        return TagClick;
    }
    public void setTagClick(boolean b){
        this.TagClick = b;
    }
}
