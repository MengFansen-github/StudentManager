package com.mfs.programmer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mfs.programmer.entity.Grade;

/**
 * 	年级service
 * @author mfs
 *
 */
@Service
public interface GradeService {
	//添加用户
		public int add(Grade grade);
		//用户列表展示
		public List<Grade> findList(Map<String, Object> queryMap);
		//获取数量
		public int getTotal(Map<String, Object> quertyMap);
		//修改方法
		public int edit(Grade grade);
		//删除方法
		public int delete(String ids);
		//查询所有
		public List<Grade> findAll();
}
