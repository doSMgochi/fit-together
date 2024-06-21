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
@WebServlet ("/boards/edit")
public class BoardsEditController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BoardDao boardDao = new BoardDao();
			int id = Integer.parseInt(request.getParameter("id"));
			Board board = boardDao.findById(id);
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			if (board == null || board.getWriterId() == null || !board.getWriterId().equals(authUser.getId())) {
				response.sendRedirect(request.getContextPath()+"/boards/"+id);
			} else {
				boolean r = boardDao.edit(board);
				request.setAttribute("editPossible", r);
				request.setAttribute("board", board);
				request.getRequestDispatcher("/WEB-INF/view/boards/edit.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
