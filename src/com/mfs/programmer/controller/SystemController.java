package com.mfs.programmer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mfs.programmer.entity.Student;
import com.mfs.programmer.entity.User;
import com.mfs.programmer.service.StudentService;
import com.mfs.programmer.service.UserService;
import com.mfs.programmer.util.CpachaUtil;

/**
 * 	系统主页控制器
 * @author mfs
 *
 */
//此注解是在根路径下面的公共的子路径，以下的在定义的方法都是此路径下的子路径
@RequestMapping("/system")
@Controller
public class SystemController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	
	
	
	//此方法代表访问的是system路径下的子路径index，使用的请求方法是get请求
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		//这里设置的是访问的hello world。jsp的文件
		model.setViewName("system/index");
		return model;
	}
	
	/////////////////////////////////////////////////////////////
	/**
	 * 	  登录页面
	 * @param model
	 * @return
	 */
	//登录页面的显示
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	//表单提交的方法
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	//最终返回的字符串按照json的形式返回
	@ResponseBody
	public Map<String, String> login(
			@RequestParam(value = "username",required = false) String username,
			@RequestParam(value = "password",required = false) String password,
			@RequestParam(value = "vcode",required = false)String vcode,
			//判断是学生还是管理员的登录，分为1和2
			@RequestParam(value = "type",required = false) int type,
			HttpServletRequest request
			) {
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(username)) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(password)) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(vcode)) {
			ret.put("type", "error");
			ret.put("msg", "验证码不能为空");
			return ret;
		}
		String loginCpacha = (String) request.getSession().getAttribute("loginCpacha");
		if (StringUtils.isEmpty(loginCpacha)) {
			ret.put("type", "erroe");
			ret.put("msg", "长时间位操作,会话已结束，请刷新后重试");
			return ret;
		}
		if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
			ret.put("type", "erroe");
			ret.put("msg", "验证码错误");
			return ret;
		}
		request.getSession().setAttribute("loginCpacha", null);
		//从数据库查找用户
		if (type==1) {
			//是管理员
			User user=userService.findByUserName(username);
			if(user == null) {
				ret.put("type", "erroe");
				ret.put("msg", "该用户不存在");
				return ret;
			}
			//判断密码是否正确
			if (!password.equals(user.getPassword())) {
				ret.put("type", "erroe");
				ret.put("msg", "密码错误,请重新输入");
				return ret;
				
			}
			request.getSession().setAttribute("user", user);
		}
		if(type == 2){
			//学生
			Student student = studentService.findByUserName(username);
			if(student == null){
				ret.put("type", "error");
				ret.put("msg", "不存在该学生!");
				return ret;
			}
			if(!password.equals(student.getPassword())){
				ret.put("type", "error");
				ret.put("msg", "密码错误!");
				return ret;
			}
			request.getSession().setAttribute("user", student);
		}
		request.getSession().setAttribute("userType", type);
		ret.put("type", "success");
		ret.put("msg", "登录成功!");
		return ret;
	}
	
	//验证码的显示
	@RequestMapping(value = "/get_cpacha",method = RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,
			@RequestParam(value = "vl",defaultValue = "4",required = false) Integer vl,
			@RequestParam(value = "w",defaultValue = "98",required = false) Integer w,
			@RequestParam(value = "h",defaultValue = "33",required = false) Integer h,
			HttpServletResponse response) {
		
		CpachaUtil cpachaUtil = new CpachaUtil(vl,w,h);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute("loginCpacha", generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * * 注销方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login_out",method = RequestMethod.GET)
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "redirect:login";
	}
}
