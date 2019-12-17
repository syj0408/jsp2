package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

import model.MemberDao;

public class SearchAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String email = request.getParameter("email");
      String tel = request.getParameter("tel");
      String id = request.getParameter("id");
      if(id == null) {  //id 찾기
         return idfind(request,email,tel);
      }else {  //비밀번호 찾기
         return pwSearch(request,id,email,tel);
      }
   }
   private ActionForward pwSearch
   (HttpServletRequest request, String id, String email, String tel) {
      MemberDao dao = new MemberDao();
      String pw = dao.pwSearch(id,email,tel);
      if(pw != null) { //비밀번호 찾기 성공
         request.setAttribute("pass", pw.substring(2,pw.length()));
         return new ActionForward();
      }else {  //비밀번호 찾기 실패
         request.setAttribute("msg", "정보에 맞는 비밀번호를 찾을 수 없습니다.");
         request.setAttribute("url", "pwForm.me");
         return new ActionForward(false,"../alert.jsp");
      }
   }
   private ActionForward idfind  (HttpServletRequest request, String tel, String email) {
      MemberDao dao = new MemberDao();
      String id = dao.idfind(email,tel);
      String msg = null;
      String url = null;
      if(id != null) { //id 찾은 경우
         id = id.substring(0,id.length()-2);
         request.setAttribute("id", id);
         return new ActionForward();
      } else { //id 찾기 실패
         msg = "해당 id가 없습니다.";
         url = "idForm.me";
         request.setAttribute("msg", msg);
         request.setAttribute("url", url);
         return new ActionForward(false,"../alert.jsp");   
      }
   }
}