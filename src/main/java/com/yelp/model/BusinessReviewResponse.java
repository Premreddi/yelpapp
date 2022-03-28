package com.yelp.model;

import java.util.ArrayList;
import java.util.List;

public class BusinessReviewResponse extends BaseResponse {

    private List<Review> reviews = new ArrayList<>();

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
