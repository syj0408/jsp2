package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAction implements Action{ //implements Action 반드시 구현.

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
   {
      try
      {
         int num1 = Integer.parseInt(request.getParameter("num1"));
         int num2 = Integer.parseInt(request.getParameter("num2"));
         request.setAttribute("result", num1 + num2);
      }
      catch(NumberFormatException e)
      {
         request.setAttribute("result", 0);
      }
      return new ActionForward(false, "addForm.jsp");
   }
   
}
