package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.User;
import model.service.UserManager;

public class ListUserController implements Controller {
	// private static final int countPerPage = 100;	// �� ȭ�鿡 ����� ����� ��

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
    	
		UserManager manager = UserManager.getInstance();
		// List<User> userList = manager.findUserList(currentPage, countPerPage);
		List<User> userList = manager.findUserList();
		
		// userList ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("userlist", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getUserFromSession(request.getSession()));		

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/user/lists.jsp";        
    }
}
