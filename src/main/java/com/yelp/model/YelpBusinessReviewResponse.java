package com.yelp.model;

import java.util.ArrayList;
import java.util.List;

public class YelpBusinessReviewResponse extends BaseResponse {

    List<YelpBusinessReviewItem> reviews = new ArrayList<>();

    public List<YelpBusinessReviewItem> getReviews() {
        return reviews;
    }

    public void setReviews(List<YelpBusinessReviewItem> reviews) {
        this.reviews = reviews;
    }

}
