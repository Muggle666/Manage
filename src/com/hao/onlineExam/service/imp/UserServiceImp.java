//package com.hao.onlineExam.service.imp;
//
//
//import java.io.InputStream;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hao.onlineExam.dao.IUserDAO;
////import com.hao.onlineExam.dao.imp.UserJDBCDAOImp;
//import com.hao.onlineExam.excel.ExcelUtils;
//import com.hao.onlineExam.model.User;
//import com.hao.onlineExam.model.vo.ExamUserVO;
//import com.hao.onlineExam.service.IUserService;
//
//@Service
//public class UserServiceImp implements IUserService{
//	
//	@Autowired
//	private IUserDAO userDAO ;
//	
//	@Override
//	public void saveStudentByExcel(InputStream inputStream, String postfix) throws NoSuchAlgorithmException {
//		Workbook wk = ExcelUtils.openWorkbook(inputStream, postfix);
//		List<ExamUserVO> allExcelUser = ExcelUtils.getStudentListByExcel(wk);
//		List<User> entities = new ArrayList<User>();
//		if(allExcelUser != null){
////			ExamRole role = examRoleDAO.get(25);
//			User entity = null;
//			for(int i = 0;i<allExcelUser.size();i++){
//				entity = new User();
//				BeanUtils.copyProperties(allExcelUser.get(i),entity);
//				if("男".equals(allExcelUser.get(i).getGender())){
//					entity.setGender(0);
//				}else if("女".equals(allExcelUser.get(i).getGender())){
//					entity.setGender(1);
//				}
////				Set<ExamRole> examRoles = new HashSet<ExamRole>();
////				examRoles.add(role);
////				entity.setExamRoles(examRoles);
//				entities.add(entity);
//			}
//		}
//		userDAO.save(entities);
//	}
//	
//	@Override
//	public List<ExamUserVO> findAllUsers() {
//		List<User> userList = userDAO.findAllUsers();
//		List<ExamUserVO> userVOList = new ArrayList<ExamUserVO>();
//		for(User user : userList){
//			ExamUserVO vo = new ExamUserVO();
//			BeanUtils.copyProperties(user, vo);
//			if(user.getGender() == 0) {
//				vo.setGender("男");
//			}
//			if(user.getGender() == 1){
//				vo.setGender("女");
//			} 
//			userVOList.add(vo);
//		}
//			
//		return userVOList;
//	}
//
//	@Override
//	public void createUser(ExamUserVO examUserVO) throws Exception{
//		User user = new User();
//		BeanUtils.copyProperties(examUserVO, user);
//		if("男".equals(examUserVO.getGender())){
//			user.setGender(0);
//		}else if("女".equals(examUserVO.getGender())){
//			user.setGender(1);
//		}
//		userDAO.createUser(user);
//	}
//
//	@Override
//	public void editUser(ExamUserVO examUserVO) throws Exception {
//		User user = new User();
//		BeanUtils.copyProperties(examUserVO, user);
//		if("男".equals(examUserVO.getGender())){
//			user.setGender(0);
//		}else if("女".equals(examUserVO.getGender())){
//			user.setGender(1);
//		}
//		userDAO.editUser(user);
//	}
//
//	@Override
//	public User getUserById(String userId) throws Exception {
//		return userDAO.getUserById(userId);
//	}
//
//	@Override
//	public void deleteUser(String userId) throws Exception {
//		userDAO.deleteUser(userId);
//	}
//
//	@Override
//	public List<ExamUserVO> getUser() throws Exception {
//		List<ExamUserVO> userVOList = new ArrayList<ExamUserVO>();
//		List<User> userList = userDAO.findAllUsers();
//		for(User user : userList){
//			ExamUserVO vo = new ExamUserVO();
//			BeanUtils.copyProperties(user, vo);
//			userVOList.add(vo);
//		}
//		return userVOList;
//	}
//
//	@Override
//	public User getByUserName(String userName) {
//		return (User) userDAO.createQuery("From User Where userName = ?").setParameter(0, userName).uniqueResult();
//	}
//
//	@Override
//	public void editUser(User user) throws Exception {
//		userDAO.editUser(user);
//	}
//
//
//}
