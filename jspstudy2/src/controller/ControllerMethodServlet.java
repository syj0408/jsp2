package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import action.board.BoardAllAction;

@WebServlet(urlPatterns = {"*.do"}, //*.do가호출되면
initParams = {@WebInitParam(name="properties", //여기를호출
value = "method.properties")})
public class ControllerMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Properties pr = new Properties();
	private BoardAllAction action = new BoardAllAction();
	private Class[] paramType = new Class[] {HttpServletRequest.class,HttpServletResponse.class};
	//init : 서블릿이 객체화 된 이후 바로 호출되는 메서드. 앞에 static이없어서 객체화가됨
	//config 객체 : 파라미터 전달
	//		 properties=method.properties
	@Override
	public void init(ServletConfig config) throws ServletException {
		//props : mthod.properties
		String props = config.getInitParameter("properties");
		FileInputStream f = null;
		try {
		// f : WEB-INF/method.properties 파일 읽기 위한 입력 스트림
			f = new FileInputStream(config.getServletContext().getRealPath("/") + "WEB-INF/"+props);
			//key = /model2/hello.do
			//value = hello
			pr.load(f);
			f.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//doGet : 클라이언트  GET방식요청시 호출되는 메서드
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		Object[] paramObjs = new Object[] {request, response};
		ActionForward forward = null;
		String command = null;
		try {
			//System.out.println(request.getRequestURI());
			//System.out.println(request.getContextPath());
			//프로젝트를 제외한 요청 url  : /model2/hello.do
			command = request.getRequestURI().substring(request.getContextPath().length());
			//methodName : hello
			String methodName = pr.getProperty(command);
			//method : 메서드 객체 생성
			//BoardAllAction 클래스 총 메서드의 이름이 methodName 이고 
			//매개변수가 paramType인 메서드를 객체로 리턴
			Method method = BoardAllAction.class.getMethod(methodName, paramType);
			//invoke : 메서드 호출 기능.
			// 			action.hello(request, response) 호출
			forward = (ActionForward)method.invoke(action, paramObjs);
		}catch (NullPointerException e) {
			forward = new ActionForward();
			e.printStackTrace();
		}catch(Exception e) {
			throw new ServletException(e);
		}
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getView());
			}else {
				if(forward.getView()==null) {
					forward.setView(command.replace(".do", ".jsp"));
				}
				RequestDispatcher disp= request.getRequestDispatcher(forward.getView());
				disp.forward(request, response);
			}
		}else response.sendRedirect("nopage.jsp");
	}
	//클라이언트 POST방식 요청시 호출되는 메서드
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
