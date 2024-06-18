package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Comment;
import model.vo.Event;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class CommentDao {

	public boolean save(Comment newComment) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO COMMENTS VALUES (COMMENTS_SEQ.NEXTVAL, ?, ?, ?)");
			stmt.setString(1, newComment.getUserId());
			stmt.setInt(2, newComment.getEventId());
			stmt.setString(3, newComment.getBody());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public List<Comment> findAll() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Comments ORDER BY ID DESC");

			ResultSet rs = stmt.executeQuery();
			List<Comment> comments = new ArrayList<>();
			while (rs.next()) {
				Comment one = new Comment();
				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setEventId(rs.getInt("event_id"));
				one.setBody(rs.getString("body"));

				comments.add(one);
			}

			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}