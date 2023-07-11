package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("userId");
    	log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();
    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());		
			
		if (curUserId.equals("admin") && 	// 로그인한 사용자가 관리자이고 	
			 !deleteId.equals("admin")		// 삭제 대상이 일반 사용자인 경우, 
			   || 							// 또는 
			!curUserId.equals("admin") &&  	// 로그인한 사용자가 관리자가 아니고 
			 deleteId.equals(curUserId))  { // 삭제 대상이 자기 자신인 경우
				
			manager.remove(deleteId);			// 사용자 정보 삭제
			if (curUserId.equals("admin")) 
				return "redirect:/user/list";		// 사용자리스트로 이동
			else 
				return "redirect:/user/logout";		// logout 실행
		}
		
		/* 삭제가 불가능한 경우 */
		/*User user = manager.findUser(deleteId);	// 사용자 정보 검색
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (curUserId.equals("admin")) 
				   ? "시스템 관리자 정보는 삭제할 수 없습니다."		
				   : "다른 사용자의 정보는 삭제할 수 없습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/view.jsp";		// 사용자 보기 화면으로 이동 (forwarding)	
		*/	
		return "redirect:/user/view?userId=" + deleteId
				+ "&deleteFailed=true"; 	// redirect 실행
	}
}
