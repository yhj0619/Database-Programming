package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	User user = null;
		UserManager manager = UserManager.getInstance();
		String userId = request.getParameter("userId");

		try {
			user = manager.findUser(userId);	// 사용자 정보 검색
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/list";
		}	
		
		request.setAttribute("user", user);		// 사용자 정보 저장				
		
		if (request.getParameter("updateFailed") != null) {
			// 수정 시도 실패
	    	request.setAttribute("exception", 
				new IllegalStateException("다른 사용자의 정보는 수정할 수 없습니다."));            
			request.setAttribute("updateFailed", true);
		}
		else if (request.getParameter("deleteFailed") != null) {
			// 삭제 시도 실패
	    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());		
	    	String msg = (curUserId.equals("admin")) 	// 현재 로그인한 사용자가 관리자
					   ? "시스템 관리자 정보는 삭제할 수 없습니다."		
					   : "다른 사용자의 정보는 삭제할 수 없습니다.";	
	    	request.setAttribute("exception", new IllegalStateException(msg));
			request.setAttribute("deleteFailed", true);
		}		
		
		return "/user/view.jsp";		// 사용자 보기 화면으로 이동
    }
}
