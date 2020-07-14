package com.tts.transitApp.model;


public class Bus {
	  public String ADHERANCE;
	  public String BLOCKID;
	  public String BLOCK_APPR;
	  public String DIRECTION;
	  public String LATITUDE;
	  public String LONGITUDE;
	  public String MSGTIME;
	  public String ROUTE;
	  public String STOPID;
	  public String TIMEPOINT;
	  public String TRIPID;
	  public String VEHICLE;
	  public Double distance;
	  
	  public Bus() {}

	  public Double getDistance() {
	    return distance;
	  }

	  public void setDistance(Double distance) {
	    this.distance = distance;
	  }
	  
	}
