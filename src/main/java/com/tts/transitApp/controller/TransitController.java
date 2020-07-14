package com.tts.transitApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.transitApp.model.Bus;
import com.tts.transitApp.model.BusRequest;
import com.tts.transitApp.service.TransitService;

@Controller
public class TransitController {
	@Autowired
	private TransitService transitService;
	
	@GetMapping({"/", "/buses"})
	public String getBusesPage(Model model) {
		model.addAttribute("busRequest", new BusRequest());
		return "index";
	}
	
	@PostMapping("/buses")
	public String getNearbyBuses(BusRequest busRequest, Model model) {
		List<Bus> buses = transitService.getNearbyBuses(busRequest);
		model.addAttribute("buses", buses);
		model.addAttribute("busRequest", busRequest);
		return "index";
	}
}
