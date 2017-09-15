package com.OCare.service;

import com.OCare.dao.*;
import com.OCare.entity.*;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mark on 8/2/15.
 */
@Service("LogonService")
@Transactional
public class AccountServiceImp implements AccountService {

    @Autowired
    private ElderDAO elderDAO;
    @Autowired
    private RelativeDAO relativeDAO;
    @Autowired
    private VolunteerDAO volunteerDAO;
    @Autowired
    private LegalPersonDAO legalPersonDAO;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private EmployeeDAO employeeDao;
    @Autowired
    private ofUserDao userDao;

    @Override
    public Pair<String, Object> logon(String phoneNum, String password,int role) {
        String md5Password = JavaMD5Util.MD5(password);

        Elder elder = null;
        Relative relative = null;
        Volunteer volunteer = null;
        LegalPerson legalPerson = null;
        if(role==0) {
            elder = elderDAO.queryByPhoneNum(phoneNum);
        }else if(role==1) {
            relative = relativeDAO.queryByPhoneNum(phoneNum);
        }else if(role==5) {
            volunteer = volunteerDAO.queryByPhoneNum(phoneNum);
        }else if(role==2) {
            legalPerson = legalPersonDAO.queryByPhoneNum(phoneNum);
        }else{
            Pair<String, Object> pair = new Pair<String, Object>("Invalid Role", null);
            return pair;
        }
        if(elder != null){
            if (elder.getPassword().equals(md5Password)){
                Pair<String, Object> pair = new Pair<String, Object>("0", elder);
                return pair;
            }else{
                Pair<String, Object> pair = new Pair<String, Object>("Incorrect password", null);
                return pair;
            }
        }else if(relative != null){
            if(relative.getPassword().equals(md5Password)){
                Pair<String, Object> pair = new Pair<String, Object>("1", relative);
                return pair;
            }else{
                Pair<String, Object> pair = new Pair<String, Object>("Incorrect password", null);
                return pair;
            }
        }else if (volunteer != null){
            if (volunteer.getPassword().equals(md5Password)){
                Pair<String, Object> pair = new Pair<String, Object>("5", volunteer);
                return pair;
            }else{
                Pair<String, Object> pair = new Pair<String, Object>("Incorrect password", null);
                return pair;
            }
        }else if (legalPerson != null){
            if (legalPerson.getPassword().equals(md5Password)){
                Pair<String, Object> pair = new Pair<String, Object>("2", legalPerson);
                return pair;
            }else{
                Pair<String, Object> pair = new Pair<String, Object>("Incorrect password", null);
                return pair;
            }
        }else{
            Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
            return pair;
        }
    }

    @Override
    public Pair<String, Object> adminlogon(String phoneNum, String password) {
        String md5Password = JavaMD5Util.MD5(password);
        Admin admin=adminDao.queryByPhoneNum(phoneNum);
        if(admin != null){
            if (admin.getPassword().equals(md5Password)){
                Pair<String, Object> pair = new Pair<String, Object>("Admin", admin);
                return pair;
            }else{
                Pair<String, Object> pair = new Pair<String, Object>("Incorrect password", null);
                return pair;
            }
        }else{
            Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
            return pair;
        }
    }

    @Override
    public String verifyPhoneNum(String id, String phoneNum,int role) {
        if(role==1){
        Elder elder = elderDAO.queryById(id);
        Relative relative = relativeDAO.queryById(id);
        if(elder != null){
            if (elder.getPhone().equals(phoneNum)){
                return "Elder";
            }else{
                return "Incorrect phone number with ID";
            }
        }
            if(relative != null){
                if (relative.getPhone().equals(phoneNum)){
                    return "Relative";
                }else{
                    return "Incorrect phone number with ID";
                }
            } else {
            return "Invalid Account";
              }
        }

        else if(role==2) {
            LegalPerson legalPerson = legalPersonDAO.queryById(id);
            if (legalPerson != null) {
                if (legalPerson.getPhone().equals(phoneNum)) {
                    return "LegalPerson";
                } else {
                    return "Incorrect phone number with ID";
                }
            } else {
                return "Invalid Account";
            }
        }

        else if(role==3) {
            Employee employee = employeeDao.queryById(id);
            if (employee != null) {
                if(employee.getPhone().equals(phoneNum)){
                    return "Employee";
                }
                else {
                    return "Incorrect phone number with ID";
                }
            } else {
                return "Invalid Account";
            }
        }

        else if(role==4){
            return "admin";
        }

        else if(role==5) {
            Volunteer volunteer = volunteerDAO.queryById(id);
            if (volunteer != null) {
                if (volunteer.getPhone().equals(phoneNum)) {
                    return "Volunteer";
                } else {
                    return "Incorrect phone number with ID";
                }
            } else {
                return "Invalid Account";
            }
        }

        else{
            return "NO role";
        }
    }

