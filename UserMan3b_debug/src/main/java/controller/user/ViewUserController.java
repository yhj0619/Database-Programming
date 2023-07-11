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
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
    	User user = null;
		UserManager manager = UserManager.getInstance();
		String userId = request.getParameter("userId");

		try {
			user = manager.findUser(userId);	// ����� ���� �˻�
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/list";
		}	
		
		request.setAttribute("user", user);		// ����� ���� ����				
		
		if (request.getParameter("updateFailed") != null) {
			// ���� �õ� ����
	    	request.setAttribute("exception", 
				new IllegalStateException("�ٸ� ������� ������ ������ �� �����ϴ�."));            
			request.setAttribute("updateFailed", true);
		}
		else if (request.getParameter("deleteFailed") != null) {
			// ���� �õ� ����
	    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());		
	    	String msg = (curUserId.equals("admin")) 	// ���� �α����� ����ڰ� ������
					   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
					   : "�ٸ� ������� ������ ������ �� �����ϴ�.";	
	    	request.setAttribute("exception", new IllegalStateException(msg));
			request.setAttribute("deleteFailed", true);
		}		
		
		return "/user/view.jsp";		// ����� ���� ȭ������ �̵�
    }
}
