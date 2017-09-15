package com.OCare.dao;

import com.OCare.entity.ofMucAffiliation;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mark on 10/27/15.
 */
@Repository
public class ofMucAffiliationDAOImpl extends IGeneralDAOImpl<ofMucAffiliation> implements ofMucAffiliationDAO {
    public ofMucAffiliationDAOImpl() {
        super(ofMucAffiliation.class);
    }

    @Override
    public List<ofMucAffiliation> getAffiliationByPhoneNum(String phoneNum) {
        String hql = "from ofMucAffiliation where jid LIKE :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m", "%"+phoneNum+"%");
        return query.list();
    }

    @Override
    public List<ofMucAffiliation> getAffiliationByPhoneNumandRoomid(String phoneNum, int id) {
        String hql = "FROM ofMucAffiliation WHERE roomID = :n AND jid LIKE :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger("n",  id);
        query.setString("m","%" + phoneNum + "%");
        return query.list();
    }
}
