package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {
	
	@Override
	public List<FeedingSchedule> getAllFeedingSchedules() {
		List<FeedingSchedule> feedingSchedule = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM FEEDING_SCHEDULES";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) { //Results are return in rows. .next returns to the next row aka another record
				FeedingSchedule fs = new FeedingSchedule();
				
				fs.setScheduleID(rs.getLong("scheduleID"));
				fs.setFeedingTime(rs.getString("feeding_time"));
				
				fs.setRecurrence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
				
				feedingSchedule.add(fs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return feedingSchedule;
	}
	@Override
	public void saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES VALUES (?,?,?,?,?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, feedingSchedule.getScheduleID());
			stmt.setString(2, feedingSchedule.getFeedingTime());
			stmt.setString(3, feedingSchedule.getRecurrence());
			stmt.setString(4, feedingSchedule.getFood());
			stmt.setString(5, feedingSchedule.getNotes());
			
			success = stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if (success == 0) {
			throw new Exception("Insert feeding schedule failed: " + feedingSchedule);
		}
	}
	@Override
	public void setFeedingSchedule(Animal animal, FeedingSchedule feedingSchedule) {
		
	}
	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) {
		Connection connection = null;
		Statement stmt = null;
		FeedingSchedule feedingSchedule = new FeedingSchedule();
		long animalID = animal.getAnimalID();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT " + animalID + " FROM ANIMALS";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			feedingSchedule.setScheduleID(rs.getLong("Feeding_Schedule"));
			
			sql = "SELECT " + feedingSchedule.getScheduleID() + " FROM FEEDING_SCHEDULES";
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return feedingSchedule;
	}
	@Override
	public void removeFeedingSchedule(Animal animal) {
		
	}
	@Override
	public void deleteFeedingSchedule(FeedingSchedule feedingSchedule) {
		
	}
}
