package jsp28;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BCommand {
	
	void excute(HttpServletRequest request, HttpServletResponse response); //excute라는 추상 메소를 하나 가지고 있음
	

}
