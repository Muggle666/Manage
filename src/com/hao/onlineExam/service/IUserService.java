//package com.hao.onlineExam.service;
//
//import java.io.InputStream;
//import java.security.NoSuchAlgorithmException;
//import java.util.List;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hao.onlineExam.model.User;
//import com.hao.onlineExam.model.vo.ExamUserVO;
//
//@Transactional
//public interface IUserService {
//	
//	public void saveStudentByExcel(InputStream inputStream,String postfix) throws NoSuchAlgorithmException;
//	
//	public List<ExamUserVO> findAllUsers();
//	
//	public void createUser(ExamUserVO examUserVO) throws Exception;
//	
//	public void editUser(ExamUserVO examUserVO) throws Exception;
//	
//	public void editUser(User user) throws Exception;
//	
//	public User getUserById(String userId) throws Exception;
//
//	public void deleteUser(String userId) throws Exception;
//	
//	public List<ExamUserVO> getUser() throws Exception;
//	
//	public User getByUserName(String userName);
//}
