package com.OCare.dao;

import com.OCare.entity.Elder;
import com.OCare.entity.OfPropertyEntity;

import java.util.List;

/**
 * Created by fowafolo on 15/7/28.
 */
public interface ElderDAO extends IGeneralDAO<Elder> {
    public Elder queryByPhoneNum(String phoneNum);
    public Elder getElderByRelativePhone(String phone);
    public List<Elder> getEldersByPhoneNum(String phoneNum);

    /*
        功能：根据姓名拿到所有老人
     */
    public List<Elder> getAllEldersByName(String elderName);

    /*
        功能：根据公司拿到所有老人
     */
    public List<Elder> getAllEldersByCompanyId(int companyId);

    public OfPropertyEntity getPropertyValue();
}
