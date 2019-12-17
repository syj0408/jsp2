package model;

import java.io.IOException;
import java.io.Reader;


import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class DBConnection {
	private static SqlSessionFactory sqlMap; //static=>전체 객체에서 하나만 씀.
	static {
		//mybatis-config.xml 파일의 위치
		String resource = "model/mapper/mybatis-config.xml"; //model/mapper/ 파일이기때문에 폴더로 지정. 패키지로 지정할 수 없음. 
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		}catch(IOException e) {
			e.printStackTrace();
		}
		//bulid : Connection 객체 설정.
		//		  SQL 구문 저장하는 컨테이너 설정.
		sqlMap = new SqlSessionFactoryBuilder().build(reader);
	}
	static SqlSession getConnection() {//같은 패키지에서만 사용가능
		return sqlMap.openSession(); //DB와 연결하기
	}
	static void close(SqlSession session) {//같은 패키지에서만 사용가능
		session.commit();
		session.close();
	}
}
