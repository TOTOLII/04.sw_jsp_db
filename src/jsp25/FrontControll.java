package jsp25;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControll
 */
@WebServlet("*.do") //모든 파일 중 뒤의 확장자가 do이면 값을 받아와라.
public class FrontControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request, response);
	}
	
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("actionDo");
		
		String uri = request.getRequestURI(); // uri 가지고 오기
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath(); //파일 주소 가지고 오기
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length()); //substring = 문자열 자르기, 파일 주소만큼 자른다음 남은 주소를 command에 저장
		System.out.println("command : " + command); 
		
		if(command.equals("/insert.do")) {
			System.out.println("insert");
			System.out.println("---------------");
		} else if (command.equals("/update.do")) {
			System.out.println("update");
			System.out.println("---------------");
		} else if (command.equals("/select.do")) {
			System.out.println("select");
			System.out.println("---------------");
		} else if (command.equals("/delete.do")) {
			System.out.println("delete");
			System.out.println("---------------");
		}
	}
}
