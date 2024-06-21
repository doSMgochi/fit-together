package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Participants;
import model.vo.Statistics;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class ParticipantDao {
	public boolean save(Participants participant) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO PARTICIPANTS VALUES (PARTICIPANTS_SEQ.NEXTVAL, ?, ?, ?)");
			stmt.setString(1, participant.getUserId());
			stmt.setInt(2, participant.getEventId());
			stmt.setDate(3, participant.getJoinAt());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Participants> findByEventId(int eventId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PARTICIPANTS WHERE EVENT_ID=?");
			stmt.setInt(1, eventId);

			ResultSet rs = stmt.executeQuery();
			List<Participants> participants = new ArrayList<>();
			while (rs.next()) {
				Participants one = new Participants();

				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setEventId(rs.getInt("event_id"));
				one.setJoinAt(rs.getDate("join_at"));
				participants.add(one);
			}

			return participants;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Statistics> findStatisticsByEventId(int eventId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement(
					"SELECT P.ID, P.USER_ID, U.AGE, U.AGECOUNT, U.GENDER, P.EVENT_ID, P.JOIN_AT FROM (SELECT 2025-BIRTH AGE, COUNT(2025-BIRTH) AGECOUNT, GENDER, ID FROM USERS GROUP BY BIRTH, GENDER, ID ORDER BY BIRTH) U JOIN PARTICIPANTS P ON U.ID = P.USER_ID WHERE P.EVENT_ID = ?");
			stmt.setInt(1, eventId);

			ResultSet rs = stmt.executeQuery();
			List<Statistics> statistics = new ArrayList<>();
			while (rs.next()) {
				Statistics one = new Statistics();

				one.setParticipantsId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setAge(rs.getInt("age"));
				one.setAgeCount(rs.getInt("agecount"));
				one.setGender(rs.getString("gender"));
				one.setJoinAt(rs.getDate("join_at"));
				statistics.add(one);
			}

			return statistics;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