    @Override
    public String changePassword(String id, String password) {
        String md5Password = JavaMD5Util.MD5(password);
        Elder elder = elderDAO.queryById(id);
        Relative relative = relativeDAO.queryById(id);
        Volunteer volunteer = volunteerDAO.queryById(id);
        LegalPerson legalPerson = legalPersonDAO.queryById(id);
        if(elder != null){
            elder.setPassword(md5Password);
            elderDAO.update(elder);
            return "change success";
        }else if(relative != null){
            relative.setPassword(md5Password);
            relativeDAO.update(relative);
            return "change success";
        }else if (volunteer != null){
            volunteer.setPassword(md5Password);
            volunteerDAO.update(volunteer);
            return "change success";
        }else if (legalPerson != null){
            legalPerson.setPassword(md5Password);
            legalPersonDAO.update(legalPerson);
            return "change success";
        }else{
            return "Invalid Account";
        }
    }


    @Override
    public String lostPasswordHandle(String id, int role, String password,String phoneNum) {
        String md5Password = JavaMD5Util.MD5(password);
        if(role==1){
           String passwordKey=elderDAO.getPropertyValue().getPropValue();

            Blowfish blowFish = new Blowfish(passwordKey); //根据加密key初始化
            String openfirePassword = blowFish.encryptString(password); //加密

            Elder elder = elderDAO.queryById(id);
            Relative relative = relativeDAO.queryById(id);
            ofUser user=userDao.queryById(phoneNum);

            if(elder != null||relative != null) {
                if (elder != null) {
                    elder.setPassword(md5Password);
                    user.setEncryptedPassword(openfirePassword);
                    elderDAO.update(elder);
                    userDao.update(user);
                }
                if (relative != null) {
                    relative.setPassword(md5Password);
                    user.setEncryptedPassword(openfirePassword);
                    relativeDAO.update(relative);
                    userDao.update(user);
                }
                return "change success";
            }
            else{
                return "Invalid Account";
            }
        }else if(role==2){
            LegalPerson legalPerson = legalPersonDAO.queryById(id);
            if (legalPerson != null){
                legalPerson.setPassword(md5Password);
                legalPersonDAO.update(legalPerson);
                return "change success";
            }else{
                return "Invalid Account";
            }
        }else if(role==3){
            Employee employee=employeeDao.queryById(id);
            if (employee != null){
                employee.setPassword(md5Password);
                employeeDao.update(employee);
                return "change success";
            }else{
                return "Invalid Account";
            }
        }else if(role==4){
            Admin admin=adminDao.queryById(id);
            if (admin != null){
                admin.setPassword(md5Password);
                adminDao.update(admin);
                return "change success";
            }else{
                return "Invalid Account";
            }
        }else if(role==5){
            Volunteer volunteer = volunteerDAO.queryById(id);
            if (volunteer != null){
                volunteer.setPassword(md5Password);
                volunteerDAO.update(volunteer);
                return "change success";
            }else{
                return "Invalid Account";
            }
        }else {
            return "NO ROLE";
        }
    }

