
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gugudan
 */
@WebServlet("/gugudan/2")
public class gugudan extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8"); // 입력될 데이터의 문자셋은 UTF-8이다 
		resp.setContentType("text/html; charset=UTF-8"); // 출력될 문서는 html(UTF-8) 이다 
		
		if(req.getParameter("dan") == null) {
			resp.getWriter().append("단을 입력해주세요");
			return;
		}
		
		int dan = Integer.parseInt(req.getParameter("dan")); 
		int limit = 1; 
		
		if(req.getParameter("limit") != null) {
			limit = Integer.parseInt(req.getParameter("limit")); 
		}
		
		resp.getWriter().append("<h1>" + String.format("구구단 %d단", dan) + "</h1>"); 
		
		for(int i=1; i<=limit; i++) {
			resp.getWriter().append("<div>" + String.format("%d*%d = %d", dan, i , dan*i)); 
		}
	}

}


