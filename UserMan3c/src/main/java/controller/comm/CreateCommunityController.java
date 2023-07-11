package controller.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Community;
import model.service.UserManager;

public class CreateCommunityController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateCommunityController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Community comm = new Community(
    		0, request.getParameter("name"),
			request.getParameter("desc"),
			null, null, null);		
        
		try {
			UserManager manager = UserManager.getInstance();
			manager.createCommunity(comm);
			System.out.println(comm.getId()); //이거 해주면 idrkqt rkwudhf t dltdma
			
	    	log.debug("Create Community : {}", comm);
	        return "redirect:/community/list";	// 성공 시 커뮤니티 리스트 화면으로 redirect
	        
	        
	        //?commId = 아디이 이름(comm.getId()) 이용해서 값 넘겨주기.
	        //return list?commId=" -+ comm.getId(); //해주면 getParameter해서 Id값 넘어갈 수 있음. 
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("comm", comm);
			return "/community/creationForm.jsp";
		}
    }
}
