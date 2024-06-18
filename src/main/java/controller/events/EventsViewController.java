package controller.events;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CommentDao;
import model.dao.EventDao;
import model.dao.GymDao;
import model.dao.ParticipantDao;
import model.vo.Comment;
import model.vo.Event;
import model.vo.Gym;
import model.vo.Participants;
import model.vo.User;

@WebServlet("/events/*")
public class EventsViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// System.out.println("/events/*");
//			 int id = Integer.parseInt(request.getParameter("id"));
			String uri = request.getRequestURI();
			// System.out.println(uri);
			int id = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
			boolean already = false;
			// System.out.println(id);
			EventDao eventDao = new EventDao();
			Event event = eventDao.findById(id);
			int gymId = event.getGymId();
			GymDao gymDao = new GymDao();
			Gym gym = gymDao.findById(gymId);
			request.setAttribute("gym", gym);
			request.setAttribute("event", event);
			ParticipantDao participantDao = new ParticipantDao();
			List<Participants> participants = participantDao.findByEventId(id);
			request.setAttribute("participants", participants);
			User authUser = (User) request.getSession().getAttribute("authUser");
			for (Participants one : participants) {
				if (one.getEventId() == event.getId() && one.getUserId().equals(authUser.getId())) {
					already = true;
				}
			}
			
			request.setAttribute("already", already);
			String tab = request.getParameter("tab");
			if (tab == null) {
				request.getRequestDispatcher("/WEB-INF/view/events/view-default.jsp").forward(request, response);
			} else if (tab.equals("comments")) {
				request.getRequestDispatcher("/WEB-INF/view/events/view-comments.jsp").forward(request, response);
			// 여기서 리스트 세팅을 해야한다?
			
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}

	}

}
