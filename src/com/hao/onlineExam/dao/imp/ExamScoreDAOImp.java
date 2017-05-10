package com.hao.onlineExam.dao.imp;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.vo.ExamScoreVO;

@Repository
public class ExamScoreDAOImp extends BaseHibernateDAO<ExamScore> implements IExamScoreDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamScoreVO> getAllExamScore(Map<String,String> conditionMap) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select es.id as id,user.userId as userId,user.userName as userName,sub.subId as subId ,sub.name as name");
		hql.append(" from ExamScore es ,User user ,ExamSubject sub ");
		hql.append(" where es.userId = user.userId");
		hql.append(" and es.subId = sub.subId");
		if(conditionMap != null){
			Iterator<Entry<String,String>> it = conditionMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String,String> currentCondition = it.next();
				if("name".equals(currentCondition.getKey())){
					hql.append(" and sub.name like '%" + currentCondition.getValue() + "%'");
				}else if("userId".equals(currentCondition.getKey())){
					hql.append(" and user.userId like '%" + currentCondition.getValue() + "%'");
				}else if("userName".equals(currentCondition.getKey())){
					hql.append(" and user.userName like '%" + currentCondition.getValue() + "%'");
				}else if("examTime".equals(currentCondition.getKey())){
					if(currentCondition.getValue() != null){
						hql.append(" and DATE_FORMAT(es.examTime,'%Y-%m-%d %H:%i') like '%" + currentCondition.getValue() + "%'");
					}
				}
			}
		}
		return this.createQuery(hql.toString()).setResultTransformer(Transformers.aliasToBean(ExamScoreVO.class)).list();
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

//	@Override
//	public ExamScore getScoreBySubId(Integer subId) {
//		String hql = " FROM ExamScore es where es.subId = :subId ";
//		return (ExamScore) this.createQuery(hql).setInteger("subId",subId).list();
//	}

}
