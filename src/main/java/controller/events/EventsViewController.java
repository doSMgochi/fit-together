package controller.events;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.EventDao;
import model.dao.GymDao;
import model.dao.ParticipantDao;
import model.vo.Event;
import model.vo.Gym;
import model.vo.Participants;

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
			// System.out.println(id);
			EventDao eventDao = new EventDao();
			Event event =eventDao.findById(id);
			int gymId = event.getGymId();
			GymDao gymDao = new GymDao();
			Gym gym = gymDao.findById(gymId);
			request.setAttribute("gym", gym);
			request.setAttribute("event", event);
			ParticipantDao participantDao = new ParticipantDao();
			List<Participants> participants =participantDao.findByEventId(id);
			request.setAttribute("participants", participants);
			
			request.getRequestDispatcher("/WEB-INF/view/events/view.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}

	}

}
