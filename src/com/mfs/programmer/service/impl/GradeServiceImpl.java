package com.mfs.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfs.programmer.dao.GradeDao;
import com.mfs.programmer.entity.Grade;
import com.mfs.programmer.service.GradeService;
@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	private GradeDao gradeDao;
	
	@Override
	public int add(Grade grade) {
		return gradeDao.add(grade);
	}

	@Override
	public List<Grade> findList(Map<String, Object> queryMap) {
		return gradeDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> quertyMap) {
		return gradeDao.getTotal(quertyMap);
	}

	@Override
	public int edit(Grade grade) {
		return gradeDao.edit(grade);
	}

	@Override
	public int delete(String ids) {
		return gradeDao.delete(ids);
	}

	@Override
	public List<Grade> findAll() {
		return gradeDao.findAll();
	}
	
}
