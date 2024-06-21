package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BoardDao;
import model.dao.EventDao;
import model.vo.Board;
import model.vo.Event;

@WebServlet("/search")
public class SearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String q= request.getParameter("q");
		if(q == null) {
			request.getRequestDispatcher("/WEB-INF/view/search-form.jsp").forward(request, response);
		} else {
			try {
				EventDao eventDao = new EventDao();
				BoardDao boardDao = new BoardDao();
				List<Event> eventList = eventDao.searchByEvent(q);
				List<Board> boardList = boardDao.searchByBoard(q);
				int eListSize = eventList.size();
				int bListSize = boardList.size();
				
				request.setAttribute("foundEvents" ,eventList);
				request.setAttribute("foundBoards" ,boardList);
				request.setAttribute("eventSize" ,eListSize);
				request.setAttribute("boardSize" ,bListSize);
				
				
				
				request.getRequestDispatcher("/WEB-INF/view/search-result.jsp").forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}







