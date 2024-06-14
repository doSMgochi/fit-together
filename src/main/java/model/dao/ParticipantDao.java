package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.Participants;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class ParticipantDao {
	public boolean save(Participants newParticipant) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO PARTICIPANTS VALUE (PARTICIPANTS_SEQ.NEXTVAL,  ?, ?, ?)");
			stmt.setInt(1, newParticipant.getId());
			stmt.setString(2, newParticipant.getUserId());
			stmt.setDate(3, newParticipant.getJoinAt());
			
			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public Participants findByParticipantsEventId(String eventId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * PARTICIPANTS WHERE EVENT_ID=?");
			stmt.setString(1, eventId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Participants(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Participants findByParticipantsUserId(String userId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * PARTICIPANTS WHERE USER_ID=?");
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Participants(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
