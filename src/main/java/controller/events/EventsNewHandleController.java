package controller.events;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.EventDao;
import model.dao.ParticipantDao;
import model.vo.Event;
import model.vo.Participants;
import model.vo.User;

@WebServlet("/events/new-handle")
public class EventsNewHandleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User authUser = (User) request.getSession().getAttribute("authUser");
			String hostId = authUser.getId();
			Date registerAt = new Date(System.currentTimeMillis());
			EventDao eventDao = new EventDao();
			int id = eventDao.generateKey();
			Event event = new Event(id, request.getParameter("title"), request.getParameter("description"),
					request.getParameter("tag"), Integer.parseInt(request.getParameter("gymId")), hostId,
					Date.valueOf(request.getParameter("openAt")), Integer.parseInt(request.getParameter("capacity")), 1,
					registerAt);

			boolean r = eventDao.save(event);

			if (r) {
				Participants participants = new Participants(0, hostId, id, registerAt);
				ParticipantDao participantDao = new ParticipantDao();
				participantDao.save(participants);
			}
			// response.sendRedirect(request.getContextPath()+"/events/view?id=111111");
			// response.sendRedirect(request.getContextPath()+"/events/1111111");
			response.sendRedirect(request.getContextPath()+"/events/"+id);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}

	}
}
