package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BoardDao;

@WebServlet("/index")
public class IndexController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BoardDao boardDao = new BoardDao();
			request.setAttribute("all", boardDao.findAll(1, 10));
			request.setAttribute("announcement", boardDao.findAnnouncement(1, 10));
			request.setAttribute("schedule", boardDao.findSchedule(1, 10));
			request.setAttribute("qna", boardDao.findQna(1, 10));
			request.setAttribute("freeboard", boardDao.findFreeboard(1, 10));
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
