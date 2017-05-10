//package test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.hao.onlineExam.dao.IUserAndSubjectDAO;
//import com.hao.onlineExam.dao.imp.UserHibernateDAOImp;
//import com.hao.onlineExam.model.ExamUserSubject;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:WebContent/WEB-INF/test-servlet.xml" })
//public class userSubjectTest {
//	@Autowired
//	private IUserAndSubjectDAO user;
//	
//	@Test
//	public void testFindSubjectByUser() throws Exception {
////		ExamUserSubject user = new ExamUserSubject();
//		List<ExamUserSubject> list = user.findSubjectByUser();
//		for(ExamUserSubject subject : list){
//			System.out.println("学生ID为"+subject.getUser().getUserId()+" 名字为"+subject.getUser().getUserName()+
//					" 需要考试的科目有："+subject.getExamSubjcet().getName()+subject.getExamSubjcet().getDescription());
//		}
//	}
//}
