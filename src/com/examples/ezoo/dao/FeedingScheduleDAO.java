package com.examples.ezoo.dao;

import java.util.List;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/*
 * Main interface used to execute CRUD methods on FeedingSchedule class.
 */
public interface FeedingScheduleDAO {
	
	/*
	 * Used to retrieve a list of all feeding schedules
	 */
	List<FeedingSchedule> getAllFeedingSchedules();
	
	/*
	 * Used to persist the feeding schedule to the datastore
	 */
	void saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception;
	
	/*
	 * Used to set a feeding schedule to an animal
	 */
	void setFeedingSchedule(Animal animal, FeedingSchedule feedingSchedule);
	
	/*
	 * Used to get a feeding schedule from a specific animal
	 */
	FeedingSchedule getFeedingSchedule(Animal animal);
	
	/*
	 * Used to remove a feeding schedule from a specific animal
	 */
	void removeFeedingSchedule(Animal animal);
	
	/*
	 * Used to delete an entire feeding schedule
	 */
	void deleteFeedingSchedule(FeedingSchedule feedingSchedule);
	
}
