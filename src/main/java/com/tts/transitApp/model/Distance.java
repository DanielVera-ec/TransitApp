package com.tts.transitApp.model;

public class Distance {
	  public String text;
	  public Integer value;
	  
	  public Distance() {}

	  public Distance(String text, Integer value) {
	    this.text = text;
	    this.value = value;
	  }

	  public String getText() {
	    return text;
	  }

	  public void setText(String text) {
	    this.text = text;
	  }

	  public Integer getValue() {
	    return value;
	  }

	  public void setValue(Integer value) {
	    this.value = value;
	  }
	  
	}
