package com.hao.onlineExam.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hao.onlineExam.dao.IExamTestDAO;
import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.SystemContext;

@Repository
public class ExamTestHibernateDAOImp extends BaseHibernateDAO<ExamTest> implements IExamTestDAO {

	@Override
	public void createExamTest(ExamTest examTest) throws Exception {
		save(examTest);
	}

	@Override
	public void editExamTest(ExamTest examTest) throws Exception {
		update(examTest);
	}

	@Override
	public void delateExamTest(Integer id) throws Exception {
		ExamTest existsExamTest = this.get(id);
		if(existsExamTest != null) this.delete(existsExamTest);
	}

	@Override
	public ExamTest getExamTestById(Integer id){
		return get(id);
	}


	@Override
	@SuppressWarnings("unchecked")
	public PagerModel<ExamTest> findAllExamTest() {
		PagerModel<ExamTest> page = new PagerModel<ExamTest>();
		ArrayList<ExamTest> dates = new ArrayList<ExamTest>();
		int offset = SystemContext.getOffset();
		int pageSize = SystemContext.getSize();
		//使用hibernate完成分页
		dates = (ArrayList<ExamTest>) this.getSession().createQuery("From ExamTest").setFirstResult(offset).setMaxResults(pageSize).list();
		page.setDates(dates);
		page.setOffset(offset);
		page.setPageSize(pageSize);
		//获取总记录数
		long totalSize = (long) this.getSession().createQuery("select count(*) from ExamTest ").uniqueResult();
		page.setTotalSize(totalSize);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamTest> getTestListBySubAndTestType(Integer subId, Integer id) {
//		String hql = "SELECT id, content, chooseA, chooseB, chooseC, chooseD, subId, testTypeId, answer FROM ExamTest et WHERE et.subId = :subId AND et.testTypeId = :id ";
		String hql = "FROM ExamTest et WHERE et.subId = :subId AND et.examTestType.id = :id ";
		return this.createQuery(hql)
//				.setResultTransformer(Transformers.aliasToBean(ExamTest.class))
				.setInteger("subId", subId)
				.setInteger("id", id)
				.list();
		
	}

	@Override
	public List<ExamTest> getTestListByIdList(List<Integer> randomTestIdList) {
		return null;
	}

	@Override
	public List<ExamTest> getExamTestBySubId(Integer subId) {
		return queryForList("FROM ExamTest", null);
	}
}
