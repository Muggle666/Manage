package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hao.onlineExam.dao.IExamTestDAO;
import com.hao.onlineExam.dao.imp.ExamTestHibernateDAOImp;
import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.ExamTestType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:WebContent/WEB-INF/test-servlet.xml" })
public class examHibernateTest {
	@Autowired
	private IExamTestDAO userHibernateDAO;

	@Test
	public void testFindAllExamTest() {
		List<ExamTest> list = (List<ExamTest>) userHibernateDAO.findAllExamTest();
		System.out.println("---查询全部的考试内容----");
		for (ExamTest examTest : list) {
			System.out.println(examTest.getContent());
		}
		System.out.println("---------------------");
	}

	@Test
	public void testCreateExamTest() throws Exception {
		ExamTest examTest = new ExamTest();
		examTest.setId(2);
		examTest.setContent("aaa");
		examTest.setChooseA("1");
		examTest.setChooseB("2");
		examTest.setChooseC("3");
		examTest.setChooseD("4");
		examTest.setSubId(2);
		examTest.setAnswer("A");
		System.out.println("插入了一条ID为" + examTest.getId() + "的数据");
	}

	@Test
	public void testEditExamTest() throws Exception {
		ExamTest examTest = new ExamTest();
		examTest.setId(888);
		userHibernateDAO.editExamTest(examTest);
		System.out.println("更新了一条ID为" + examTest.getId() + "的数据");
	}

	@Test
	public void testDeleteExamTest() throws Exception {
		ExamTest examTest = new ExamTest();
		userHibernateDAO.delateExamTest(1);
		System.out.println("删除了一条ID为" + examTest.getId() + "的数据");
	}

	@Test
	public void testFindExamTestById() throws Exception {
		ExamTest examTest = userHibernateDAO.getExamTestById(1);
		System.out.println("查到了一条ID为" + examTest.getId() + "的数据");
	}

//	@Test
//	public void testFindAllTable() throws Exception {
//		List list = userHibernateDAO.findAllTable();
//		if (list instanceof ExamTest) {
//			List<ExamTest> listA = list;
//			for (ExamTest examTest : listA) {
//				System.out.println("TYPE:ExamTest");
//				System.out.println(examTest.getAnswer());
//			}
//		} 
//			else if (list instanceof ExamTestType) {
//			List<ExamTestType> listB = list;
//			for (ExamTestType examTestType : listB) {
//				System.out.println("TYPE:ExamTestType");
//				System.out.println(examTestType);
//			}
//		}  
//	else if (list instanceof Object) {
//			System.out.println("TYPE:Object");
//			Iterator it = list.iterator();
//			while(it.hasNext()){
//				Object str = it.next();
//				System.out.println(str.toString());
//			}
//		}else if (list == null) {
//			System.out.println("The data is null");
//		} else {
//			System.out.println("error");
//		}
////		List<ExamTest> list = userHibernateDAO.findAllTable();
////		for(ExamTest exam : list){
////			System.out.println(exam.getId());
////		}
//	}
}
