

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/usr/home/gugudan2")
public class HomeServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			resp.setCharacterEncoding("UTF-8"); // 입력될 데이터의 문자셋은 UTF-8이다 
			resp.setContentType("text/html; charset=UTF-8"); // 출력될 문서는 html(UTF-8) 이다 
			
			if(req.getParameter("dan") == null) {
				resp.getWriter().append("단을 입력해주세요");
				return;
			}
			
			int dan = Integer.parseInt(req.getParameter("dan")); 
			int limit = 9; 
			
			if(req.getParameter("limit") != null) {
				limit = Integer.parseInt(req.getParameter("limit")); 
			}
			
		
			req.setAttribute("dan", dan);
			req.setAttribute("limit", limit);
			
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/usr/home/gugudan2.jsp");
			rd.forward(req, resp);
				
			
		}

}
