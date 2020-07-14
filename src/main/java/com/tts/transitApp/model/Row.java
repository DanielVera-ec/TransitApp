package com.tts.transitApp.model;

import java.util.List;

public class Row {
	public List<Element> elements;
	
	public Row() {}

	public Row(List<Element> elements) {
		this.elements = elements;
	}

	public List<Element> getRows() {
		return elements;
	}

	public void setRows(List<Element> elements) {
		this.elements = elements;
	}
	
	
	
	
}
