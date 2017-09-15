package com.OCare.dao;

import com.OCare.entity.Elder;
import com.OCare.entity.OfPropertyEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fowafolo on 15/7/28.
 */
@Repository
public class ElderDAOImp extends IGeneralDAOImpl<Elder> implements ElderDAO{

    public ElderDAOImp()
    {
        super(Elder.class);
    }

    @Override
    public Elder queryByPhoneNum(String phoneNum) {
        String hql = "from Elder where phone = :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m", phoneNum);
        List<Elder> list = query.list();
        if (list.size() == 0){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public List<Elder> getEldersByPhoneNum(String phoneNum) {
        String hql = "from Elder where phone = :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m", phoneNum);
        return query.list();
    }

    @Override
    public Elder getElderByRelativePhone(String phone) {
        String hql = "from ofMucRoom where name like :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m", phone);
        List<Elder> list = query.list();
        if (list.size() == 0){
            return null;
        }else{
            String name=list.get(0).getName();
            //有待处理字符串
            String elderPhone=name;
            String _hql = "from Elder where phone = :m";
            Query _query = sessionFactory.getCurrentSession().createQuery(hql);
            _query.setString("m", elderPhone);
            List<Elder> elist = _query.list();
            if (elist.size() == 0){
                return null;
            }else{
                return elist.get(0);
            }
        }
    }



    @Override
    public List<Elder> getAllEldersByName(String elderName) {
        String hql = "from Elder where name = :n";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("n",elderName);
        return query.list();
    }

    @Override
    public List<Elder> getAllEldersByCompanyId(int companyId) {
        String hql = "from Elder where company_id = :n";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger("n",companyId);
        return query.list();
    }

    @Override
    public OfPropertyEntity getPropertyValue() {
        String passwordKey="passwordKey";
        String hql="from OfPropertyEntity where name= :n";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("n",passwordKey);
        List<OfPropertyEntity> list=query.list();
        return list.get(0);
    }
}
