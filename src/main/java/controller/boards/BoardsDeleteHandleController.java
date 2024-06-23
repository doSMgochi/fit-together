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

@WebServlet("/boards/delete-handle")
public class BoardsDeleteHandleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login?url=/boards/delete");
			return;
		}
		try {
			  // ID 파라미터 추출
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/events/error");
                return;
            }
            int id = Integer.parseInt(idStr);
			BoardDao boardDao = new BoardDao();
			Board board = boardDao.findById(id);

			if (board == null) {
				response.sendRedirect(request.getContextPath() + "/events/error");
				return;
			}
			User authUser = (User) request.getSession().getAttribute("authUser");

			if (board.getWriterId().equals(authUser.getId())) {
				boolean r = boardDao.deleteById(id);
				if (r) {
					response.sendRedirect(request.getContextPath() + "/boards");
				} else {
					response.sendRedirect(request.getContextPath() + "/events/error");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/boards/" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/events/error");
		}
	}
}
