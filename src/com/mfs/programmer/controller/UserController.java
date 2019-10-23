package com.mfs.programmer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mfs.programmer.entity.User;
import com.mfs.programmer.page.Page;
import com.mfs.programmer.service.UserService;

/**
 * 	用户（管理员）控制器
 * @author mfs
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 	用户管理列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("user/user_list");
		return model;
	}
	
	/**
	 * 	用户展示
	 * @return
	 */
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
		@RequestParam(value = "username",required = false,defaultValue = "")String username,
		Page page){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", "%"+username+"%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize",page.getRows());
		ret.put("rows", userService.findList(queryMap));
		ret.put("total", userService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 	删除用户
	 * @param mfs
	 * @return
	 */
	@RequestMapping(value ="/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value = "ids[]",required = true) Long[] ids
			){
		Map<String, String> ret = new HashMap<String, String>();
		if (ids == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择你要删除的数据");
			return ret;
		}
		String idsString ="";
		for(Long id:ids) {
			idsString +=id+",";
		}
		idsString = idsString.substring(0,idsString.length()-1);
		if (userService.delete(idsString)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg","删除成功");
		return ret;
	}
	
	/**
	 * 	修改用户的操作
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(User user){
		Map<String, String> ret = new HashMap<String, String>();
		//判断是否为空，为空不能提交
		if (user == null) {
			ret.put("type", "error");
			ret.put("msg","添加失败，请联系开发者");
			return ret;
		}
		//判断用户名是否为空
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg","用户名不能为空");
			return ret;
		}
		//判断密码是否为空
		if (StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg","密码不能为空");
			return ret;
		}
		//添加时判断用户名是否存在
		User exitsUser = userService.findByUserName(user.getUsername());
		if (exitsUser!=null) {
			if (user.getId()!=exitsUser.getId()) {
				ret.put("type", "error");
				ret.put("msg","该问户名已经存在");
				return ret;
			}
		}
		//判断是否填入
		if (userService.edit(user) <= 0) {
			ret.put("type", "error");
			ret.put("msg","修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg","修改成功");
		return ret;
	}
	
	/**
	 * 	添加用户的操作
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User user){
		Map<String, String> ret = new HashMap<String, String>();
		//判断是否为空，为空不能提交
		if (user == null) {
			ret.put("type", "error");
			ret.put("msg","添加失败，请联系开发者");
			return ret;
		}
		//判断用户名是否为空
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg","用户名不能为空");
			return ret;
		}
		//判断密码是否为空
		if (StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg","密码不能为空");
			return ret;
		}
		//添加时判断用户名是否存在
		User exitsUser = userService.findByUserName(user.getUsername());
		if (exitsUser!=null) {
			ret.put("type", "error");
			ret.put("msg","该问户名已经存在");
			return ret;
		}
		//判断是否填入
		if (userService.add(user)<=0) {
			ret.put("type", "error");
			ret.put("msg","添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg","添加成功");
		return ret;
	}
	

}
