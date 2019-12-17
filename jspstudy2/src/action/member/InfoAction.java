package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.Member;
import model.MemberDao;

public class InfoAction extends UserloginAction{
	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		Member info = new MemberDao().selectOne(id);
		request.setAttribute("info", info);
		return new ActionForward();
	}
}
