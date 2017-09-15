package com.OCare.dao;


import com.OCare.entity.Admin;

/**
 * Created by Ma on 2015/11/18.
 */
public interface AdminDao extends IGeneralDAO<Admin> {
    public Admin queryByPhoneNum(String phoneNum);
}
