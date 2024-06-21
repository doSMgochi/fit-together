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
import model.vo.Statistics;
import model.vo.User;

@WebServlet("/events/*")
public class EventsViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login?url=/events");
			return;
		}
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
			List<Statistics> statistics = participantDao.findStatisticsByEventId(event.getId());
			float avgAge = 0;
			int count = 0;
			int male = 0;
			int female = 0;
			for (Statistics one : statistics) {
				count++;
				avgAge += one.getAge() * one.getAgeCount();
				if (one.getGender().equals("남")) {
					male++;
				}
				else if (one.getGender().equals("여")) {
					female++;
				}
			}
			avgAge = avgAge / count;
			request.setAttribute("avgAge", avgAge);
			request.setAttribute("male", male);
			request.setAttribute("female", female);
			
			request.setAttribute("statistics", statistics);
			
			request.setAttribute("already", already);
			String tab = request.getParameter("tab");
			if (tab == null) {
				request.getRequestDispatcher("/WEB-INF/view/events/view-default.jsp").forward(request, response);
			} else if (tab.equals("comments")) {
				CommentDao commentDao = new CommentDao();
				List<Comment> printComment = commentDao.findAll(event.getId());
				int commentCount = printComment.size();
				request.setAttribute("commentCount", commentCount);
				request.setAttribute("printComment", printComment);
				
				request.getRequestDispatcher("/WEB-INF/view/events/view-comments.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}

	}

}
