package controller.boards;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BoardDao;

@WebServlet("/boards")
public class BoardsController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String category = request.getParameter("category");
			int p = request.getParameter("p") == null ? 1 : Integer.parseInt(request.getParameter("p"));
			int size = 10; // 페이지당 표시할 개수
			int start = size * (p - 1) + 1;
			int end = size * p;
			BoardDao boardDao = new BoardDao();
			int count = boardDao.countAll();
			int totalPages = count / size + (count % size > 0 ? 1 : 0);
			if (category == null) {

				request.setAttribute("boards", boardDao.findAll(start, end));

			} else if (category.equals("announcement")) {
				request.setAttribute("boards", boardDao.findAnnouncement(start, end));

			} else if (category.equals("schedule")) {
				request.setAttribute("boards", boardDao.findSchedule(start, end));

			} else if (category.equals("qna")) {
				request.setAttribute("boards", boardDao.findQna(start, end));

			} else if (category.equals("freeboard")) {
				request.setAttribute("boards", boardDao.findFreeboard(start, end));

			} else {

				request.setAttribute("boards", boardDao.findAll(start, end));
			}
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", p);
			request.getRequestDispatcher("/WEB-INF/view/boards/list.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