    @Override
    public Pair<String, Object> personInforModifyHandle(String phoneNum,int role,String change,int type ) {
        if(role==0)
        {
            String id=elderDAO.queryByPhoneNum(phoneNum).getId();
            Elder elder = elderDAO.queryById(id);
            ofUser user=userDao.queryById(phoneNum);
            if(elder==null)
            {
                Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
                return pair;
            }
            if(type==0){
                elder.setPhone(change);
            }
            if(type==1){
                String md5Password = JavaMD5Util.MD5(change);
                String passwordKey=elderDAO.getPropertyValue().getPropValue();

                Blowfish blowFish = new Blowfish(passwordKey); //根据加密key初始化
                String openfirePassword = blowFish.encryptString(change); //加密
                elder.setPassword(md5Password);
                user.setEncryptedPassword(openfirePassword);
            }
            if(type==2){
                elder.setImage(change);
            }
            elderDAO.update(elder);
            userDao.update(user);
            Pair<String, Object> pair = new Pair<String, Object>("change success", elder);
            return pair;

        }
        else if(role==1)
        {
            String id=relativeDAO.queryByPhoneNum(phoneNum).getId();
            Relative relative = relativeDAO.queryById(id);
            ofUser user=userDao.queryById(phoneNum);
            if(relative==null)
            {
                Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
                return pair;
            }
            if(type==0){
                relative.setPhone(change);
            }
            if(type==1){
                String md5Password = JavaMD5Util.MD5(change);
                String passwordKey=elderDAO.getPropertyValue().getPropValue();

                Blowfish blowFish = new Blowfish(passwordKey); //根据加密key初始化
                String openfirePassword = blowFish.encryptString(change); //加密
                relative.setPassword(md5Password);
                user.setEncryptedPassword(openfirePassword);
            }
            if(type==2){
                relative.setImage(change);
            }
            relativeDAO.update(relative);
            userDao.update(user);
            Pair<String, Object> pair = new Pair<String, Object>("change success", relative);
            return pair;
        }
        else if(role==2)
        {
            LegalPerson legalPerson=legalPersonDAO.queryByPhoneNum(phoneNum);
            if(legalPerson==null)
            {
                Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
                return pair;
            }
            if(type==0){
                legalPerson.setPhone(change);
            }
            if(type==1){
                String md5Password = JavaMD5Util.MD5(change);
                legalPerson.setPassword(md5Password);
            }
            if(type==1){
                legalPerson.setImage(change);
            }
            legalPersonDAO.update(legalPerson);
            Pair<String, Object> pair = new Pair<String, Object>("change success", legalPerson);
            return pair;
        }
        else if(role==3)
        {
            Admin admin=adminDao.queryById(phoneNum);
            if(admin==null)
            {
                Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
                return pair;
            }

            if(type==1){
                String md5Password = JavaMD5Util.MD5(change);
                admin.setPassword(md5Password);
            }

            adminDao.update(admin);
            Pair<String, Object> pair = new Pair<String, Object>("change success", admin);
            return pair;
        }
        else if(role==4)
        {
            String id=employeeDao.queryByPhoneNum(phoneNum).getId();
            Employee employee=employeeDao.queryById(id);
            if(employee==null)
            {
                Pair<String, Object> pair = new Pair<String, Object>("Invalid Account", null);
                return pair;
            }
            if(type==0){
                employee.setPhone(change);
            }
            if(type==1){
                String md5Password = JavaMD5Util.MD5(change);
                employee.setPassword(md5Password);
            }
            if(type==2){
                employee.setImage(change);
            }
            employeeDao.update(employee);
            Pair<String, Object> pair = new Pair<String, Object>("change success", employee);
            return pair;
        }
        else
        {
            Pair<String, Object> pair = new Pair<String, Object>("ROLE NOT EXIST", null);
            return pair;
        }
    }


    @Override
    public Pair<String, Object> getImageByPhoneNum(String phoneNum) {
        Elder elder = elderDAO.queryByPhoneNum(phoneNum);
        Relative relative = relativeDAO.queryByPhoneNum(phoneNum);
        Volunteer volunteer = volunteerDAO.queryByPhoneNum(phoneNum);
        LegalPerson legalPerson = legalPersonDAO.queryByPhoneNum(phoneNum);

        if (elder!=null)
        {
            Pair<String, Object> pair = new Pair<String, Object>("get image successfully.",elder.getImage());
            return pair;
        }else if(relative != null){
            Pair<String, Object> pair = new Pair<String, Object>("get image successfully.",relative.getImage());
            return pair;
        }else if (volunteer != null){
            Pair<String, Object> pair = new Pair<String, Object>("get image successfully.",volunteer.getImage());
            return pair;
        }else if (legalPerson != null){
            Pair<String, Object> pair = new Pair<String, Object>("get image successfully.",legalPerson.getImage());
            return pair;
        }else{
            Pair<String, Object> pair = new Pair<String, Object>("PhoneNum does not match to any account!",null);
            return pair;
        }
    }

    @Override
    public boolean isChangeImageSucc(String phoneNum,String newImg) {
        Elder elder = elderDAO.queryByPhoneNum(phoneNum);
        Relative relative = relativeDAO.queryByPhoneNum(phoneNum);
        Volunteer volunteer = volunteerDAO.queryByPhoneNum(phoneNum);
        LegalPerson legalPerson = legalPersonDAO.queryByPhoneNum(phoneNum);
        boolean flag ;
        if (elder!=null)
        {
            elder.setImage(newImg);
            elderDAO.update(elder);
            flag = true;
        }else if(relative != null){
            relative.setImage(newImg);
            relativeDAO.update(relative);
            flag = true;
        }else if (volunteer != null){
            volunteer.setImage(newImg);
            volunteerDAO.update(volunteer);
            flag = true;
        }else if (legalPerson != null){
            legalPerson.setImage(newImg);
            legalPersonDAO.update(legalPerson);
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
}
