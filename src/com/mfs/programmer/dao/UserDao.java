package com.mfs.programmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mfs.programmer.entity.User;

//通过用户表查找用户名，传给service
@Repository
public interface UserDao {
	//查找用户，用户登录
	public User findByUserName(String username);
	//添加方法
	public  int add(User user);
	//用户列表展示
	public List<User> findList(Map<String, Object> queryMap);
	//获取数量
	public int getTotal(Map<String, Object> quertyMap);
	//修改方法
	public int edit(User user);
	//删除方法
	public int delete(String ids);
}
