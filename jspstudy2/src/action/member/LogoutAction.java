package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;

public class LogoutAction extends UserloginAction { //실제 컨트롤러가 UserloginAction여기에있는 doexecute를 호출

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		String msg = login + "님이 로그아웃 하셨습니다.";
		String url = "loginForm.me";
		request.getSession().invalidate();
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		return new ActionForward(false,"../alert.jsp");
	}
}
