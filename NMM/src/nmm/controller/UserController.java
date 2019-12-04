package nmm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nmm.dto.ModelAndView;
import nmm.dto.UserDTO;
import nmm.service.UserService;

public class UserController implements Controller {

	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("userId", null);
		session.setAttribute("userNo", null);
		return new ModelAndView("servlet?controller=product+review&command=productList", false);
	}

	@Override
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("servlet?controller=product+review&command=productList", false);
	}

	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		UserDTO user = UserService.loginCheck(userId, userPwd);
		if (user != null) {
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userNo", user.getUserNo());
		}
		return new ModelAndView("servlet?controller=product+review&command=productList", false);
	}

	public ModelAndView signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String userAddr = request.getParameter("userAddr");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String userBirth = request.getParameter("userBirth");
		UserDTO dto = new UserDTO(userId, userPwd,userName,  userAddr, userPhone, userEmail, userBirth);
		UserService.insert(dto);

		return new ModelAndView("servlet?controller=product+review&command=productList", false);
	}

	public ModelAndView upDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String userAddr = request.getParameter("userAddr");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String userBirth = request.getParameter("userBirth");
		UserDTO dto = new UserDTO(userId, userPwd, userName, userPhone, userAddr, userEmail);
		UserService.update(dto);
		return new ModelAndView("servlet?controller=product+review&command=productList", false);
	}

	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		UserService.delete(userId);
		return new ModelAndView("servlet?controller=product+review&command=productList", false);
	}
}
