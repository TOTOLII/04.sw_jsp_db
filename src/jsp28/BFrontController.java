 package jsp28;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do") // 모든 파일 뒤에 확장자가 .do이면 다 이쪽으로 옴.
public class BFrontController extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //서블릿으로 상속 받아 doGet과 doPost객체가 있음.
		System.out.println("doGet");
		actionDo(request, response); // doGet이나 doPost로 넘어와도 직접 처리하지 않고 actionDO로 넘김
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);// doGet이나 doPost로 넘어와도 직접 처리하지 않고 actionDO로 넘김
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		System.out.println("actionDO");
		request.setCharacterEncoding("EUC-KR");
		
		String viewPage = null; //어떤 페이지를 보여줄 것인가 저장하는 변수 선언 (초기화 x)
		BCommand command = null; //Data Acess Object에게 적합한 해당 작업을 수행 시키기 위한 변수 선언 (초기화 x)
		
		String uri = request.getRequestURI(); // 프로젝트의 URI값을 얻어온다. 예) http://localhost/test/test.jsp의 경우 모든 주소를 다 얻어온다.
		String conPath = request.getContextPath(); // 프로젝트의 path를 얻어온다. 예) http://localhost/test/test.jsp의 경우 http://~/test경로 까지만 얻어온다.
		String com = uri.substring(conPath.length()); // 프로젝트의 명.확장자를 얻어오기 위해 URI에서 Path를 뺀다. 위의 예를 보고 설명하자면, /test.jsp를 얻어오는 것.
		String ext[] = com.split("/");
		viewPage = "/jsp29/";
		
		
		if(ext[2].equals("write_view.do")) {
			viewPage += "write_view.jsp";
		} else if(ext[2].equals("write.do")) {
			System.out.println("It's work in bfcontroller write.do");
			command = new BWriteCommand();
			command.excute(request, response);
			viewPage = "list.do";
		} else if (ext[2].equals("list.do")) {
			command = new BListCommand(); //만들어 두었던 command 변수에 BListCommand라는 변수를 새로 생성해서 할당
		 	command.excute(request, response);
			viewPage += "list.jsp";
		} else if (ext[2].equals("content_view.do")) {
			System.out.println("sucess_BFrontcotroller");
			command = new BContentCommand();
			command.excute(request, response);
			viewPage +="content_view.jsp";
		} else if (ext[2].equals("modify.do")) {
			command = new BModifyCommand();
			command.excute(request, response);
			viewPage = "list.do";
		} else if (ext[2].equals("delete.do")) {
			command = new BDeleteCommand();
			command.excute(request, response);
			viewPage = "list.do";
		} else if (ext[2].equals("reply_view.do")) {
			command = new BReplyViewCommand();
			command.excute(request, response);
			viewPage += "reply_view.jsp";	
		} else if (ext[2].contentEquals("reply.do")) {
			command = new BReplyCommand();
			command.excute(request, response);
			viewPage = "list.do";
		}

		System.out.println("viewPage : " + viewPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //지정한 viewPage로 넘김.
		dispatcher.forward(request, response);
	}
}
