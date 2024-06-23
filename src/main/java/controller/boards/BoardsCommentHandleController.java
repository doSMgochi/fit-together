package controller.boards;

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

@WebServlet("/boards/comments")
public class BoardsCommentHandleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath() + "/boards/"+request.getParameter("boardId"));
			return;
		}
		try {
			// Comments
			String comments = request.getParameter("comments");
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			User authUser = (User) request.getSession().getAttribute("authUser");
			Comment c = new Comment(0, authUser.getId(), -1, boardId, comments);
			CommentDao commentDao = new CommentDao();
			boolean r = commentDao.boardCommentSave(c);
			if (r) {
			}
			List<Comment> comment = new ArrayList<Comment>();
			comment.add(c);
			
			response.sendRedirect(request.getContextPath() + "/boards/"+boardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
