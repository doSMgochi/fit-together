package controller.boards;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vo.User;
@WebServlet ("/boards/write")
public class BoardsWriteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login?url=/boards/write");
			return;
		}
		try {
			User authUser = (User)request.getSession().getAttribute("authUser");
			String user = authUser.getId();
			request.setAttribute("writerId", user);
			
			request.getRequestDispatcher("/WEB-INF/view/boards/write.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
