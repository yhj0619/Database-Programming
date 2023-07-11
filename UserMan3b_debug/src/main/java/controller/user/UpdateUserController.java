package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager;
import model.Community;
import model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		
    		String updateId = request.getParameter("userId");
			HttpSession session = request.getSession();
	
			if (UserSessionUtils.isLoginUser("admin", session) || 
				UserSessionUtils.isLoginUser(updateId, session)) {
				// 현재 로그인한 사용자가 관리자이거나 수정 대상 사용자인 경우 -> 수정 가능
				
				UserManager manager = UserManager.getInstance();
				User user = manager.findUser(updateId);	// 수정하려는 사용자 정보 검색
				request.setAttribute("user", user);			
				
				List<Community> commList = manager.findCommunityList();		// 커뮤니티 리스트 검색
				request.setAttribute("commList", commList);	
				
				return "/user/updateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
			// 수정 불가능한 경우 -> 사용자 보기로 redirect
			/*
			UserManager manager = UserManager.getInstance();
			User user = manager.findUser(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("user", user);			
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("다른 사용자의 정보는 수정할 수 없습니다."));            
			return "/user/view.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
	    	*/
			return "redirect:/user/view?userId=" + updateId
					+ "&updateFailed=true"; 	// redirect 실행
	    }	
    	
    	// POST request (회원정보가 parameter로 전송됨)
    	User updateUser = new User(
    		request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("name"),
    		request.getParameter("email"),
    		request.getParameter("phone"),
			Integer.parseInt(request.getParameter("commId")));

    	log.debug("Update User : {}", updateUser);

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);			
        return "redirect:/user/list";			
    }
}
