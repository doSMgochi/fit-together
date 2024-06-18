package controller.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CommentDao;
import model.vo.Comment;
import model.vo.User;

@WebServlet("/events/comments")
public class EventsCommentController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Comments
			String comments = request.getParameter("comments");
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			User authUser = (User) request.getSession().getAttribute("authUser");
			Comment c = new Comment(0, authUser.getId(), eventId, comments);
			CommentDao commentDao = new CommentDao();
			boolean r = commentDao.save(c);
			if (r) {
			}
			List<Comment> comment = new ArrayList<Comment>();
			comment.add(c);
			// 이부분! 리다이렉트를 하면 안 찍히는 듯 함
			List<Comment> printComment = commentDao.findAll();
			int commentCount = printComment.size();
			request.setAttribute("commentCount", commentCount);
			request.setAttribute("printComment", printComment);
			response.sendRedirect(request.getContextPath() + "/events/"+eventId+"?tab=comments");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
