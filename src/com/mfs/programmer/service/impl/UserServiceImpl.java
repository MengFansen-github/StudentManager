package com.mfs.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfs.programmer.dao.UserDao;
import com.mfs.programmer.entity.User;
import com.mfs.programmer.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	//查找用户，用户登录
	@Autowired
	private UserDao userDao;
	//实现service的方法
	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	//添加方法
	@Override
	public int add(User user) {
		return userDao.add(user);
	}
	//用户列表展示
	@Override
	public List<User> findList(Map<String, Object> queryMap) {
		return userDao.findList(queryMap);
	}
	//获取数量
	@Override
	public int getTotal(Map<String, Object> quertyMap) {
		return userDao.getTotal(quertyMap);
	}
	//修改方法
	@Override
	public int edit(User user) {
		return userDao.edit(user);
	}
	@Override
	public int delete(String ids) {
		return userDao.delete(ids);
	}

}
