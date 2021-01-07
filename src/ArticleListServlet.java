
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/usr/article/articleList")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("127.0.0.1", "dpals103", "dlgywn0168", "jspCommunity");
		// MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");


		// 게시판코드
		String boardCode = req.getParameter("boardCode");
		if (boardCode == null) {
			resp.getWriter().append("게시판 코드를 입력해주세요");
			return; 
		}
		
		// 게시판 이름
		String category = MysqlUtil
				.selectRowStringValue(new SecSql().append("select category from board where code =?", boardCode));

		// 게시판별 게시글 리스트
		List<Map<String, Object>> articleList = MysqlUtil
				.selectRows(new SecSql().append("select * from article where boardCode=? order by id desc", boardCode));
		MysqlUtil.closeConnection();

		req.setAttribute("boardCode", boardCode);
		req.setAttribute("category", category);
		req.setAttribute("articleList", articleList);

		req.getRequestDispatcher("/jsp/usr/article/articleList.jsp").forward(req, resp);
	}

}
