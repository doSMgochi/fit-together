package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.Gym;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class GymDao {
	
	public Gym findByUserId(String userId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GYMS WHERE ID=?");
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Gym(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Gym findByType(String type) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GYMS WHERE TYPE=?");
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Gym(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Gym findByTypeDistinct() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT TYPE FROM GYMS");
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Gym(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
}
