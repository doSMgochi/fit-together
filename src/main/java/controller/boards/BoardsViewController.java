package controller.boards;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BoardDao;
import model.dao.CommentDao;
import model.vo.Board;
import model.vo.Comment;
import model.vo.User;

@WebServlet("/boards/*")
public class BoardsViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BoardDao boardDao = new BoardDao();
			String uri = request.getRequestURI();
			int id = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
			Board board = boardDao.findById(id);

			String body = board.getBody().replace("\n", "<br/>");
			board.setBody(body);

			boolean already = false;
			boolean authorCheck = false;
			boolean r = boardDao.increaseReadCntById(id);
			request.setAttribute("board", board);
			request.setAttribute("already", already);
			if (request.getSession().getAttribute("authUser") != null) {
				User authUser = (User) request.getSession().getAttribute("authUser");
				String writerId = authUser.getId();
				if (board.getWriterId().equals(writerId)) {
					authorCheck = true;
					request.setAttribute("authorCheck", authorCheck);
				}
			}

			CommentDao commentDao = new CommentDao();
			List<Comment> printComment = commentDao.boardCommentfindAll(board.getId());
			int commentCount = printComment.size();
			request.setAttribute("commentCount", commentCount);
			request.setAttribute("printComment", printComment);

			request.getRequestDispatcher("/WEB-INF/view/boards/view.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}
	}
}
