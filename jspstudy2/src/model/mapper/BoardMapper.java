package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Board;

public interface BoardMapper {
	
	@Select("select ifnull(max(num),0) from board")
	int maxnum();

	@Insert("insert into board "
			+ " (num,name, pass, subject, content, file1, regdate,"
			+ " readcnt, grp,grplevel,grpstep) "
			+ " values(#{num},#{name},#{pass},#{subject},#{content},#{file1},now(),0,#{grp},#{grplevel},#{grpstep})")
	int insert(Board board);

	@Select({"<script>","select count(*) from board",
		"<if test='col1 != null'>where ${col1} like '%${find}%' </if>",
		"<if test='col2 != null'>OR ${col2} like '%${find}%' </if>",
		"</script>"})
	int boardcount(Map<String,Object> map);

	@Select({"<script>","select * from board ",
		"<if test='col1 != null'>where ${col1} like '%${find}%'</if>",
		"<if test='col2 != null'>or ${col1} like '%${find}%' </if>",
		"<choose>",
		"<when test='num != null'> where num =#{num} </when>",
		"<otherwise>"
		+" order by grp desc, grpstep asc limit #{start},#{limit}"
		+"</otherwise>",
		"</choose>",
		"</script>"})
	List<Board> select(Map<String, Object> map);


	@Update("update board set readcnt = readcnt + 1 where num =#{num}")
	void readcntadd(int num);

	@Update("update board set grpstep = grpstep + 1 where grp =#{grp} and grpstep > #{grpstep}")
	void getStepAdd(@Param("grp") int grp,@Param("grpstep") int grpstep);

	@Update("update board set name=#{name}, subject=#{subject}, content=#{content}, file1=#{file1} where num=#{num}")
	int update(Board board);

	@Delete("delete from board where num=#{num}")
	int delete(int num);

	@Select("select name , count(*) cnt from board group by name having count(*) > 1 order by cnt")
	List<Map<String, Integer>> graph();

}
