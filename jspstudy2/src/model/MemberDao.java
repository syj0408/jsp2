package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.MemberMapper;
//Model 기능을 담당하는 클래스. db와 연관된 기능을 가진 클래스.
//Dao : Data Access Object
//Member: Member클래스
public class MemberDao {
	private Map<String,Object> map = new HashMap<String,Object>();
	private Class<MemberMapper> cls = MemberMapper.class;
	public Member selectOne(String id) {
		//id : 화면에서 입력된 아이디
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			List<Member> list = session.getMapper(cls).select(map);
			return list.get(0);//list에있는 첫번째 레코드.
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	//회원등록
	public int insert(Member m) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).insert(m);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
	 	return 0; //insert 실패
	} 
	public List<Member> list() {
		SqlSession session  = DBConnection.getConnection(); //Connection 객체생성
		try {
			return session.getMapper(cls).select(null); //모든 멤버의 요소를 가져옴 select(null)
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null; //에러가 나면 null값 전달.
	}
	public int update(Member m) {
		//m : 사용자가 입력한 회원 정보. 변경될 내용
		SqlSession session  = DBConnection.getConnection();
		try {
			return session.getMapper(cls).update(m);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int delete(String id) {
		//m : 사용자가 입력한 회원 정보. 변경될 내용
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int updatePass(String id, String pass) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).updatpass(id,pass);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public String idfind(String email, String tel) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).idfind(email,tel);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}return null;
	}
	 public String pwSearch(String id, String email, String tel) {
		 SqlSession session = DBConnection.getConnection();
		   try {
			   return session.getMapper(cls).pwsearch(id,email,tel);
		   } catch (Exception e) {
			   e.printStackTrace();
		   } finally {
			   DBConnection.close(session);
		   }
		   return null;
	   }

}
