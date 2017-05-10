package test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hao.onlineExam.dao.IUserDAO;
import com.hao.onlineExam.dao.imp.UserHibernateDAOImp;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.ExamTestType;
import com.hao.onlineExam.model.ExamUserSubject;
import com.hao.onlineExam.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:WebContent/WEB-INF/test-servlet.xml" })
public class userTest {
	@Autowired
	private IUserDAO userDAO;
	
	@Test
	public void testFindAllExamTest() {
		List<User> list = userDAO.findAllUsers();
		System.out.println("---查询全部的学生名字----");
		for (User user : list) {
			System.out.println(user.getUserName());
		}
		System.out.println("---------------------");
	}

	@Test
	public void testCreateExamTest() throws Exception {
		User user = new User();
		user.setUserId("1");
		user.setUserName("奥巴马");
		System.out.println("插入了一条ID为" +user.getUserId()+ "的数据");
	}

	@Test
	public void testEditExamTest() throws Exception {
		User user = new User();
		user.setUserId("888");
		user.setUserName("毛泽东");
		System.out.println("更新了一条名字为" + user.getUserId() + "的数据");
	}

	@Test
	public void testDeleteExamTest() throws Exception {
		User user = new User();
		user.setUserId("1");
		userDAO.delete(user);
		System.out.println("删除了一条ID为" + user.getUserId() + "的数据");
	}
}
