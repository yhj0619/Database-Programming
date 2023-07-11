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
			
		if (curUserId.equals("admin") && 	// �α����� ����ڰ� �������̰� 	
			 !deleteId.equals("admin")		// ���� ����� �Ϲ� ������� ���, 
			   || 							// �Ǵ� 
			!curUserId.equals("admin") &&  	// �α����� ����ڰ� �����ڰ� �ƴϰ� 
			 deleteId.equals(curUserId))  { // ���� ����� �ڱ� �ڽ��� ���
				
			manager.remove(deleteId);			// ����� ���� ����
			if (curUserId.equals("admin")) 
				return "redirect:/user/list";		// ����ڸ���Ʈ�� �̵�
			else 
				return "redirect:/user/logout";		// logout ����
		}
		
		/* ������ �Ұ����� ��� */
		/*User user = manager.findUser(deleteId);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (curUserId.equals("admin")) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "�ٸ� ������� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/view.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
		*/	
		return "redirect:/user/view?userId=" + deleteId
				+ "&deleteFailed=true"; 	// redirect ����
	}
}
