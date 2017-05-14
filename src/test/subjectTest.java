//package test;
//
//import static org.junit.Assert.*;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.hao.onlineExam.dao.IExamTestDAO;
//import com.hao.onlineExam.dao.ISubjectDAO;
//import com.hao.onlineExam.model.ExamSubject;
//import com.hao.onlineExam.model.ExamTest;
//import com.hao.onlineExam.model.ExamTestType;
//import com.hao.onlineExam.model.ExamUserSubject;
//import com.hao.onlineExam.model.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:WebContent/WEB-INF/test-servlet.xml" })
//public class subjectTest {
//	
//	@Autowired
//	private ISubjectDAO subjectDAO;
//	
//	@Test
//	public void testFindAllExamTest() {
//		List<ExamSubject> list = subjectDAO.findAllSubjects();
//		System.out.println("---查询全部的科目内容----");
//		for (ExamSubject examSubject : list) {
//			System.out.println(examSubject.getName());
//		}
//		System.out.println("---------------------");
//	}
//
//	@Test
//	public void testCreateExamTest() throws Exception {
//		ExamSubject examSubject = new ExamSubject();
//		examSubject.setSubId(6);
//		System.out.println("插入了一条ID为" + examSubject.getSubId() + "的数据");
//	}
//
//	@Test
//	public void testEditExamTest() throws Exception {
//		ExamSubject examTest = new ExamSubject();
//		examTest.setSubId(888);
//		subjectDAO.editSubject(examTest);
//		System.out.println("更新了一条ID为" + examTest.getSubId() + "的数据");
//	}
//
//	@Test
//	public void testDeleteExamTest() throws Exception {
//		ExamSubject examTest = new ExamSubject();
//		subjectDAO.deleteSubject(6);
//		System.out.println("删除了一条ID为" + examTest.getSubId() + "的数据");
//	}
//	
//	@Test
//	public void testFindExamTestById() throws Exception {
//		ExamSubject examTest = subjectDAO.getSubjectById(1);
//		System.out.println("查到了一条ID为" + examTest.getSubId() + "的数据");
//	}
//	
//
//	
//}
