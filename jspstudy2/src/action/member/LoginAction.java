package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. id,pass 파라미터 저장
 * 2. id 해당하는 정보를 db에서 조회
 * 		- 존재하면 비밀번호 확인
 * 		- 존재하지 않으면 id없음 메세지를 출력하고 loginForm.me 페이지로 이동
 * 3. id가 존재하면 비밀번호 검증
 * 		-비밀번호가 맞는 경우: session 로그인 정보 저장
 * 						로그인 성공 메세지를 출력 , main.me 페이지로 이동
 * 		-비밀번호가 틀리는 경우: 로그인 실패 메세지를 출력 , loginForm.me 페이지로 이동

 * 
 * */
public class LoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.id,pass 파라미터 저장
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String msg = "해당 id가 존재하지 않습니다.";
		String url = "loginFrom.me";
		//id 해당하는 정보를 db에서 조회
		Member mem = new MemberDao().selectOne(id);
		//비밀번호 검증
		if(mem != null) { 
			//로그인 성공
			if(pass.equals(mem.getPass())) {
				request.getSession().setAttribute("login",id); //session.setAttribute("login",id); session은 jsp내장객체라 requset.getsession()
				msg = mem.getName() + "님이 로그인하셨습니다.";
				url = "main.me";
			}else {
			//로그인 실패
				msg = "비밀번호가 틀립니다.";		
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return new ActionForward(false,"../alert.jsp");
	}
}
