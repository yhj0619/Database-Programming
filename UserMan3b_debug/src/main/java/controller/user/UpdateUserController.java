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
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
    		
    		String updateId = request.getParameter("userId");
			HttpSession session = request.getSession();
	
			if (UserSessionUtils.isLoginUser("admin", session) || 
				UserSessionUtils.isLoginUser(updateId, session)) {
				// ���� �α����� ����ڰ� �������̰ų� ���� ��� ������� ��� -> ���� ����
				
				UserManager manager = UserManager.getInstance();
				User user = manager.findUser(updateId);	// �����Ϸ��� ����� ���� �˻�
				request.setAttribute("user", user);			
				
				List<Community> commList = manager.findCommunityList();		// Ŀ�´�Ƽ ����Ʈ �˻�
				request.setAttribute("commList", commList);	
				
				return "/user/updateForm.jsp";   // �˻��� ����� ������ update form���� ����     
			}    
			
			// ���� �Ұ����� ��� -> ����� ����� redirect
			/*
			UserManager manager = UserManager.getInstance();
			User user = manager.findUser(updateId);	// �����Ϸ��� ����� ���� �˻�
			request.setAttribute("user", user);			
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("�ٸ� ������� ������ ������ �� �����ϴ�."));            
			return "/user/view.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
	    	*/
			return "redirect:/user/view?userId=" + updateId
					+ "&updateFailed=true"; 	// redirect ����
	    }	
    	
    	// POST request (ȸ�������� parameter�� ���۵�)
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
