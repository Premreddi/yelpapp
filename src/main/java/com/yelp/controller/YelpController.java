package com.yelp.controller;

import com.google.gson.Gson;
import com.yelp.config.MyErrorHandler;
import com.yelp.model.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/yelp")
@Transactional(rollbackFor = Exception.class)
public class YelpController {

    public static final String DATE_FORMAT = "MM-dd-yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:SS";
    String yelpUrl = "https://api.yelp.com/v3/businesses/";
    String bearerToken = "9ejLORKsQnr8xFJrWV8rQmRx0HQV_G443_wXMtTxy9NAYsBsgvxe6zMUwIQFfOdhNafiYX23Nyelv3PhRaNWnht2ETt8rzlCXoEHL9s38J8MoLMfJ2YcTAaHqAxuYXYx";

    @RequestMapping( method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<YelpBusinessReviewResponse> getYelpReviewsByBusinessAndLocation(@RequestParam(value = "business", required = true) String business, @RequestParam(value = "location", required = true) String location) throws Exception {

        YelpBusinessReviewResponse yelpBusinessReviewResponse = new YelpBusinessReviewResponse();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(new Object()), headers);
        ResponseEntity<YelpReviewResponse> yelpResponse = restTemplate.exchange(yelpUrl + "search?term=" + business + "&location=" + location , HttpMethod.GET, entity, YelpReviewResponse.class);
        if (yelpResponse.getStatusCode() != HttpStatus.OK) {
            yelpBusinessReviewResponse.setStatusMessage("No data found");
            return ResponseEntity.badRequest().body(yelpBusinessReviewResponse);
        }

        ResponseEntity<BusinessReviewResponse> result = restTemplate.exchange(yelpUrl + yelpResponse.getBody().getBusinesses().get(0).getId() + "/reviews", HttpMethod.GET, entity, BusinessReviewResponse.class);
        if (result.getStatusCode() != HttpStatus.OK) {
            yelpBusinessReviewResponse.setStatusMessage("No data found");
            return ResponseEntity.badRequest().body(yelpBusinessReviewResponse);
        }

        List<YelpBusinessReviewItem> reviewItems = new ArrayList<>();
        for (Review review: result.getBody().getReviews()) {
            YelpBusinessReviewItem yelpBusinessReviewItem = new YelpBusinessReviewItem();
            yelpBusinessReviewItem.setImageUrl(review.getUser().getImage_url());
            yelpBusinessReviewItem.setDate(getDateFormater().format(getDateTimeFormater().parse(review.getTime_created())));
            yelpBusinessReviewItem.setName(review.getUser().getName());
            yelpBusinessReviewItem.setRating(review.getRating());
            reviewItems.add(yelpBusinessReviewItem);
        }

        yelpBusinessReviewResponse.setReviews(reviewItems);
        return ResponseEntity.ok(yelpBusinessReviewResponse);
    }

    public SimpleDateFormat getDateFormater(){
        return new SimpleDateFormat(DATE_FORMAT);
    }

    public SimpleDateFormat getDateTimeFormater(){
        return new SimpleDateFormat(DATE_TIME_FORMAT);
    }

}