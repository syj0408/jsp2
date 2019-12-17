package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
1. 모든 파라미터 Member 객체에 저장
2. 입력된 비밀번호와 db에 저장된 비밀번호가 같지않으면
	"비밀번호가 틀렸습니다." 메세지 출력 후, updateForm.me 페이지로 이동.
3. 1번에 내용을 db에 수정하기
	 int MemberDao.update(Member)
	결과가 0보다 크면 "수정성공" 메세지 출력. info.me 페이지 이동
		 0보다 작으면 "수정 실패" 메세지 출력. main.mn 페이지 이동 
*/  
import action.ActionForward;
import model.Member;
import model.MemberDao;

public class UpdateAction extends UserloginAction{
	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member(); //usebean액션태그를 사용할수없기때문에 일일히 집어 넣음.
		mem.setPicture(request.getParameter("picture")); 
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setEmail(request.getParameter("email"));
		mem.setTel(request.getParameter("tel"));

		MemberDao dao=new MemberDao();
		Member dbmem = dao.selectOne(id);
		String msg =  "비밀번호가 틀렸습니다.";
		String url = "updateForm.me?id="+mem.getId();
		if(!login.equals("admin") && mem.getPass().equals(dbmem.getPass())) {
			if(dao.update(mem) > 0 ) {
				msg="수정이 완료되었습니다.";
				url="info.me?id="+mem.getId();
				
			}else {
				msg="수정을 완료하지 못하였습니다.";
				url="main.me";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
}
