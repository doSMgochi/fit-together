package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/events/join/*")
public class EventJoinController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			EventDao eventDao = new EventDao();
			ParticipantDao participantDao = new ParticipantDao();
			
			String[] items = request.getRequestURI().split("/");
			int eventId = Integer.parseInt(items[items.length - 1]);
			if (authUser == null) {
				response.sendRedirect(request.getContextPath()+"/login?url=/events/" + eventId);
			}
			String authUserId = authUser.getId();
			Date joinAt = new Date(System.currentTimeMillis());

			List<Participants> participants = participantDao.findByEventId(eventId);
			List<String> userIds = new ArrayList<String>();
			for (Participants one : participants) {
				userIds.add(one.getUserId());
			}
			Event event = eventDao.findById(eventId);
			if (!userIds.contains(authUserId) && event.getCur() < event.getCapacity()) {
			Participants p = new Participants(-1, authUserId, eventId, joinAt);
			participantDao.save(p);
			eventDao.increaseCurrentById(eventId);
			}
			response.sendRedirect(request.getContextPath()+"/events/"+eventId);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
