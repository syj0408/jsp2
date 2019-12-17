package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. 파라미터 정보를 Member 객체에 저장
 * 2. Member 객체를 db에 추가하기.
 * 		- 성공 : 성공 메세지 출력, loginForm.me 페이지 이동
 * 		- 실패 : 실패 메세지 출력, joinForm.me 페이지 이동
 * 
 */
public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1. 파라미터 정보를 Member 객체에 저장
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));
		//2.Member 객체를 db에 추가하기.
		MemberDao dao = new MemberDao();
		String msg = "회원가입 실패";
		String url = "joinForm.me";
		if(dao.insert(mem) > 0) {
			msg = "회원가입이 완료되었습니다.";
			url = "loginForm.me";
		}
		request.setAttribute("msg", msg);				
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
}
