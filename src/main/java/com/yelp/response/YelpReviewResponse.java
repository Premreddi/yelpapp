package com.yelp.response;

import java.util.ArrayList;
import java.util.List;

public class YelpReviewResponse {


    private List<Businesses> businesses = new ArrayList<>();

    public List<Businesses> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Businesses> businesses) {
        this.businesses = businesses;
    }

    public static class Businesses {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}
