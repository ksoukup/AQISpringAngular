package com.fdmgroup.reading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.reading.Region;

@RestController
public class ReadingController {
	
	String[] regionIds = new String[] {"RWE", "RSO", "RNO", "REA", "RCE", "NRS"};
	
	@Autowired
	private ReadingRepository readingRepositry; 

	private static final Logger logger = LoggerFactory.getLogger(ReadingController.class);
	
	@GetMapping("/regionalReading")
	public List<Reading> getReading(@RequestParam(value="regionName", defaultValue="NRS") String regionName) {
		logger.debug(String.format("Reading repository for %s", regionName ));
		return readingRepositry.findByRegionName(regionName);
	}
	
	@GetMapping("/currentReadings")
	public List<Region> getCurrentReadings(){
		List<Region> regions = new ArrayList<Region>();
		List<Reading> readings = readingRepositry.findCurrentReadings();
		
		for(String regionId: regionIds) {
			Region region = new Region(readings.stream()
					.filter(r -> r.getRegionName().equals(regionId))
					.collect(Collectors.toCollection(ArrayList::new)));
				region.setRegionId(regionId);
				region.setRegionName(regionId);

			logger.trace(region.toString());
			regions.add(region);
		}
		
		return regions;
	}
}

