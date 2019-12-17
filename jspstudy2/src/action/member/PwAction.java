package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;

public class PwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   String id = request.getParameter("id");
		   String email = request.getParameter("email");
		   String tel = request.getParameter("tel");
		   String msg = "비밀번호를 찾았습니다.";
		   String url = "loginForm.me";
		   MemberDao dao = new MemberDao();
		   String pass = dao.pwSearch(id,email,tel); 
		   if(pass != null) {
			   pass = pass.substring(2,pass.length());
			   request.setAttribute("pass", pass);
			   return new ActionForward();
		   }else {
			   msg = "정보에 맞는 비밀번호를 찾을 수 없습니다.";
			   url = "pwForm.me";
			   request.setAttribute("mgs", msg);
			   request.setAttribute("url", url);
			return  new ActionForward(false,"../alert.jsp");
		   }

   }
}