package com.OCare.dao;

import com.OCare.entity.Admin;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ma on 2015/11/18.
 */
@Repository
public class AdminDaoImp extends IGeneralDAOImpl<Admin> implements AdminDao  {
    public AdminDaoImp()
    {
        super(Admin.class);
    }


    @Override
    public Admin queryByPhoneNum(String phoneNum) {
        String hql = "from Admin where id = :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m", phoneNum);
        List<Admin> list = query.list();
        if (list.size() == 0){
            return null;
        }else{
            return list.get(0);
        }
    }
}
