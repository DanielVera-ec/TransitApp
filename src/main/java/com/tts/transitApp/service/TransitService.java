package com.tts.transitApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.transitApp.model.Bus;
import com.tts.transitApp.model.BusComparator;
import com.tts.transitApp.model.BusRequest;
import com.tts.transitApp.model.DistanceResponse;
import com.tts.transitApp.model.GeocodingResponse;
import com.tts.transitApp.model.Location;

@Service
public class TransitService {

    @Value("${transit_url}")
    private String transitUrl;
    
    @Value("${geocoding_url}")
    private String geocodingUrl;
    
    @Value("${distance_url}")
    private String distanceUrl;
    
    @Value("${google_api_key}")
    private String googleApiKey;
    
    private List<Bus> getBuses(){
      RestTemplate restTemplate = new RestTemplate();
      Bus[] buses = restTemplate.getForObject(transitUrl, Bus[].class);
      return Arrays.asList(buses);
    }
    
    private Location getCoordinates(String description) {
      description = description.replace(" ",  "+");
      String url = geocodingUrl + description + "GA&key=" + googleApiKey;
      RestTemplate restTemplate = new RestTemplate();
      GeocodingResponse geocodingResponse = restTemplate.getForObject(url, GeocodingResponse.class);
      return geocodingResponse.results.get(0).geometry.location;
    }
    
    private Double getDistance(Location origin, Location destination) {
      String url = distanceUrl + "origins=" + origin.lat + "," + origin.lng + 
          "&destinations=" + destination.lat + "," + destination.lng + 
          "&key=" + googleApiKey;
      RestTemplate restTemplate = new RestTemplate();
      DistanceResponse distanceResponse = restTemplate.getForObject(url, DistanceResponse.class);
      return distanceResponse.rows.get(0).elements.get(0).distance.value * 0.000621371;
    }
    
    public List<Bus> getNearbyBuses(BusRequest busRequest){
      List<Bus> allBuses = this.getBuses();
      Location personLocation = this.getCoordinates(busRequest.address + " " + busRequest.city);
      List<Bus> nearbyBuses = new ArrayList<>();
      for(Bus bus : allBuses) {
        Location busLocation = new Location();
        busLocation.lat = bus.LATITUDE;
        busLocation.lng = bus.LONGITUDE;
        Double latDistance = Double.parseDouble(busLocation.lat) - Double.parseDouble(personLocation.lat);
        Double lngDistance = Double.parseDouble(busLocation.lng) - Double.parseDouble(personLocation.lng); 
        if ( Math.abs(latDistance) <= 0.05 && Math.abs(lngDistance) <= 0.05) {
          Double distance = getDistance(busLocation, personLocation);
          if (distance <= 1) {
            bus.distance = Math.round(distance * 100) / 100D;
            nearbyBuses.add(bus);
          }
        }
      }
      Collections.sort(nearbyBuses, new BusComparator());
      return nearbyBuses;
    }
    
}
