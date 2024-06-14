package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Event;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class EventDao {
	public boolean save(Event newEvent) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO EVENTS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, newEvent.getId());
			stmt.setString(2, newEvent.getTitle());
			stmt.setString(3, newEvent.getDescription());
			stmt.setString(4, newEvent.getTag());
			stmt.setInt(5, newEvent.getGymId());
			stmt.setString(6, newEvent.getHostId());
			stmt.setDate(7, newEvent.getOpenAt());
			stmt.setInt(8, newEvent.getCapacity());
			stmt.setInt(9, newEvent.getCur());
			stmt.setDate(10, newEvent.getRegisterAt());
			
			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public Event findByEventId(String eventId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS WHERE ID=?");
			stmt.setString(1, eventId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getInt(9), rs.getDate(10));
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public List<Event> findAll() throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Events ORDER BY OPEN_AT ASC");

			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while (rs.next()) {
				Event one = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getInt(9), rs.getDate(10));
				events.add(one);
			}
			return events;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public List<Event> findTag(String tag) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Events where tag=? ORDER BY OPEN_AT ASC");
			
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while (rs.next()) {
				Event one = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getInt(9), rs.getDate(10));
				events.add(one);
			}
			return events;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean increaseCurrent(int userId) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("UPDATE EVENTS SET ATTENDEE = ATTENDEE+1 WHERE ID=?");
			stmt.setInt(1, userId);

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
