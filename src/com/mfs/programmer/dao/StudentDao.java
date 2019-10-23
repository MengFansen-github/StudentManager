package com.mfs.programmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mfs.programmer.entity.Clazz;
import com.mfs.programmer.entity.Grade;
import com.mfs.programmer.entity.Student;
import com.mfs.programmer.entity.User;
/**
 * 	学生dao层
 * @author mfs
 *
 */
@Repository
public interface StudentDao {
	public Student findByUserName(String username);
	public int add(Student student);
	public int edit(Student student);
	public int delete(String ids);
	public List<Student> findList(Map<String,Object> queryMap);
	public List<Student> findAll();
	public int getTotal(Map<String,Object> queryMap);
}
