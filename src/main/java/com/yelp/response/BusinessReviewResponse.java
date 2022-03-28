package com.yelp.response;

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

    public static class Review {
        private String rating;
        private String time_created;
        private User user;

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getTime_created() {
            return time_created;
        }

        public void setTime_created(String time_created) {
            this.time_created = time_created;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

}
