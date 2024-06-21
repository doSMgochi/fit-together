package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Gym;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class GymDao {
	
	public int countAll() throws SQLException{
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM GYMS");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Gym findById(int id) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GYMS WHERE ID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Gym one = new Gym();

				one.setId(rs.getInt("id"));
				one.setName(rs.getString("name"));
				one.setType(rs.getString("type"));
				one.setRegion(rs.getString("region"));
				one.setAgency(rs.getString("agency"));
				one.setManager(rs.getString("manager"));
				return one;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Gym> findByType(String type) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GYMS WHERE TYPE=?");
			stmt.setString(1, type);
			
			
			ResultSet rs = stmt.executeQuery();
			List<Gym> gyms = new ArrayList<Gym>();
			while (rs.next()) {
				Gym one = new Gym();

				one.setId(rs.getInt("id"));
				one.setName(rs.getString("name"));
				one.setType(rs.getString("type"));
				one.setRegion(rs.getString("region"));
				one.setAgency(rs.getString("agency"));
				one.setManager(rs.getString("manager"));
				gyms.add(one);
			}

			return gyms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Gym> findAll(int start, int end) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement("select * from (select g.*, rownum rn from (select * from gyms order by name) g) where rn between ? and ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			
			ResultSet rs = stmt.executeQuery();
			List<Gym> gyms = new ArrayList<Gym>();
			while (rs.next()) {
				Gym one = new Gym();
				
				one.setId(rs.getInt("id"));
				one.setName(rs.getString("name"));
				one.setType(rs.getString("type"));
				one.setRegion(rs.getString("region"));
				one.setAgency(rs.getString("agency"));
				one.setManager(rs.getString("manager"));
				gyms.add(one);
			}
			
			return gyms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> findDistinctType() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT TYPE FROM GYMS ");

			ResultSet rs = stmt.executeQuery();
			List<String> types = new ArrayList<>();
			while (rs.next()) {
				String type = rs.getString("type");
				types.add(type);
			}

			return types;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
