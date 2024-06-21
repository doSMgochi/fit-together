package controller.boards;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BoardDao;
import model.vo.Board;
import model.vo.User;

@WebServlet("/boards/write-handle")
public class BoardsEditHandleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login?url=/boards/write");
			return;
		}
		try {

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			String category = request.getParameter("category");
			User authUser = (User) request.getSession().getAttribute("authUser");
			String writerId = authUser.getId();
			BoardDao boardDao = new BoardDao();
			Board one = new Board(boardDao.generateKey(), writerId, title, body, new Date(System.currentTimeMillis()), 0, category);
			
			boolean r = boardDao.save(one);
			if(r) {
				response.sendRedirect(request.getContextPath() + "/boards/" + one.getId());
			} else {
				response.sendRedirect(request.getContextPath()+"/boards/list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
