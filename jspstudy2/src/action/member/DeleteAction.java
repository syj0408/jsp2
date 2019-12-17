package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.Member;
import model.MemberDao;

/*
1.id가 관리자 인 경우
	"관리자는 탈퇴가 불가능합니다. 메세지 출력. list.me 페이지 이동
2.탈퇴 조건
	입력된 비밀번호와 db의 비밀번호를 검증
		-관리자가 아니면서 비밀번호 불일치 
				"비밀번호가 틀립니다" 메세지 출력. deleteForm.me 페이지 이동
3. 관리자 로그인, 또는 비밀번호 일치하는 경우 db에서 회원 정보 삭제하기
 	삭제 성공 
 		일반사용자 : 로그아웃 후 탈퇴메세지 출력 . loginForm.me 페이지이동
 		관리자 : 탈퇴 메세지 출력. list.me 페이지 이동.
 	삭제 실패 
 		일반사용자 : 삭제실패 메세지 출력. info.me 페이지 이동.
 		관리자 : 삭제실패 메세지 출력. list.me 페이지 이동.
*/
public class DeleteAction extends UserloginAction {
	  @Override
	   protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) 
	   {
	      if(id.equals("admin")) // 관리자가 관리자 계정 탈퇴 요청시
	      {
	         request.setAttribute("msg", "관리자는 탈퇴할 수 없습니다");
	         request.setAttribute("url", "list.me");
	         return new ActionForward(false, "../alert.jsp");
	      }
	      String pass = request.getParameter("pass");
	      MemberDao dao = new MemberDao();
	      Member mem = dao.selectOne(id);
	      String msg = "비밀번호가 틀립니다";
	      String url = "deleteForm.me?id=" + id;
	      if(login.equals("admin") || pass.equals(mem.getPass())) // 관리자이거나 입력한 값이 비밀번호와 일치하는 경우
	      {
	            if(dao.delete(id) > 0) // 삭제 성공
	            {
	            if(login.equals("admin")) // 관리자인 경우
	            {
	                 msg = "강제탈퇴 성공";
	               url = "list.me";
	            }
	            else // 일반 사용자의 경우
	            {
	               msg = id + "회원님의 탈퇴가 완료되었습니다";
	               url = "loginForm.me";
	               request.getSession().invalidate();
	            }
	         }
	         else // 삭제 실패
	         {
	            msg= id + "님의 탈퇴시 오류 발생";
	            if(login.equals("admin")) {// 관리자인 경우
	               url = "list.me";
	            }else { // 일반 사용자의 경우 
	               url = "info.me?id=" + id;               
	            }   
	         }
	      }
	      request.setAttribute("msg", msg);
	      request.setAttribute("url", url);
	      return new ActionForward(false, "../alert.jsp");
	   }
}