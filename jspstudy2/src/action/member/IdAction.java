package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;

public class IdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String  id = request.getParameter("id");
		if(id == null) { //id찾기
			return idfind(request,tel,email);
		}else { //pw 찾기
			return pwSearch(request,id,tel,email);
		}
	}
	private ActionForward pwSearch(HttpServletRequest request, String id, String tel, String email) {
		MemberDao dao = new MemberDao();
//		String msg = null;
//		String url = null;
		String pw = dao.pwSearch(id, email, tel);
		if(pw != null) { //비밀번호 찾기 성공
			request.setAttribute("pass", pw.substring(2, pw.length()));
			return new ActionForward();
		}else { //비밀번호  찾기 실패			
		//	msg = "정보에 맞는 id를 찾을 수 없습니다.";
		//	url = "idForm.me";
			  request.setAttribute("msg", "정보에 맞는 id를 찾을 수 없습니다.");
			  request.setAttribute("url", "idForm.me");
			return  new ActionForward(false,"../alert.jsp");
		}
	}
	private ActionForward idfind(HttpServletRequest request, String tel, String email) {
		MemberDao dao = new MemberDao();
		String msg = null;
		String url = null;
		Member mem = new Member();
		String id = dao.idfind(email, tel);
		if(id != null){ //id를 찾는경우(존재)
			id = id.substring(0,id.length()-2);
			request.setAttribute("id", id);
			return new ActionForward(); //id.jsp로 이동
		}else { //id 찾기 실패.
			 msg = "정보에 맞는 id를 찾을 수 없습니다.";
			 url = "idForm.me";
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return new ActionForward(false,"../alert.jsp");
		}
	}
}

