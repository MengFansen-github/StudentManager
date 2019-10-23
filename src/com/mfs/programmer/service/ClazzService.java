package com.mfs.programmer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mfs.programmer.entity.Clazz;
@Service
public interface ClazzService {
	//添加用户
	public int add(Clazz clazz);
	//用户列表展示
	public List<Clazz> findList(Map<String, Object> queryMap);
	//获取数量
	public int getTotal(Map<String, Object> quertyMap);
	//修改方法
	public int edit(Clazz clazz);
	//删除方法
	public int delete(String ids);
	//查询所有
	public List<Clazz> findAll();
}
