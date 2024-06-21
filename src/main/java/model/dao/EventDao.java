package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Event;
import model.vo.complex.EventTagCount;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class EventDao {

	public int generateKey() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT EVENTS_SEQ.NEXTVAL FROM DUAL");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int key = rs.getInt("nextval");
				return key;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public List<EventTagCount> tagCounting() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT TAG, COUNT(*) FROM EVENTS GROUP BY TAG");
			ResultSet rs = stmt.executeQuery();
			List<EventTagCount> tagCount = new ArrayList<EventTagCount>();
			while (rs.next()) {
				EventTagCount one = new EventTagCount();
				one.setTag(rs.getString(1));
				one.setCount(rs.getInt(2));

				tagCount.add(one);
			}
			return tagCount;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Event> searchByEvent(String search) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = 
					conn.prepareStatement("SELECT * FROM EVENTS WHERE TITLE LIKE ? OR DESCRIPTION LIKE ? ORDER BY OPEN_AT ASC");
			stmt.setString(1, "%"+search+"%");
			stmt.setString(2, "%"+search+"%");
			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while (rs.next()) {
				Event one = new Event();
				one.setId(rs.getInt("id"));
				one.setTitle(rs.getString("title"));
				one.setDescription(rs.getString("title"));
				one.setTag(rs.getString("tag"));
				one.setGymId(rs.getInt("gym_id"));
				one.setHostId(rs.getString("host_id"));
				one.setOpenAt(rs.getDate("open_at"));
				one.setCapacity(rs.getInt("capacity"));
				one.setCur(rs.getInt("cur"));
				one.setRegisterAt(rs.getDate("register_at"));

				events.add(one);
			}

			return events;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean save(Event event) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO EVENTS VALUES(?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, event.getId());
			stmt.setString(2, event.getTitle());
			stmt.setString(3, event.getDescription());
			stmt.setString(4, event.getTag());
			stmt.setInt(5, event.getGymId());
			stmt.setString(6, event.getHostId());
			stmt.setDate(7, event.getOpenAt());
			stmt.setInt(8, event.getCapacity());
			stmt.setInt(9, event.getCur());
			stmt.setDate(10, event.getRegisterAt());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Event> findAll() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS ORDER BY OPEN_AT ASC");

			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while (rs.next()) {
				Event one = new Event();
				one.setId(rs.getInt("id"));
				one.setTitle(rs.getString("title"));
				one.setDescription(rs.getString("description"));
				one.setTag(rs.getString("tag"));
				one.setGymId(rs.getInt("gym_id"));
				one.setHostId(rs.getString("host_id"));
				one.setOpenAt(rs.getDate("open_at"));
				one.setCapacity(rs.getInt("capacity"));
				one.setCur(rs.getInt("cur"));
				one.setRegisterAt(rs.getDate("register_at"));

				events.add(one);
			}

			return events;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Event> findByTag(String tag) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@/13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("select * from events where tag=? order by open_at");
			stmt.setString(1, tag);

			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while (rs.next()) {
				Event one = new Event();
				one.setId(rs.getInt("id"));
				one.setTitle(rs.getString("title"));
				one.setDescription(rs.getString("description"));
				one.setTag(rs.getString("tag"));
				one.setGymId(rs.getInt("gym_id"));
				one.setHostId(rs.getString("host_id"));
				one.setOpenAt(rs.getDate("open_at"));
				one.setCapacity(rs.getInt("capacity"));
				one.setCur(rs.getInt("cur"));
				one.setRegisterAt(rs.getDate("register_at"));

				events.add(one);
			}

			return events;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Event findById(int id) throws SQLException {

		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS WHERE ID=?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Event one = new Event();
				one.setId(rs.getInt("id"));
				one.setTitle(rs.getString("title"));
				one.setDescription(rs.getString("description"));
				one.setTag(rs.getString("tag"));
				one.setGymId(rs.getInt("gym_id"));
				one.setHostId(rs.getString("host_id"));
				one.setOpenAt(rs.getDate("open_at"));
				one.setCapacity(rs.getInt("capacity"));
				one.setCur(rs.getInt("cur"));
				one.setRegisterAt(rs.getDate("register_at"));
				return one;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean increaseCurrentById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt;
			stmt = conn.prepareStatement("UPDATE EVENTS SET CUR = CUR + 1 WHERE ID=?");
			stmt.setInt(1, id);

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
