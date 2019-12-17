package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;

/* 기능
 * 1. 로그아웃 상태인 경우. 로그인하세요. 메세지 출력.
 * 	   다른 사용자의 비밀번호 변경 불가.
 * 	 Opener 페이지를  loginForm.me 페이지 이동.
 * 	  현재페이지 닫기
 * 2. pass,chgpass 파라미터 값 저장
 * 3. pass 비밀번호가 db에 저장된 비밀번호와 틀리면
 * 	    비밀번호 오류 메세지 출력. passwordForm.me 페이지이동
 * 4. pass 비밀번호가 db에 저장된 비밀번호와 같으면
 * 	    비밀번호 수정. 
 * 	  opener 페이지를 info.me 페이지 이동.
 * 	     현재 페이지닫기 
 * 
 */
public class PasswordAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		boolean opener = false;
		boolean closer = false;
		String msg = "비밀번호 오류입니다.확인하세요.";
		String url = "passwordForm.me";
		if(login == null || login.trim().equals("")){ //로그아웃 상태
			opener  = true;
			closer = true;
			msg = "로그인하세요.";
			url = "loginForm.me";
		}else {//로그인
			String pass = request.getParameter("pass");
			String chgpass = request.getParameter("chgpass");
			MemberDao dao= new MemberDao();
			Member mem = dao.selectOne(login); //login은 값이있는애드람ㄴ 들어옴
			if(pass.equals(mem.getPass())){ //입력된 비밀번호와 db에 입력된 비밀번호가 같은 경우
				closer = true;
				if(dao.updatePass(login,chgpass) > 0) {
					opener = true;
					msg ="비밀번호가 변경되었습니다.";
					url="info.me?id=" + login;
				}else{
					msg = "비밀번호 변경중 오류가 발생했습니다.";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("opener", opener);
		request.setAttribute("closer", closer);
		return new ActionForward();
	}

}
