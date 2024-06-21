package controller.gyms;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.GymDao;

@WebServlet("/gyms")
public class GymsController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int p = request.getParameter("p") == null ? 1 : Integer.parseInt(request.getParameter("p"));
			int size = 10; // 페이지당 표시할 개수
//			int start = size * p - size + 1;
			int start = size * (p - 1) + 1;
			int end = size * p;

			GymDao gymDao = new GymDao();
			int count = gymDao.countAll();
			int totalPages = count/size + (count%size >0 ? 1: 0); 
			
			request.setAttribute("gyms", gymDao.findAll(start, end));
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage",  p);
			
			
			request.getRequestDispatcher("/WEB-INF/view/gyms/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
