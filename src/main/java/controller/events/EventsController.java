package controller.events;

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
import model.dao.GymDao;
import model.dao.ParticipantDao;
import model.vo.Event;
import model.vo.Participants;
import model.vo.User;
import model.vo.complex.EventWithDetail;

@WebServlet("/events")
public class EventsController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login?url=/events");
			return;
		}
		try {
			// 파라미터 뽑을 것 있는지..? 세션 뽑을 것 있는지..?
			User authUser = (User)request.getSession().getAttribute("authUser");
			
			EventDao eventDao = new EventDao();
			GymDao gymDao = new GymDao();
			ParticipantDao participantDao = new ParticipantDao();
			
			List<Event> list =eventDao.findAll();
			
			
			List<EventWithDetail> detailList = new ArrayList<>();
			for(Event e : list) {
				EventWithDetail one  = new EventWithDetail();
				one.setEvent(e);
				one.setGym(gymDao.findById( e.getGymId() ));
				
				Date openAt = e.getOpenAt();
				long gap = openAt.getTime() - System.currentTimeMillis();
				double dday= (double)gap / (1000*60*60*24);
				int ddday = (int)(Math.ceil(dday));
				
				one.setDday(ddday);
				if(authUser == null) {
					one.setJoined(false);
				}else {
					List<Participants> participants = 
							participantDao.findByEventId(e.getId());
					if(Math.random() > 0.5) {
						one.setJoined(true);
					}else {
						one.setJoined(false);
					}
				}

				detailList.add(one);
			}
			
			request.setAttribute("events", detailList);
			request.setAttribute("tagCounts", eventDao.tagCounting());
			

			
			
			
			
			
			request.getRequestDispatcher("/WEB-INF/view/events/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
