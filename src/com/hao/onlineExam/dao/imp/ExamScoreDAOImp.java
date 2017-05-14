package com.hao.onlineExam.dao.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.SystemContext;
import com.hao.onlineExam.model.vo.ExamScoreVO;

@Repository
public class ExamScoreDAOImp extends BaseHibernateDAO<ExamScore> implements IExamScoreDAO{

	@SuppressWarnings("unchecked")
	@Override
	public PagerModel<ExamScoreVO> getAllExamScore(Map<String,String> conditionMap) {
		StringBuilder hql = new StringBuilder();
		StringBuilder count = new StringBuilder();
		StringBuilder condition = new StringBuilder();
		
		hql.append("SELECT es.id AS id, user.userId AS userId, user.userName AS userName, sub.subId AS subId, sub.name AS name, es.examTime AS examTime, es.score AS score ");
		hql.append("FROM ExamScore es, User user, ExamSubject sub ");
		hql.append("WHERE es.userId = user.userId ");
		hql.append("AND es.subId = sub.subId ");
		
		count.append("SELECT count(*) ");
		count.append("FROM ExamScore es, User user, ExamSubject sub ");
		count.append("WHERE es.userId = user.userId ");
		count.append("AND es.subId = sub.subId ");
		
		if(conditionMap != null){
			Iterator<Entry<String, String>> it = conditionMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, String> currentCondition = it.next();
				if("name".equals(currentCondition.getKey())){
					condition.append("AND sub.name LIKE '%" + currentCondition.getValue() +"%' ");
				}else if("userId".equals(currentCondition.getKey())){
					condition.append("AND user.userId LIKE '%" + currentCondition.getValue() +"%' ");
				}else if("userName".equals(currentCondition.getKey())){
					condition.append("AND user.userName LIKE '%" + currentCondition.getValue() +"%' ");
				}else if("examTime".equals(currentCondition.getKey())){
					if(currentCondition.getValue() != null){
						condition.append("AND DATE_FORMAT(es.examTime, '%Y-%m-%d %H:%i:%s') LIKE '%" + currentCondition.getValue() +"%' ");
					}
				}
			}
		}
		//分页
				PagerModel<ExamScoreVO> page = new PagerModel<ExamScoreVO>();
				ArrayList<ExamScoreVO> dates = new ArrayList<ExamScoreVO>();
				int offset = SystemContext.getOffset();
				int pageSize = SystemContext.getSize();
				//使用hibernate完成分页
				dates = (ArrayList<ExamScoreVO>) this.getSession().createQuery(hql.toString() + condition.toString()).setFirstResult(offset).setMaxResults(pageSize).setResultTransformer(Transformers.aliasToBean(ExamScoreVO.class)).list();
				page.setDates(dates);
				page.setOffset(offset);
				page.setPageSize(pageSize);
				//获取总记录数
				long totalSize = (long) this.getSession().createQuery(count.toString() + condition.toString()).uniqueResult();
				page.setTotalSize(totalSize);
				return page;
	}

	@Override
	public ExamScore getExamScore(String userId,Integer subId) {
		String hql = "FROM ExamScore es WHERE es.userId = :userId AND es.subId = :subId";
		return (ExamScore) this.createQuery(hql)
				.setString("userId", userId)
				.setInteger("subId", subId)
				.uniqueResult();
	}

	@Override
	public ExamScoreVO getScoreById(Integer scoreId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select user.userName as userName,sub.name as name,es.examTime as examTime,es.score as score");
		hql.append(" from ExamScore es,User user,ExamSubject sub ");
		hql.append(" where es.userId = user.userId ");
		hql.append(" and es.subId = sub.subId ");
		hql.append(" and es.id = :scoreId ");
		return (ExamScoreVO) this.createQuery(hql.toString())
				.setResultTransformer(Transformers.aliasToBean(ExamScoreVO.class))
				.setInteger("scoreId", scoreId)
				.uniqueResult();
	}

}
