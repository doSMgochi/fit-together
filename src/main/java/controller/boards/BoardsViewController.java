package controller.boards;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BoardDao;
import model.vo.Board;
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
			boolean already = false;
			boolean r = boardDao.increaseReadCntById(id);
			request.setAttribute("board", board);
			request.setAttribute("already", already);
			if (request.getSession().getAttribute("authUser") != null) {
				User authUser = (User) request.getSession().getAttribute("authUser");
				String writerId = authUser.getId();
				request.setAttribute("writerId", writerId);
			}
			String tab = request.getParameter("tab");
			if (tab == null) {
				request.getRequestDispatcher("/WEB-INF/view/boards/view-default.jsp").forward(request, response);
			} else if (tab.equals("comments")) {
//				CommentDao commentDao = new CommentDao();
//				List<Comment> printComment = commentDao.findAll(event.getId());
//				int commentCount = printComment.size();
//				request.setAttribute("commentCount", commentCount);
//				request.setAttribute("printComment", printComment);

				request.getRequestDispatcher("/WEB-INF/view/boards/view-comments.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}
	}
}
