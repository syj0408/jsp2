//C : CONTROLLER
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("hello", "Hello world");
		return new ActionForward(false,"hello.jsp");
	}

}
