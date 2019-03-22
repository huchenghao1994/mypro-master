package com.yaspeed.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yaspeed.core.util.RequestHolder;
import com.yaspeed.web.pojo.ActiveUser;

/**
 * 
 * Description: 登陆和退出
 * 
 * @version 1.0
 */
@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	// 登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) throws Exception {
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		String message = "";
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				// 最终会抛给异常处理器
				message = "账号不存在,请重新填写";
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				message = "用户名/密码错误,请重新填写";
			} else if("randomCodeError".equals(exceptionClassName)){
				message = "验证码错误，请重新填写";
			} else {
				message = "发生未知错误";
			}
		}
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		// 登陆失败还到login页面
		model.addAttribute("username", request.getParameter("username"));
		model.addAttribute("password", request.getParameter("password"));
		request.setAttribute("message", message);
		return "/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		// 如果已经登录，则跳转到管理首页
		if (subject != null) {
			subject.logout();
		}
		// 登陆失败还到login页面
		return "redirect:/login";
	}
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
//		Subject subject = SecurityUtils.getSubject();
//		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
//		RequestHolder.getSession().setAttribute("activeUser", activeUser);
		return "/index";
	}
}
