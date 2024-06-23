package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Board;
import model.vo.Event;
import model.vo.Gym;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class BoardDao {
	
	public int generateKey() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT BOARDS_SEQ.NEXTVAL FROM DUAL");
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
	
	public int countAll() throws SQLException{
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM BOARDS");
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
	
	public boolean save(Board newBoard) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("insert into Boards values(?, ?, ?, ?, ?, 0 , ?)");
			
			stmt.setInt(1, newBoard.getId());
			stmt.setString(2, newBoard.getWriterId());
			stmt.setString(3, newBoard.getTitle());
			stmt.setString(4, newBoard.getBody());
			stmt.setDate(5, newBoard.getWritedAt());
			stmt.setString(6, newBoard.getCategory());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean increaseReadCntById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("update Boards set read_cnt=read_cnt+1 where id=?");
			stmt.setInt(1, id);

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean edit(Board oldBoard) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		
		try (Connection conn = ods.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement("UPDATE Boards SET TITLE=? , BODY=? WHERE id =?");
			stmt.setString(1, oldBoard.getTitle());
			stmt.setString(2, oldBoard.getBody());
			stmt.setInt(3, oldBoard.getId());
			
			int r = stmt.executeUpdate();
			
			return r == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public Board findById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Boards WHERE id=?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM Boards WHERE id=?");
			stmt.setInt(1, id);

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Board> findAll(int start, int end) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("select * from (select g.*, rownum rn from (select * from boards order by id desc) g) where rn between ? and ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			List<Board> boards = new ArrayList<Board>();
			while (rs.next()) {
				Board one = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
				boards.add(one);
			}
			return boards;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Board> findAnnouncement(int start, int end) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("select * from (select g.*, rownum rn from (select * from boards where category = '공지사항' order by id desc) g) where rn between ? and ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			List<Board> boards = new ArrayList<Board>();
			while (rs.next()) {
				Board one = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
				boards.add(one);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Board> findSchedule(int start, int end) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("select * from (select g.*, rownum rn from (select * from boards where category = '대회일정' order by id desc) g) where rn between ? and ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			List<Board> boards = new ArrayList<Board>();
			while (rs.next()) {
				Board one = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
				boards.add(one);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Board> findQna(int start, int end) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("select * from (select g.*, rownum rn from (select * from boards where category = '질문/답변' order by id desc) g) where rn between ? and ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			List<Board> boards = new ArrayList<Board>();
			while (rs.next()) {
				Board one = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
				boards.add(one);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Board> findFreeboard(int start, int end) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("select * from (select g.*, rownum rn from (select * from boards where category = '자유게시판' order by id desc) g) where rn between ? and ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			List<Board> boards = new ArrayList<Board>();
			while (rs.next()) {
				Board one = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
				boards.add(one);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Board> searchByBoard(String search) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//13.124.229.167:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = 
					conn.prepareStatement("SELECT * FROM BOARDS WHERE TITLE LIKE ? OR BODY LIKE ? OR CATEGORY LIKE ? ORDER BY ID DESC");
			stmt.setString(1, "%"+search+"%");
			stmt.setString(2, "%"+search+"%");
			stmt.setString(3, "%"+search+"%");
			ResultSet rs = stmt.executeQuery();
			List<Board> boards = new ArrayList<Board>();
			while (rs.next()) {
				Board one = new Board();
				one.setId(rs.getInt("id"));
				one.setWriterId(rs.getString("writer_id"));
				one.setTitle(rs.getString("title"));
				one.setBody(rs.getString("body"));
				one.setWritedAt(rs.getDate("writed_at"));
				one.setReadCnt(rs.getInt("read_cnt"));
				one.setCategory(rs.getString("category"));

				boards.add(one);
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
