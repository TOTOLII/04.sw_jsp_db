package jsp28;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BReplyViewCommand implements BCommand{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId =request.getParameter("bId");
		BDao dao= new BDao();
		BDto dto = dao.reply_view(bId);
		
		request.setAttribute("reply_view", dto);		
	}
}