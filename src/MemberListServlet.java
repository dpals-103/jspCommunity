
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/usr/member/memberList")
public class MemberListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* 필수문구 (언어타입 선언) */
		resp.setCharacterEncoding("UTF-8"); // 입력될 데이터의 문자셋은 UTF-8이다
		resp.setContentType("text/html; charset=UTF-8"); // 출력될 문서는 html(UTF-8) 이다

		MysqlUtil.setDBInfo("127.0.0.1", "dpals103", "dlgywn0168", "jspCommunity");
		//MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		List<Map<String,Object>> memberMapList = 
				MysqlUtil.selectRows(new SecSql().append("select * from member order by id desc"));
		
		MysqlUtil.closeConnection();

		req.setAttribute("memberMapList", memberMapList);
		req.getRequestDispatcher("/jsp/usr/member/memberList.jsp").forward(req, resp);
	}

}
