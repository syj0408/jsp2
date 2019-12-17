package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

public abstract class UserloginAction implements Action {
	protected String login; //로그인 정보
	protected String id; //id 정보
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		id =request.getParameter("id");
	//	System.out.println(login+"," + id);
		if(login == null || login.trim().equals("")) {
			request.setAttribute("msg", "로그인하세요");
			request.setAttribute("url", "loginForm.jsp");
			return new ActionForward(false,"../alert.jsp"); //여기실행하면 return doExecute(request,response);을 실행안함.
		}else {
			  if(!login.equals("admin") && id != null && !login.equals(id)) {
				request.setAttribute("msg", "본인만 가능합니다.");
				request.setAttribute("url", "main.jsp");
				return new ActionForward(false,"../alert.jsp");
			}
		}
		return doExecute(request,response);
	}
protected abstract ActionForward doExecute(HttpServletRequest request , HttpServletResponse response);
}
