package com.OCare.dao;

import com.OCare.entity.ofUser;
import org.springframework.stereotype.Repository;

/**
 * Created by Ma on 2015/11/21.
 */
@Repository
public class ofUserDaoImp extends IGeneralDAOImpl<ofUser> implements ofUserDao {
    public ofUserDaoImp()
    {
        super(ofUser.class);
    }
}
