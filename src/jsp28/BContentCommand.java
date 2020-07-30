package jsp28;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BContentCommand implements BCommand{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId); //해당 아이디에 해당하는 내용들 하나만 가지고 오면 됨
		System.out.println("sucess BContentCommand");
		request.setAttribute("content_view", dto);
	}
}
