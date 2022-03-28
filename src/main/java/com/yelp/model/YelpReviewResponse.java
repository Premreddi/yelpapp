package com.yelp.model;

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


}
