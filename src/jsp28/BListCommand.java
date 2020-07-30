package jsp28;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BListCommand implements BCommand{ //BCommand를 상속받음.

	@Override //상속 받은 추상메소드 excute를 재작성하여 사용
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao(); //dao객체 생성 (데이터베이스에 접근해서 로직을 수행하기 위해서 생성함)
		ArrayList<BDto> dtos = dao.list(); //dao에서 list객체 호출
		request.setAttribute("list", dtos); // 받아온 결과값의 속성을 지정한다.
	}
}
