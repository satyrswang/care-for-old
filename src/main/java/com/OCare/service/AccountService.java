package com.OCare.service;

import javafx.util.Pair;

/**
 * Created by mark on 8/2/15.
 */
public interface AccountService {
    public Pair<String, Object> logon(String phoneNum, String password,int role);
    public Pair<String, Object> adminlogon(String phoneNum, String password);
    public String verifyPhoneNum(String id, String phoneNum,int role);
    public String changePassword(String id, String password);
    public Pair<String, Object> personInforModifyHandle(String id,int role,String change,int type );
    public String lostPasswordHandle(String id, int role,String password,String phoneNum);

    /*
        功能：通过某人的电话号查Image
        参数：电话号码phoneNum
        返回值：Image的String值
     */
    public Pair<String, Object> getImageByPhoneNum(String phoneNum);

    /*
        功能：通过电话号码改Image
        参数：电话号码phoneNum，新照片newImg
        返回值：是否成功，true为成功，false为失败
     */
    public boolean isChangeImageSucc(String phoneNum,String newImg);
}
