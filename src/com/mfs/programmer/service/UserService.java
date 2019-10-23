package com.mfs.programmer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mfs.programmer.entity.User;

//接到userdao的传过来的值，传给实现类实现
@Service
public interface UserService {
	//查找用户
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
