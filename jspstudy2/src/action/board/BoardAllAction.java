package action.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.oreilly.servlet.MultipartRequest;


import action.ActionForward;
import model.Board;
import model.BoardDao;

public class BoardAllAction {
	private BoardDao dao = new BoardDao();
	public ActionForward hello(HttpServletRequest requset,HttpServletResponse response) {
		requset.setAttribute("hello", "Hello, world");
		return new ActionForward();
	}
	/**  
	  1. multipart/form.data 타입의 전송이므로 MultipartRequest 객체 생성
	  2. 파라미터 값을 model.Board 객체 저장.
 			useBean 사용불가: request 정보의 파라미터와 빈클래스의 프로퍼티 비교
 					       request 객체를 사용 할 수 없음.
 	  3. 게시물 번호 num 현재 등록된 num의 최대값을 조회. 최대값 +1 등록된 게시물의 번호.
 		 db에서 maxnum을 구해서 +1 값으로 num 설정하기.
 	  4. board 내용을 DB에 등록하기
 			등록 성공: 메세지 출력. list.do 페이지 이동
 			등록 실패: 메세지 출력. writeForm.do 페이지 이동
	 * @throws ServletException 
	 
	 */
	public ActionForward write(HttpServletRequest request, HttpServletResponse reseponse) throws ServletException {
		String msg = "게시물 등록 실패";
		String url = "writeForm.do";
		String path = request.getServletContext().getRealPath("/")+"model2/board/file/";
		try {
		File f = new File(path);
		if(!f.exists())f.mkdirs();
		MultipartRequest multi = new MultipartRequest(request, path,10*1024*1024,"euc-kr");
		//2
		Board b = new Board();
		b.setName(multi.getParameter("name"));
		b.setPass(multi.getParameter("pass"));
		b.setSubject(multi.getParameter("subject"));
		b.setContent(multi.getParameter("content"));
		b.setFile1(multi.getFilesystemName("file1"));
		//3
		int num = dao.maxnum(); //최대값을 가져오게됨
		b.setNum(++num);
		b.setGrp(num);
		if(dao.insert(b)) {
			msg = "게시물 등록 성공";
			url = "list.do";
		}
		}catch(IOException e) {
			throw new ServletException();
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	/*
	 *  1. 한페이지당 10건의 게시물을 출력하기.
      		pageNum 파라미터값을 저장 => 없는 경우는 1로 설정하기.
   		2. 최근 등록된 게시물이 가장 위에 배치함.
   		3. 화면에 필요한 정보를 속성으로 등록. view로 전송.
	 * 
	 */
	public ActionForward list(HttpServletRequest request, HttpServletResponse reseponse) throws ServletException {
		int limit =  10;
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}catch (NumberFormatException e) {}
		String  column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals(""))
			column=null;
		if(find != null && find.trim().equals(""))
			find=null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount(column,find);
		List<Board> list = dao.list(pageNum, limit,column,find);
		int maxpage = (int)((double)boardcnt/limit+0.95);
		int startpage = ((int)(pageNum/10.0 + 0.9)-1)* 10 + 1;
		int endpage = startpage + 9;
		if(endpage > maxpage) endpage = maxpage;
		int boardnum = boardcnt - (pageNum-1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	/*
	 *  1. num 파라미터 정보를 변수에 저장.
  		2. num을 이용하여 db에서 게시물 정보를 조회
     		Board BoardDao.selectOne(num)
  		3. 조회수 증가시키기   
     		void BoardDao.readcntadd(num)
  		4. 조회된 게시물을 화면에 출력. 
	 * 
	 */
	public ActionForward info(HttpServletRequest request, HttpServletResponse reseponse) {
		int num = Integer.parseInt(request.getParameter("num"));
		Board b = dao.selectOne(num);
		// request.getRequestURI() : /jspstudy2/model2/board/info.do
		if(request.getRequestURI().contains("info.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		return new ActionForward();
	}
	  /*
	*   1.파라미터 값을 Board 객체에 저장하기. 
			원글정보: num, grp, grplevel, grpstep 
			답글정보: name, pass, subject, content => 등록정보
		2.같은 grp 값을 사용하는 게시물들의 grpstep 값을 1 증가하기
			void BoardDao.grpSetAdd(grp,grpstep)
		3.Board 객체를 db에 insert 하기
			num : maxnum + 1
			grp : 원글과 동일
			grplevel : 원글 + 1
			grpstep : 원글 + 1
		*/
	public ActionForward reply(HttpServletRequest request, HttpServletResponse reseponse) {
		Board b = new Board();
		b.setNum(Integer.parseInt(request.getParameter("num")));
		b.setGrp(Integer.parseInt(request.getParameter("grp")));
		b.setGrplevel(Integer.parseInt(request.getParameter("grplevel")));
		b.setGrpstep(Integer.parseInt(request.getParameter("grpstep")));
		b.setName(request.getParameter("name"));
		b.setPass(request.getParameter("pass"));
		b.setSubject(request.getParameter("subject"));
		b.setContent(request.getParameter("content"));
		dao.getStepAdd(b.getGrp(), b.getGrpstep());
		int grplevel = b.getGrplevel();
		int grpstep = b.getGrpstep();
		int num = dao.maxnum();
		String msg = "답변 등록시 오류발생";
		String url = "replyForm.do?num=" +b.getNum();
		b.setNum(++num);
		b.setGrplevel(grplevel+1);
		b.setGrpstep(grpstep+1);
		if(dao.insert(b)){
			msg = "답변 등록완료";
			url = "list.do";
	}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	/*
	 * 1.파라미터 정보들을 Board 객체에 저장.
	 * 2.비밀번호 검증
	 * 	비밀번호 일치하는경우
	 * 		수정 파라미터의 내용으로 해당 게시물의 내용을 수정하기.
	 * 		첨부 파일의변경이 없는 경우 file2 파라미터의 내용을 다시 저장하기
	 * 	비밀번호 불일치
 			비밀번호 오류 메세지 출력하고, updateForm.do로 페이지 이동
 		3. 수정성공 : 수정성공 메세지 출력 후. info.do 페이지 이동
 	                수정실패 : 수정실패 메세지 출력 후. updateForm.do 페이지 이동
	 * 
	 */
	public ActionForward update(HttpServletRequest request,HttpServletResponse response) throws IOException  {
		   Board b = new Board();
		   String path = request.getServletContext().getRealPath("/")+"model2/board/file/";
		   MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024 ,"euc-kr");
		    b.setNum(Integer.parseInt(multi.getParameter("num")));
		    b.setName(multi.getParameter("name"));
			b.setPass(multi.getParameter("pass"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFile1(multi.getFilesystemName("file1")); //첨부파일에 해당하는 수정이 안된경우.
			if(b.getFile1() == null || b.getFile1().equals("")) {
				b.setFile1(multi.getParameter("file2"));
			}
			BoardDao dao = new BoardDao();
			Board dbBoard = dao.selectOne(b.getNum());
			String msg = "비밀번호가 틀렸습니다.";
			String url = "updateForm.do?num="+b.getNum();
			if(b.getPass().equals(dbBoard.getPass())){
				if(dao.update(b)){
					msg = "게시물 수정완료";
					url = "info.do?num="+b.getNum();
				}else {
					msg= "게시물 수정 실패 ";
				}
			}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	/*
	 *  1. num,pass 파라미터를 변수에 저장.
		2. 입력된 비밀번호와 db 비밀번호 검증
			틀린경우: 비밀번호 오류 메세지 출력, deleteForm.do 페이지 이동
		3. 비밀번호가 맞는 경우 : 게시물 삭제.
			삭제 성공 : 삭제 성공 메세지 출력. list.do 페이지 이동
			삭제 실패  : 삭제 실패 메세지 출력. info.do 페이지 이동
	 * 
	 */
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pass= request.getParameter("pass");
		String msg = "비밀번호가 틀립니다.";
		String url = "deleteForm.do";
		Board b = dao.selectOne(num);
		if(b == null) {
			msg = "없는 게시글 입니다.";
			url = "list.do";
		}else {
			if(pass.equals(b.getPass())) {
				msg = "비밀번호가 틀립니다.";
				url = "deleteForm.do";
				if(dao.delete(num)) {
					msg = "게시물 삭제완료";
					url = "list.do";
				}else {
					msg = "게시물 삭제 실패";
					url = "info.do?num=" +num;
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	/*
	 * CKEDITOR에서 이미지를 게시판 내용에 추가하기 
	 */
	public ActionForward imgupload(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getServletContext().getRealPath("/") + "model2/board/imgfile/";
		File f= new File(path);
		if(!f.exists()) f.mkdirs();
		MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024 , "euc-kr");
		String fileName = multi.getFilesystemName("upload");
		request.setAttribute("fileName", fileName);
		request.setAttribute("CKEditorFuncNum", request.getParameter("CKEditorFuncNum"));
		return new ActionForward(false,"ckeditor.jsp");
	}
	public ActionForward exchange(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url="https://www.koreaexim.go.kr/site/program/financial/exchange?menuid=001001004002001";
		Document doc = null;
		List<String> list =  new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		try {
			doc = Jsoup.connect(url).get();
			Elements e1 = doc.select(".tc");//환율정보 =국가코드,환율값 태그선택
			Elements e2 = doc.select(".tl2.bdl");//국가이름
			for(int i=0; i<e1.size();i++) {
				if(e1.get(i).html().equals("USD")) { //usd인지 구분
					list.add(e1.get(i).html());
					for(int j=1; j<=6; j++) {
						list.add(e1.get(i+j).html());//usd 정보 저장
					}
					break;
				}
			}
			for(Element ele : e2) {
				if(ele.html().contains("미국")) {
					list2.add(ele.html());
				}
			}
			    
		}catch(IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("today", new Date());
		return new ActionForward(); //전달.
	}
	
	public ActionForward exchange2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url="https://www.koreaexim.go.kr/site/program/financial/exchange?menuid=001001004002001";
		Document doc = null;
		Map<String,List<String>> map = new TreeMap<>(Comparator.reverseOrder());
		List<String> names = Arrays.asList("미국","유로","일본");
		List<String> codes = Arrays.asList("USD","EUR","JPY");
		try {
			doc = Jsoup.connect(url).get();
			Elements e1 = doc.select(".tc");//환율정보 =국가코드,환율값 태그선택
			Elements e2 = doc.select(".tl2.bdl");//국가이름
			for(Element ele : e2) { //국가이름
			  for(int n =0; n<names.size(); n++) {
					//names.get(0) : 미국
				 if(ele.html().contains(names.get(n))) {
					List<String> list = new ArrayList<>();
				  for(int i=0; i<e1.size(); i++) {
					if(e1.get(i).html().startsWith(codes.get(n))) { //EUR에 대한 코드를 가지고있음
						//codes.get(n) : "USD"
					  list.add(e1.get(i).html());
						// i : USD 코드값의 index값
					  for(int j=1; j<=6; j++) { //eur에 값들을
						list.add(e1.get(i+j).html());//정보 저장
					}	
				}
			}
			//ele.html() : 국가이름
			//list : 국가의 통화코드와 환율값
			 map.put(ele.html(),list);
			 }
			}
		   }
		}catch(IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("map", map);
		request.setAttribute("today", new Date());
		return new ActionForward(); //전달.
	}
	public ActionForward graph(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Map<String,Integer>> list = dao.boardgraph(); //String = name으로 들어가고 Integer= cnt,? 맵객체를 list화 해서 가짐..
		StringBuilder json = new StringBuilder("[");
		int i= 0;
		//json형태로 생성
		System.out.println(list.size());
		for(Map<String,Integer> m :list) {
			for(Map.Entry<String, Integer> me : m.entrySet()) {
				if(me.getKey().equals("name"))json.append("{\"name\":\""+me.getValue()+"\",");
				if(me.getKey().equals("cnt"))json.append("\"cnt\":"+me.getValue()+"}");
			}
			i++;
			if(i<list.size()) json.append(",");
		}
		json.append("]");
		request.setAttribute("json", json.toString().trim());
		return new ActionForward();
	}
	
	public ActionForward graph2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Map<String,Integer>> list = dao.boardgraph(); //String = name으로 들어가고 Integer= cnt,? 맵객체를 list화 해서 가짐..
		StringBuilder json = new StringBuilder("[");
		int i= 0;
		//json형태로 생성
		System.out.println(list.size());
		for(Map<String,Integer> m :list) {
			for(Map.Entry<String, Integer> me : m.entrySet()) {
				if(me.getKey().equals("name"))json.append("{\"name\":\""+me.getValue()+"\",");
				if(me.getKey().equals("cnt"))json.append("\"cnt\":"+me.getValue()+"}");
			}
			i++;
			if(i<list.size()) json.append(",");
		}
		json.append("]");
		request.setAttribute("json", json.toString().trim());
		return new ActionForward();
	}
}

