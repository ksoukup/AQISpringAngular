package com.fdmgroup.reading;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, ReadingId> {
	
	List<Reading> findByRegionName(String regionName);
	
	@Query("SELECT r1 FROM Reading r1 WHERE r1.timeStamp =(SELECT MAX(r2.timeStamp) FROM Reading r2 WHERE r1.regionName = r2.regionName)")
	List<Reading> findCurrentReadings();

}