package action.member;

import java.io.File;
import java.io.IOException;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.Action;
import action.ActionForward;
/*
 * 1. 파일업로드부분.
 */
public class PictureAction implements Action 
{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
   {
      String path = request.getServletContext().getRealPath("/") + "model2/member/picture";
      String fname = null;
      try {
            File f = new File(path);
            if(!f.exists()) 
            {
               f.mkdirs();
            }
            MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"euc-kr");
            fname = multi.getFilesystemName("picture");
         } 
      catch (IOException e) 
      {
         e.printStackTrace();
      }
      request.setAttribute("fname", fname);
      return new ActionForward();
   }

}
