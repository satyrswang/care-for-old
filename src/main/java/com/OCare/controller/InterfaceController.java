package com.OCare.controller;

import com.OCare.dao.LegalPersonDAO;
import com.OCare.entity.*;
import com.OCare.service.*;
import it.sauronsoftware.ftp4j.FTPClient;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.*;

import javax.management.MBeanServer;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.management.MBeanServer;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;

/**
 * Created by mark on 8/2/15.
 */
@Controller
@RequestMapping("/app")
public class InterfaceController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private ElderService elderService;
    @Autowired
    private RelativeService relativeService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ElderConditionService elderConditionService;


    @Autowired
    private CompanyService companyService;
    @Autowired
    private openFireService openfireService;
    @Autowired
    private FtpService ftpService;
    @Autowired
    private LegalPersonDAO legalPersonDAO;
    /**
     * @param phoneNum：Phone    number
     * @param password：Password
     * @return Error: true, ErrorMsg
     * Error: false, Account type, Account detail
     */
    @RequestMapping(value = "/logon")
    @ResponseBody

    public Map<String, Object> logon(String phoneNum, String password,int role,HttpSession httpSession){

        Map<String, Object> result = new HashMap<String, Object>();

        if (phoneNum == null || password == null || phoneNum == "" || password == "") {
            result.put("error", true);
            result.put("errorMsg", "PhoneNum or password is empty");
            return result;

        }else{
            String status = accountService.logon(phoneNum, password,role).getKey();
            if(status == "Invalid Account"){

                result.put("error", true);
                result.put("errorMsg", "Invalid Account");
                return result;
            } else if (status == "Incorrect password") {
                result.put("error", true);
                result.put("errorMsg", "Incorrect password");
                return result;

            }else{
                httpSession.setAttribute("sessionId",phoneNum);
                httpSession.setAttribute("sessionType",status);

                result.put("error", false);
                result.put("accountType", status);
                result.put("account", accountService.logon(phoneNum, password,role).getValue());
                return result;
            }
        }
    }

    /*@RequestMapping(value = "/logouttest")
    @ResponseBody
    public String logout(HttpSession httpSession){
        String status=(String) httpSession.getAttribute("sessionType");
        return status;
    }*/

    //admin的logon函数
    @RequestMapping(value = "/adminlogon")
    @ResponseBody
    public Map<String, Object> adminlogon(String phoneNum, String password,HttpSession httpSession){
        Map<String, Object> result = new HashMap<String, Object>();

        if(phoneNum == null || password == null || phoneNum == "" || password == ""){
            result.put("error", true);
            result.put("errorMsg", "PhoneNum or password is empty");
            return result;
        }else{
            String status = accountService.adminlogon(phoneNum, password).getKey();
            if(status == "Invalid Account"){
                result.put("error", true);
                result.put("errorMsg", "Invalid Account");
                return result;
            }else if (status == "Incorrect password"){
                result.put("error", true);
                result.put("errorMsg", "Incorrect password");
                return result;
            }else{
                httpSession.setAttribute("sessionId",phoneNum );
                httpSession.setAttribute("sessionType",status );
                result.put("error", false);
                result.put("accountType", status);
                result.put("account", accountService.adminlogon(phoneNum,password).getValue());
                return result;
            }
        }
    }

/*
    @RequestMapping(value = "/adminRegister")
    @ResponseBody
    public void adminRegister(String user,String password){
        registerService.register(user,password);
    }*/


    //logout函数
    @RequestMapping(value = "/logout")
    @ResponseBody
    public void logout(String sessionId,HttpSession httpSession){


        if(sessionId==""||sessionId==null) {
            httpSession.invalidate();
        }
        else{
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
            httpSession.invalidate();
        }


    }



    /**
     * @param elderId:       Elder CitizenId
     * @param companyId:     Company Id
     * @param elderName:     Elder Name
     * @param elderPhone:    Elder Phone number
     * @param elderAddress:  Elder address
     * @param elderPassword: Elder Password
     * @param elderImage:    Elder Image
     * @return Error: false, account detail
     */
    @RequestMapping(value = "/register/elder")
    @ResponseBody
    public Map<String, Object> elderRegister(String elderId, int companyId, String elderName,
                                             String elderPhone, String elderAddress,
                                             String elderPassword, String elderImage) {
        Map<String, Object> result = new HashMap<String, Object>();

        //身份证或者手机号已经注册

        if (isIdOrPhoneNumExist_elder(elderId,elderPhone)==1)
        {
            result.put("error",true);
            result.put("errorMsg","openfire phone exists");

            return result;
        }
        if (isIdOrPhoneNumExist_elder(elderId,elderPhone)==2)
        {
            result.put("error",true);
            result.put("errorMsg","elder id number exists");
            return result;
        }
        if (isIdOrPhoneNumExist_elder(elderId,elderPhone)==3)
        {
            result.put("error",true);
            result.put("errorMsg","elder phone exists");
            return result;
        }
        if (isIdOrPhoneNumExist_elder(elderId,elderPhone)==255)
        {
            result.put("error",true);
            result.put("errorMsg","unknow error");
            return result;
        }

        //身份证或者手机号未被注册
        Elder elder = registerService.registerForAnElder(elderId, companyId, elderName, elderPhone, elderAddress, elderPassword, elderImage);

        result.put("error", false);
        result.put("account", elder);
        return result;
    }

    public int isIdOrPhoneNumExist_elder(String id, String phoneNum) {
        //boolean flag = true;
        ArrayList<Elder> elders = registerService.getAllElders();
        ArrayList<ofUser> users =registerService.getAllUser();
        //ArrayList<Relative> relatives = registerService.getAllRelatives();
        //ArrayList<Volunteer> volunteers = registerService.getAllVolunteers();
        // ArrayList<LegalPerson> legalPersons = registerService.getAllLegalPerson();

        Iterator<Elder> elderIterator = elders.iterator();
        Iterator<ofUser> userIterator=users.iterator();
        //Iterator<Relative> relativeIterator = relatives.iterator();
        //Iterator<Volunteer> volunteerIterator = volunteers.iterator();
        //Iterator<LegalPerson> legalPersonIterator = legalPersons.iterator();

        while (elderIterator.hasNext())
        {
            Elder elder = elderIterator.next();
            if (elder.getId().equals(id))
            {
                return 2;
            }
            if (elder.getPhone().equals(phoneNum))
            {
                return 3;
            }
        }
        while (userIterator.hasNext())
        {
            ofUser user = userIterator.next();
            if (user.getUsername().equals(phoneNum))
            {
                return 1;
            }
        }


        return 0;
    }

    /**
     * @param relativeId:       relative citizen_id
     * @param relativeName:     relative name
     * @param relativePhone:    relative phone number
     * @param relativeAddress:  relative address
     * @param relativePassword: relative password
     * @param relativeImage:    relative image
     * @return Error: false, account detail
     */
    @RequestMapping(value = "/register/relative")
    @ResponseBody
    public Map<String, Object> relativeRegister(String relativeId, String relativeName,
                                                String relativePhone, String relativeAddress,
                                                String relativePassword, String relativeImage) {
        Map<String, Object> result = new HashMap<String, Object>();
        //身份证或者手机号已经注册

        if (isIdOrPhoneNumExist_relative(relativeId,relativePhone)==1)
        {
            result.put("error",true);
            result.put("errorMsg","openfire phone exists");
            return result;
        }
        if (isIdOrPhoneNumExist_relative(relativeId,relativePhone)==2)
        {
            result.put("error",true);
            result.put("errorMsg","relative id number exists");
            return result;
        }
        if (isIdOrPhoneNumExist_relative(relativeId,relativePhone)==3)
        {
            result.put("error",true);
            result.put("errorMsg","relative phone exists");
            return result;
        }
        if (isIdOrPhoneNumExist_relative(relativeId,relativePhone)==255)
        {
            result.put("error",true);
            result.put("errorMsg","unknow error");

            return result;
        }
        //身份证或者手机号未被注册
        Relative relative = registerService.registerForARelative(relativeId, relativeName, relativePhone, relativeAddress, relativePassword, relativeImage);
        result.put("error", false);
        result.put("account", relative);
        return result;
    }

    public int isIdOrPhoneNumExist_relative(String id, String phoneNum) {
        //boolean flag = true;
        //ArrayList<Elder> elders = registerService.getAllElders();
        ArrayList<ofUser> users =registerService.getAllUser();
        ArrayList<Relative> relatives = registerService.getAllRelatives();
        //ArrayList<Volunteer> volunteers = registerService.getAllVolunteers();
        // ArrayList<LegalPerson> legalPersons = registerService.getAllLegalPerson();

        //Iterator<Elder> elderIterator = elders.iterator();
        Iterator<ofUser> userIterator=users.iterator();
        Iterator<Relative> relativeIterator = relatives.iterator();
        //Iterator<Volunteer> volunteerIterator = volunteers.iterator();
        //Iterator<LegalPerson> legalPersonIterator = legalPersons.iterator();

        while (relativeIterator.hasNext())
        {
            Relative relative = relativeIterator.next();
            if (relative.getId().equals(id))
            {
                return 2;
            }
            if (relative.getPhone().equals(phoneNum))
            {
                return 3;
            }
        }
        while (userIterator.hasNext())
        {
            ofUser user = userIterator.next();
            if (user.getUsername().equals(phoneNum))
            {
                return 1;
            }
        }
        return 0;
    }

    /**
     * @param volunteerId:        volunteer citizenId
     * @param volunteerName:      volunteer name
     * @param volunteerPhone:     volunteer phone number
     * @param volunteerAddress:   volunteer address
     * @param volunteerEmail:     volunteer email
     * @param volunteerCompanyId: volunteer's companyId
     * @param volunteerPassword:  volunteer password
     * @param volunteerImage:     volunteer image
     * @return Error: false, account detail
     */
    @RequestMapping(value = "/register/volunteer")
    @ResponseBody
    public Map<String, Object> volunteerRegister(String volunteerId, String volunteerName, String volunteerPhone,
                                                 String volunteerAddress, String volunteerEmail,
                                                 int volunteerCompanyId, String volunteerPassword, String volunteerImage) {
        Map<String, Object> result = new HashMap<String, Object>();

        //身份证或者手机号已经注册

        if (isIdOrPhoneNumExist_volunteer(volunteerId,volunteerPhone)==1)
        {
            result.put("error",true);
            result.put("errorMsg","openfire phone exists");
            return result;
        }
        if (isIdOrPhoneNumExist_volunteer(volunteerId, volunteerPhone)==2)
        {
            result.put("error",true);
            result.put("errorMsg","volunteer id number exists");
            return result;
        }
        if (isIdOrPhoneNumExist_volunteer(volunteerId, volunteerPhone)==3)
        {
            result.put("error",true);
            result.put("errorMsg","volunteer phone exists");
            return result;
        }
        if (isIdOrPhoneNumExist_volunteer(volunteerId, volunteerPhone)==255)
        {
            result.put("error",true);
            result.put("errorMsg","unknow error");

            return result;
        }
        //身份证或者手机号未被注册
        Volunteer volunteer = registerService.registerForAVolunteer(volunteerId, volunteerName, volunteerPhone, volunteerAddress, volunteerEmail, volunteerCompanyId, volunteerPassword, volunteerImage);
        result.put("error", false);
        result.put("account", volunteer);
        return result;
    }


    public int isIdOrPhoneNumExist_volunteer(String id, String phoneNum) {
        //boolean flag = true;
        //ArrayList<Elder> elders = registerService.getAllElders();
        ArrayList<ofUser> users =registerService.getAllUser();
        //ArrayList<Relative> relatives = registerService.getAllRelatives();
        ArrayList<Volunteer> volunteers = registerService.getAllVolunteers();
        // ArrayList<LegalPerson> legalPersons = registerService.getAllLegalPerson();

        //Iterator<Elder> elderIterator = elders.iterator();
        Iterator<ofUser> userIterator=users.iterator();
        //Iterator<Relative> relativeIterator = relatives.iterator();
        Iterator<Volunteer> volunteerIterator = volunteers.iterator();
        //Iterator<LegalPerson> legalPersonIterator = legalPersons.iterator();

        while (volunteerIterator.hasNext())
        {
            Volunteer volunteer = volunteerIterator.next();
            if (volunteer.getId().equals(id))
            {
                return 2;
            }
            if (volunteer.getPhone().equals(phoneNum))
            {
                return 3;
            }
        }
        while (userIterator.hasNext())
        {
            ofUser user = userIterator.next();
            if (user.getUsername().equals(phoneNum))
            {
                return 1;
            }
        }


        return 0;
    }

//    /**
//     * @param lpId: legal person citizenId
//     * @param lpName: legal person name
//     * @param lpPhone: legal person phone number
//     * @param lpEmail: legal person email
//     * @param lpPassword: legal person password
//     * @param lpImage: lega person image
//     * @return
//     * Error: false, account detail
//     */
//    @RequestMapping(value = "/register/legalperson")
//    @ResponseBody
//    public Map<String, Object> legalPersonRegister(String lpId, String lpName, String lpPhone,
//                                                   String lpEmail, String lpPassword, String lpImage){
//        Map<String, Object> result = new HashMap<String, Object>();
//        //身份证或者手机号已经注册
//        if (isIdOrPhoneNumExist_legalperson(lpId,lpPhone)==2)
//        {
//            result.put("error",true);
//            result.put("errorMsg","legalperson id number exists");
//            return result;
//        }
//        if (isIdOrPhoneNumExist_legalperson(lpId,lpPhone)==3)
//        {
//            result.put("error",true);
//            result.put("errorMsg","legalperson phone exists");
//            return result;
//        }
//        if (isIdOrPhoneNumExist_legalperson(lpId,lpPhone)==255)
//        {
//            result.put("error",true);
//            result.put("errorMsg","unknow error");
//            return result;
//        }
//        //身份证或者手机号未被注册
//        LegalPerson legalPerson = registerService.registerForALegalPerson(lpId, lpName, lpPhone, lpEmail, lpPassword, lpImage);
//        result.put("error", false);
//        result.put("account", legalPerson);
//        return result;
//    }


    @RequestMapping(value = "/register/legalperson", method=RequestMethod.POST)

    public String legalPersonRegister(HttpServletRequest request,Model model){

        Map<String, Object> result = new HashMap<String, Object>();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile("lp_img");

        String lpId=request.getParameter("legalperson_id");
        String lpName=request.getParameter("legalperson_name");
        String lpPhone=request.getParameter("legalperson_tel");
        String lpEmail=request.getParameter("legalperson_email");
        String lpPassword=request.getParameter("legalperson_password");

        //身份证或者手机号已经注册

        if(lpId == ""|| lpName == "" || lpPhone == "" || lpEmail == "" || lpPassword == ""){
            result.put("error",true);
            result.put("errorMsg","Something is empty!");
            model.addAttribute("errMsg", "输入信息有误");


            return "Register";}

        if (isIdOrPhoneNumExist_legalperson(lpId,lpPhone)==1)
        {
            result.put("error",true);
            result.put("errorMsg","openfire phone exists");
            model.addAttribute("errMsg", " phone exists");
            return "Register";

        }
        if (isIdOrPhoneNumExist_legalperson(lpId, lpPhone)==2)
        {
            result.put("error",true);
            result.put("errorMsg","legalperson id number exists");
            model.addAttribute("errMsg", " phone exists");
            return "Register";
        }
        if (isIdOrPhoneNumExist_legalperson(lpId,lpPhone)==3)
        {
            result.put("error",true);
            result.put("errorMsg","legalperson phone exists");
            model.addAttribute("errMsg", " phone exists");
            return "Register";
        }

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(!multipartResolver.isMultipart(request)){
            result.put("error",true);
            result.put("errorMsg","unknow error");
            model.addAttribute("errMsg", "图片未上传成功");
            return "Register";
        }
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request

            //取得request中的所有文件名

            if (file != null) {
                File convFile = new File(file.getOriginalFilename());

                try {
                    convFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(convFile);
                    fos.write(file.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ftpService.CreateLp(lpId);
                ftpService.uploadFileForLp(convFile, lpId);

                String xxx="ftp://ocare:ocare@202.120.163.167/legalperson/"+lpId+'/'+convFile.getPath();

                System.out.println("success");
                LegalPerson legalPerson = registerService.registerForALegalPerson(lpId, lpName, lpPhone, lpEmail, lpPassword, xxx);
                result.put("error", false);
                result.put("account", legalPerson);

                return "index";
            }

            // 重定向
        }

        return "Register";

    }

    public int isIdOrPhoneNumExist_legalperson(String id, String phoneNum) {
        //boolean flag = true;
        //ArrayList<Elder> elders = registerService.getAllElders();
        //ArrayList<ofUser> users =registerService.getAllUser();
        //ArrayList<Relative> relatives = registerService.getAllRelatives();
        //ArrayList<Volunteer> volunteers = registerService.getAllVolunteers();
        ArrayList<LegalPerson> legalPersons = registerService.getAllLegalPerson();

       // Iterator<Elder> elderIterator = elders.iterator();
        //Iterator<ofUser> userIterator=users.iterator();
        //Iterator<Relative> relativeIterator = relatives.iterator();
        //Iterator<Volunteer> volunteerIterator = volunteers.iterator();
        Iterator<LegalPerson> legalPersonIterator = legalPersons.iterator();

        while (legalPersonIterator.hasNext())
        {
            LegalPerson legalPerson = legalPersonIterator.next();
            if (legalPerson.getId().equals(id))
            {
                return 2;
            }
            if (legalPerson.getPhone().equals(phoneNum))
            {
                return 3;
            }
        }

        return 0;
    }


    /**
     * @param relativeId:  relative citizenId
     * @param elderId:     elder citizenId
     * @param togetherImg: together image
     * @return Error: false,
     */
    @RequestMapping(value = "/apply/monitor")
    @ResponseBody
    public Map<String, Object> applyMonitor(String relativeId, String elderId, String togetherImg) {
        Map<String, Object> result = new HashMap<String, Object>();
        if(relativeId==null||relativeId==""||elderId==null||elderId==""||togetherImg==null||togetherImg==""){
            result.put("error", true);
            result.put("errorMsg","Null Input");
            return result;
        }
        String status=verifyService.submitMonitorApply(relativeId, elderId, togetherImg);
        if(status.equals("NoRelative")){
            result.put("error", true);
            result.put("errorMsg","No Relative");
            return result;
        }
        if(status.equals("Exists")){
            result.put("error", true);
            result.put("errorMsg","Relation Exists");
            return result;
        }
        if(status.equals("NoElder")){
            result.put("error", true);
            result.put("errorMsg","No Elder");
            return result;
        }
        result.put("error", false);
        return result;
    }

    /**
     * @param relativeId: relativeId
     * @return Error: true, ErrorMsg
     * Error:false, monitor detail
     */
    @RequestMapping(value = "/status/monitor")
    @ResponseBody
    public Map<String, Object> checkMonitor(String relativeId) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<ElderMonitor> list = verifyService.monitorStatus(relativeId);
        if (list.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "Invalid relativeId Or no related information.");
        } else {
            result.put("error", false);
            result.put("result", list);
        }
        return result;
    }

    /**
     * @param phoneNum: phone number
     * @return Error: false, code
     */
    @RequestMapping(value = "/code")
    @ResponseBody

    public Map<String, Object> createCode(String phoneNum,HttpSession httpSession){

        Map<String, Object> result = new HashMap<String, Object>();
        int code = smsService.sendVerifyCodeToPhone(phoneNum);
        //int code=1111;
        httpSession.setAttribute("sessionPN",phoneNum);
        httpSession.setAttribute("sessionCode",code);
        result.put("error", false);
        result.put("code", code);
        return result;
    }



    /**
     * @param id:       citizenId
     * @param phoneNum: phone number
     * @return Error: true, ErrorMsg
     * Error:false, account type
     */
    @RequestMapping(value = "/verify/phone")
    @ResponseBody

    public Map<String, Object> verifyPhoneNum(String id, String phoneNum,int role){
        Map<String, Object> result = new HashMap<String, Object>();
        String status = accountService.verifyPhoneNum(id, phoneNum, role);
        if(status == "Incorrect phone number") {

            result.put("error", true);
            result.put("errorMsg", "Incorrect phone number");
            return result;
        } else if (status == "Invalid Account") {
            result.put("error", true);
            result.put("errorMsg", "Invalid Account");
            return result;
        } else {
            result.put("error", false);
            result.put("type", status);
            return result;
        }
    }

    /**
     * @param id:       citizenId
     * @param password: new password
     * @return Error: true, ErrorMsg
     * Error: false
     */
    @RequestMapping(value = "/changepassword")
    @ResponseBody
    public Map<String, Object> changePassword(String id, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        String status = accountService.changePassword(id, password);
        if (status == "Invalid Account") {
            result.put("error", true);
            result.put("errorMsg", "Invalid Account");
            return result;
        } else {
            result.put("error", false);
            return result;
        }

    }

   //修改个人信息;手机号
    @RequestMapping(value = "/personInforModifyHandle1")
    @ResponseBody
    public Map<String, Object> personInforModifyHandle1(String phoneNum,int code,String sessionId,HttpSession httpSession){
        Map<String, Object> result = new HashMap<String, Object>();

        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }


        String id=(String)httpSession.getAttribute("sessionId");
        String _role= (String)httpSession.getAttribute("sessionType");
        int role=Integer.parseInt(_role);
        //首先验证要绑定的手机
        int a=(Integer) httpSession.getAttribute("sessionCode");
        //String _code=(String)httpSession.getAttribute("sessionCode");
        //int a = Integer.parseInt(_code);
        if(!(code==a)){
            result.put("error", true);
            result.put("errorMsg", "Invalid CODE");
            return result;
        }
        if(role==0||role==1||role==2||role==3||role==4)
        {
            String status=accountService.personInforModifyHandle(id, role, phoneNum,0).getKey();
            Object object=accountService.personInforModifyHandle(id, role, phoneNum,0).getValue();
            if(status == "Invalid Account"){
                result.put("error", true);
                result.put("errorMsg", "Invalid Account");
                return result;
            }else{
                result.put("error", false);
                result.put("object",object);
                return result;
            }
        }
        else
        {
            result.put("error", true);
            result.put("errorMsg", "ROLE NOT EXIST");
            return result;
        }
    }

    //修改个人信息;密码
    @RequestMapping(value = "/personInforModifyHandle2")
    @ResponseBody
    public Map<String, Object> personInforModifyHandle2(String newPassword,String password,String sessionId,HttpSession httpSession){
        Map<String, Object> result = new HashMap<String, Object>();
        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }

        String phoneNum=(String)httpSession.getAttribute("sessionId");
        String _role= (String)httpSession.getAttribute("sessionType");
        int role=Integer.parseInt(_role);
        String passwordstatus = accountService.logon(phoneNum, password,role).getKey();
        if(passwordstatus == "Invalid Account"){
            result.put("error", true);
            result.put("errorMsg", "Invalid Account");
            return result;
        }
        if (passwordstatus == "Incorrect password"){
            result.put("error", true);
            result.put("errorMsg", "Incorrect password");
            return result;
        }

        if(role==0||role==1||role==2||role==3||role==4)
        {
            String status=accountService.personInforModifyHandle(phoneNum, role, newPassword,1).getKey();
            if(status == "Invalid Account"){
                result.put("error", true);
                result.put("errorMsg", "Invalid Account");
                return result;
            }else{
                result.put("error", false);
                result.put("object",accountService.personInforModifyHandle(phoneNum, role, newPassword,1).getValue() );
                return result;
            }
        }
        else
        {
            result.put("error", true);
            result.put("errorMsg", "ROLE NOT EXIST");
            return result;
        }
    }

    //修改个人信息;头像
    @RequestMapping(value = "/personInforModifyHandle3")
    @ResponseBody
    public Map<String, Object> personInforModifyHandle3(String change,String sessionId,HttpSession httpSession){
        Map<String, Object> result = new HashMap<String, Object>();
        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }

        String id=(String)httpSession.getAttribute("sessionId");
        int role=(Integer)httpSession.getAttribute("sessionType");
        if(role==0||role==1||role==2||role==3||role==4)
        {
            String status=accountService.personInforModifyHandle(id, role, change,2).getKey();
            if(status == "Invalid Account"){
                result.put("error", true);
                result.put("errorMsg", "Invalid Account");
                return result;
            }else{
                result.put("error", false);
                result.put("object",accountService.personInforModifyHandle(id, role, change,2).getValue());
                return result;

            }
        }
        else
        {
            result.put("error", true);
            result.put("errorMsg", "ROLE NOT EXIST");
            return result;
        }
    }




    //获取验证码以后改密
    @RequestMapping(value = "/lostPasswordHandle")
    @ResponseBody
    public Map<String, Object> lostPasswordHandle2(String id, int code,int role,String password,String sessionId,HttpSession httpSession){
        Map<String, Object> result = new HashMap<String, Object>();
        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }

        String phoneNum=(String)httpSession.getAttribute("sessionPN");
        int a=(Integer) httpSession.getAttribute("sessionCode");

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
        String vStatus = accountService.verifyPhoneNum(id, phoneNum,role);
        if(vStatus == "Incorrect phone number with ID") {
            result.put("error", true);
            result.put("errorMsg", "Incorrect phone number with ID");
            return result;
        }
        if (vStatus == "Invalid Account"){
            result.put("error", true);
            result.put("errorMsg", "Invalid Account");
            return result;
        }
        if (vStatus == "NO role"){
            result.put("error", true);
            result.put("errorMsg", "NO role");
            return result;
        }

        if(!(code==a)){
            result.put("error", true);
            result.put("errorMsg", "Invalid CODE");
            return result;
        }
        if(role==1||role==2||role == 3 || role == 4 || role == 5) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
            String status=accountService.lostPasswordHandle(id, role, password, phoneNum);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
            if(status == "Invalid Account"){
                result.put("error", true);
                result.put("errorMsg", "Invalid Account");
                return result;
            }else{
                result.put("error", false);
                return result;
            }
        }
        else
        {
            result.put("error", true);
            result.put("errorMsg", "ROLE NOT EXIST");
            return result;
        }
    }



    /*
        功能：根据phoneNum拿到头像
     */
    @RequestMapping(value = "/getImageByPhoneNum")
    @ResponseBody
    public Map<String, Object> getImageByPhoneNum(String phoneNum) {
        Map<String, Object> result = new HashMap<String, Object>();
        //传参错误
        if (phoneNum == null || phoneNum == "null") {
            result.put("error", true);
            result.put("errorMsg", "Phone Number is valid!");
        } else {
            String status = accountService.getImageByPhoneNum(phoneNum).getKey();
            if (status == "get image successfully.") {
                result.put("error", false);
                result.put("image", accountService.getImageByPhoneNum(phoneNum).getValue());
            } else if (status == "PhoneNum does not match to any account!") {
                result.put("error", true);
                result.put("errorMsg", "PhoneNum does not match to any account!");
            }
        }
        return result;
    }

    /*
        功能：根据phoneNum修改头像为newImage
     */
    @RequestMapping("/setNewImageByPhoneNum")
    @ResponseBody
    public Map<String, Object> setNewImageByPhoneNum(String phoneNum, String newImage) {
        Map<String, Object> result = new HashMap<String, Object>();
        //传参数错误
        if (phoneNum == null || phoneNum == "" || newImage == null || newImage == "") {
            result.put("error", true);
            result.put("errorMsg", "Phone Number is valid Or new Image is valid!");
        } else {
            boolean flag = accountService.isChangeImageSucc(phoneNum, newImage);
            if (flag == true) {
                result.put("error", false);
            } else {
                result.put("error", true);
                result.put("errorMsg", "Change Image not Successfully!");
            }
        }
        return result;
    }

    /*
        功能：修改某个人和老人的关系为新的type
     */
    @RequestMapping("/changeRelation")
    @ResponseBody
    public Map<String, Object> changeRelationBetweenElderAndSomebody(String elderId, String sbPhoneNum, int newRelationType) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (sbPhoneNum == null || sbPhoneNum == "") {
            result.put("error", true);
            result.put("errorMsg", "Phone Number is valid Or new Image is valid!");
        } else {
            boolean flag = verifyService.changeRelationBetweenElderAndSomebody(elderId, sbPhoneNum, newRelationType);
            if (flag == false) {
                result.put("error", true);
                result.put("errorMsg", "Change Relation Failed.");
            } else {
                result.put("error", false);
            }

        }
        return result;
    }

    /*
        功能：监护人负责删除某个人和老人的关系（设type为7）
     */
    @RequestMapping("/deleteRelation")
    @ResponseBody
    public Map<String, Object> deleteRelationBetweenElderAndSomebody(String elderId, String sbPhoneNum) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (sbPhoneNum == null || sbPhoneNum == "") {
            result.put("error", true);
            result.put("errorMsg", "Delete Relation Failed.");
        } else {
            boolean flag = verifyService.deleteRelationBetweenElderAndSomebody(elderId, sbPhoneNum);
            if (flag == false) {
                result.put("error", true);
                result.put("errorMsg", "Delete Relation Failed.");
            } else {
                result.put("error", false);
            }
        }
        return result;
    }

    /*
        功能：通过身份证拿到某个老人的全部信息，即实体集
     */
    @RequestMapping("/getElderInfo")
    @ResponseBody
    public Map<String, Object> getElderInfoById(String elderId) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (elderId == null || elderId == "")
        {
            result.put("error",true);
            result.put("errorMsg","Elder Id Input Error!");

        } else {
            Elder elder = elderService.getElderById(elderId);
            if (elder == null) {
                result.put("error", true);
                result.put("errorMsg", "Get Entity Error!");
            } else {
                result.put("error", false);
                result.put("elderEntity", elder);
            }
        }
        return result;
    }

    /*
        功能：通过身份证拿到某个relative的全部信息，即实体集
     */
    @RequestMapping("/getRelativeInfo")
    @ResponseBody
    public Map<String, Object> getRelativeInfoById(String relativeId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (relativeId == null || relativeId == "") {
            result.put("error", true);
            result.put("errorMsg", "Relative Id Input Error!");
        } else {
            Relative relative = relativeService.getRelativeById(relativeId);
            if (relative == null) {
                result.put("error", true);
                result.put("errorMsg", "Get Entity Error!");
            } else {
                result.put("error", false);
                result.put("relativeEntity", relative);
            }
        }
        return result;
    }

    /*
        功能：通过老人Id拿到所有的监护人的list
     */
    @RequestMapping("/getMonitorsByElderId")
    @ResponseBody
    public Map<String, Object> getMonitorsByElderId(String elderId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (elderId == null || elderId == "") {
            result.put("error", true);
            result.put("errorMsg", "Elder Id Input Error!");
        } else {
            ArrayList<Relative> relatives = elderService.getAllMonitorsByElderId(elderId);
            if (relatives == null) {
                result.put("error", true);
                result.put("errorMsg", "Get List Error!");
            } else {
                result.put("error", false);
                result.put("monitorList", relatives);
            }
        }
        return result;
    }

    //超级管理员拿到所有老人数据
    @RequestMapping("/getAllElders")
    @ResponseBody
    public Map<String, Object> getAllElders() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<Elder> allElders = elderService.getAllElders();
        if (allElders.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "没有老人的数据");
        } else {
            result.put("error", false);
            result.put("result", allElders);
        }
        return result;
    }

   //某个区域负责人拿到所有老人数据
    @RequestMapping("/getEldersLocated")
    @ResponseBody
    public Map<String, Object> getAllEldersLocated(int companyId)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<Elder> allElders = elderService.getAllEldersLocated(companyId);
        if (allElders.size() == 0)
        {
            result.put("error",true);
            result.put("errorMsg","没有老人的数据");
        }else {
            result.put("error",false);
            result.put("result",allElders);
        }
        return result;
    }


    //某人查找他监护或帮助的所有老人数据
    @RequestMapping("/getEldersByRelativePhone")
    @ResponseBody
    public Map<String, Object> getEldersByRelativePhone(String phoneNum)
    {
        System.out.println("2222222222222");
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<Elder> allElders=new ArrayList<Elder>();

        //首先（该人监护的老人)，ElderMoniter表
        System.out.println("aaaaaaaazzaaaaaaaaaaaaa");
        Relative relative=relativeService.getRelativeByPhoneNum(phoneNum);
        System.out.println("aaaqqaaaaazzaaaaaaaaaaaaa");
        if(relative==null){
            result.put("error",false);
            result.put("result","Relative NOT EXIST" );
        return result;
        }

        List<ElderMonitor> elderList1=elderService.findAllElderMonitorsByRelativeId(relative.getId());
        if(elderList1!=null) {
            for (int i = 0; i < elderList1.size(); i++) {
                Elder elderA=elderService.getElderById(elderList1.get(i).getElder_id());
                if(elderA!=null) {
                    elderA.setPassword("");
                    allElders.add(elderA);
                }
            }
        }
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");


        //然后(帮助)，根据该人电话找到他所在的所有房间，如果这个房间是养老间则取出老人信息
         List<ofMucMember> mucMembers=openfireService.getRoomIdbyPhone(phoneNum);
        if(mucMembers==null)
        {
            System.out.println("aaaaaaaaaaaaaaaaaaaabb");
            if (allElders.size() == 0)
            {
                result.put("error",true);
                result.put("errorMsg","没有老人的数据");
            }else {
                result.put("error",false);
                result.put("result",allElders);
            }
            return result;
        }
        else{
            System.out.println("aaaaaaaaaaavvaaaaaaaaa");
                for(int i=0;i<mucMembers.size();i++)
                {
                    System.out.println("aaaaaxxxaaaaaaaaaaaaaa");
                    List<ofMucRoom> mucRooms=openfireService.getRoomByid(mucMembers.get(i).getRoomId());
                    for(int j=0;j<mucRooms.size();j++){
                        System.out.println("aaaaaaaaxxaaaaaaaaaaaa");
                        if(isOcareRoom(mucRooms.get(j))){
                            String name=mucRooms.get(j).getName();
                            System.out.println("aassaaaaaaaaaaaaaaaaaa");
                            for(int k=0;k<name.length();k++){
                                if(name.charAt(k)=='(')
                                {
                                    System.out.println("aaaxxzaaaaaaaaaaaaaaaaa");
                                    name=name.substring(0,k-1);
                                    break;
                                }
                            }
                            Elder elderB=elderService.getElderByPhoneNum(name);
                            allElders.add(elderB);
                        }
                    }
                }
            result.put("error",false);
            result.put("result",allElders);
            return result;
        }
    }

    //判断某房间是否是养老间
    boolean isOcareRoom(ofMucRoom mucRoom){
        //name字段有监护人号码
        String name=mucRoom.getName();
        boolean flag=name.contains("(");
        if(flag==true)  return flag;

        //查看isOCareRoom字段，为1是
        if(mucRoom.getIsOCareRoom()==1)
            return true;

        return  false;
    }



    /*
        功能：注册公司机构
        注意：注册机构分为两个步骤，先有法人代表才能注册公司
     */

    @RequestMapping(value = "/company/register", method = RequestMethod.POST)

    public String companyRegister(HttpServletRequest request ,HttpSession httpSession,Model model)
    {

        String phone= (String) httpSession.getAttribute("sessionId");
        LegalPerson legalPerson=legalPersonDAO.queryByPhoneNum(phone);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        String agent_name=request.getParameter("agent_name");
        String agent_code=request.getParameter("agent_code");
        String legalperson_id=legalPerson.getId();
        String company_phone=request.getParameter("company_phone");
        String company_address=request.getParameter("company_address");
        String[] url=new String[2];

        Map<String, Object> result = new HashMap<String, Object>();
        if (agent_name == "" || agent_code == "" ||  company_phone == null || company_address==null)
        {
            result.put("error",true);
            result.put("errorMsg","Something is empty!");
            model.addAttribute("errMsg","输入信息有误");

            return "applyComany";
        }else if(legalperson_id == ""||legalperson_id==null){
            model.addAttribute("errMsg","请先登录");

            return "applyComany";
        }
        else if(!registerService.isLegalPersonIdExist(legalperson_id)) {
            result.put("error",true);
            result.put("errorMsg","Legal Person not exsits");
            model.addAttribute("errMsg","请先注册");

            return "applyComany";
        }else {

            if (multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                int i=0;
                while (iter.hasNext()) {

                    //记录上传过程起始时的时间，用来计算上传时间
                    int pre = (int) System.currentTimeMillis();
                    //取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        File convFile = new File(file.getOriginalFilename());

                        try {
                            convFile.createNewFile();
                            FileOutputStream fos = new FileOutputStream(convFile);
                            fos.write(file.getBytes());
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ftpService.CreateCompany(agent_code);
                        ftpService.uploadFileForCompany(convFile, agent_code);
                        url[i]=convFile.getPath();
                        i++;
                    }
                }
            }

            Company companyToRegister =  registerService.registerForCompany(agent_name, legalperson_id, company_phone, company_address,"ftp://ocare:ocare@202.120.163.167/company/"+agent_code+"/"+url[0],"ftp://ocare:ocare@202.120.163.167/company/"+agent_code+"/"+url[1]);
            result.put("error",false);
            result.put("account",companyToRegister);
            return "WaitPermition";

        }

    }

    /*
        功能：通过身份证号删除员工
        参数：身份证
     */
    @RequestMapping("/employee/delete")
    @ResponseBody
    public Map<String, Object> deleteEmployeeById(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (id == null || id == "") {
            result.put("error", true);
            result.put("errorMsg", "Input phoneNum is null");
        } else {
            String flag = employeeService.deleteEmployeeById(id);
            if (flag == "success")
                result.put("error", "注销成功");
            else {
                result.put("error", true);
                result.put("errorMsg", "Can not find such employee!");
            }
        }
        return result;
    }

    /*
        功能：更新员工信息
     */
    @RequestMapping("/employee/updateById")
    @ResponseBody
    public Map<String, Object> updateEmployeeInfoById(String id, String newName, String newPhone, String newAddress,
                                                      String newDepartment, String newPosition, int newStatus,
                                                      String newPassword, String newImage, String newSuperiot,
                                                      String newWorkExperience, String newWorkDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        boolean flag = employeeService.changeEmployeeInfoById(id, newName, newPhone, newAddress, newDepartment, newPosition,
                newStatus, newPassword, newImage, newSuperiot, newWorkExperience, newWorkDetail);
        if (flag == false) {
            result.put("error", true);
            result.put("errorMsg", "failed to update the employee's info!");
        } else {
            result.put("error", false);
        }
        return result;
    }

    /*
        功能：显示老人有效合同的状况
        返回：Contract实例
     */
    @RequestMapping("/contract/showContractInServiceByElderId")
    @ResponseBody
    public Map<String, Object> showContractInServiceByElderId(String elderId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (elderId == null || elderId == "") {
            result.put("error", true);
            result.put("errorMsg", "input elder id is invalid");
        } else {
            result.put("error", false);
            result.put("contract", contractService.getElderContractInfoByElderId(elderId));
            result.put("contractStatus", contractService.getElderContractInfoByElderId(elderId).getStatus());
        }

        return result;
    }

    /*------------------2015.10.12------------------*/

    /*
        功能：通过老人身份证拿到老人最近一次的位置信息
        返回：经纬度坐标数组
     */
    @RequestMapping("/map/getElderPresentLocationById")
    @ResponseBody
    public Map<String, Object> getElderPresentLocationById(String elderId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (elderId == null || elderId == "") {
            result.put("error", true);
            result.put("errorMsg", "input elder id is invalid");
        } else {
            ElderCondition presentElderCondition = elderConditionService.getElderPresentConditionById(elderId);
            if (presentElderCondition == null) {
                result.put("error", true);
                result.put("errorMsg", "No related info about this elder!");
                return result;
            }
            double latitude = presentElderCondition.getLatitude(), longtitude = presentElderCondition.getLongtitude();
            result.put("error", false);
            result.put("latitude", latitude);
            result.put("longtitude", longtitude);
        }

        return result;
    }

    /*
        功能：返回数据库所有老人和对应的所有监护人的集合
        返回值：
     */

    @RequestMapping("/getAllElderRelatedInfo")
    @ResponseBody
    public Map<String, Object> getAllElderRelatedInfo() {
        Map<String, Object> result = new HashMap<String, Object>();
        //get all elder entites
        ArrayList<Elder> elders = elderService.getAllElders();

        //the list of result information together
        ArrayList<HashMap<String, Object>> elderInfo = new ArrayList<HashMap<String, Object>>();

        if (elders.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "There is no elders in database");
            return result;
        }

        for (Elder elder : elders) {
            //the current elder information
            HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
            //elder info
            tempElderInfo.put("elder", elder);
            //relative info
            tempElderInfo.put("relatives", verifyService.getMonitorsByElderId(elder.getId()));
            //add to list
            elderInfo.add(tempElderInfo);
        }

        result.put("error", false);
        result.put("info", elderInfo);

        return result;
    }

    /*
        功能：返回所有老人的当前（最后一次）位置信息
        返回值
     */
    @RequestMapping("map/allEldersPresentLocationInfo")
    @ResponseBody
    public Map<String, Object> allEldersPresentLocationInfo() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", false);
        ArrayList<ElderCondition> allEldersPresentLocationInfo = elderConditionService.getAllEldersPresentCondition();
        result.put("result", allEldersPresentLocationInfo);
        result.put("resultNum", allEldersPresentLocationInfo.size());
        return result;
    }

    /*
        功能：根据手机号找老人（所有老人，可能多个）mark
        返回值
     */

    @RequestMapping("/elder/phone/{phoneNum}")
    @ResponseBody
    public Map<String, Object> getEldersByPhoneNum(@PathVariable String phoneNum) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (phoneNum == null || phoneNum.equals("")) {
            result.put("error", true);
            result.put("errorMsg", "INPUT_CANNOT_NULL");
            return result;
        }

        List<Elder> elders = elderService.getEldersByPhoneNum(phoneNum);

        if (elders == null || elders.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "ELDER_NOT_EXIST");
            return result;
        }

        result.put("error", false);
        result.put("elder", elders);
        return result;
    }

    @RequestMapping("/getElderInfoByPhone/{relativePhoneNum}/{elderPhoneNum}")
    @ResponseBody
    public Map<String, Object> findElderWithRelativePhoneNumAndElderPhoneNum(@PathVariable String relativePhoneNum, @PathVariable String elderPhoneNum) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (relativePhoneNum == null || elderPhoneNum == null || relativePhoneNum.equals("") || elderPhoneNum.equals("")) {
            System.out.println(relativePhoneNum + " " + elderPhoneNum);
            result.put("error", true);
            result.put("errorMsg", "INPUT_CANNOT_NULL");
            return result;
        }

        Relative relative = relativeService.getRelativeByPhoneNum(relativePhoneNum);
        //没有SQL注入直接利用其他接口返回值获取
        List<Elder> elders = elderService.getEldersByPhoneNum(elderPhoneNum);

        if (relative == null) {
            result.put("error", true);
            result.put("errorMsg", "RELATIVE_NOT_EXIST");
            return result;
        }

        if (elders == null || elders.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "ELDER_NOT_EXIST");
            return result;
        }

        Elder elder = elders.get(0);

        String relativeId = relative.getId();
        ArrayList<Elder> listElders = verifyService.getAllEldersByMonitorId(relativeId);

        int iFlag = 0;

        for (Elder tempElder : listElders) {
            if (tempElder.getId() == elder.getId()) {
                iFlag = 1;
                break;
            }
        }

        if (iFlag == 0) {
            result.put("error", true);
            result.put("errorMsg", "RELATIVE_AND_ELDER_NOT_MATCH");
            return result;
        }

        result.put("error", false);
        result.put("elder", elder);
        return result;
    }

    /*
        功能：列出所有老人的合同情况和监护人
        参数：无
        返回：每个老人的合同和所有监护人
     */
    @RequestMapping("/contract/listAllEldersContractAndMonitors")
    @ResponseBody
    public Map<String, Object> listAllEldersContractAndMonitors() {
        Map<String, Object> result = new HashMap<String, Object>();


        //get all elder contract from contract entity
        ArrayList<Contract> contracts = (ArrayList<Contract>) contractService.getAllContracts();

        //the list of result information together
        ArrayList<HashMap<String, Object>> contractInfo = new ArrayList<HashMap<String, Object>>();

        if (contracts.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "There is no contract available");
            return result;
        }

        for (Contract contract : contracts) {
            //the current elder information
            HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
            //elder info
            tempElderInfo.put("elderContract", contract);
            //relative info
            tempElderInfo.put("monitors", verifyService.getMonitorsByElderId(contract.getElder_id()));
            //elder name
            tempElderInfo.put("elderName", elderService.getElderById(contract.getElder_id()).getName());
            //add to list
            contractInfo.add(tempElderInfo);
        }

        result.put("error", false);
        result.put("info", contractInfo);

        return result;
    }

    /*
        功能：同上
        注意：返回给于子涵
     */
    @RequestMapping("/contract/listAllEldersContractAndMonitorsYu")
    @ResponseBody
    public Map<String,Object> listAllEldersContractAndMonitorsYu() {
        ArrayList<HashMap<String, Object>> relatives = new ArrayList<HashMap<String, Object>>();
        //get all elder contract from contract entity
        ArrayList<Contract> contracts = (ArrayList<Contract>) contractService.getAllContracts();

        //the list of result information together
        ArrayList<HashMap<String, Object>> contractInfo = new ArrayList<HashMap<String, Object>>();


        for (Contract contract : contracts) {
            //the current elder information
            HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();


            Elder tempElder = elderService.getElderById(contract.getElder_id());
//<<<<<<< HEAD
//            tempElderInfo.put("contract_id", contract.getId());
//            tempElderInfo.put("old_name", tempElder.getName());
//            tempElderInfo.put("old_id", contract.getElder_id());
//            tempElderInfo.put("execution", contract.getStatus());
//            tempElderInfo.put("date", contract.getStart_time());
//            tempElderInfo.put("service", "special service");
//            tempElderInfo.put("payment", "payed");
//
//            ArrayList<Relative> tempMonitos = verifyService.getMonitorsByElderId(contract.getElder_id());
//
//            for (int i = 0; i < 2; i++) {
//                if (tempMonitos.size() != 0 && tempMonitos.size() > i) {
//                    String tempStr1 = "keeper" + Integer.toString(i + 1) + "_name";
//                    tempElderInfo.put(tempStr1, tempMonitos.get(i).getName());
//                    String tempStr2 = "keeper" + Integer.toString(i + 1) + "_id";
//                    tempElderInfo.put(tempStr2, verifyService.getMonitorsByElderId(contract.getElder_id()).get(i).getId());
//=======

            tempElderInfo.put("contract_id",contract.getId());
            tempElderInfo.put("old_name",tempElder.getName());
            tempElderInfo.put("old_id",contract.getElder_id());
            tempElderInfo.put("execution",contract.getStatus());
            tempElderInfo.put("date",contract.getStart_time());
            tempElderInfo.put("service","special service");
            tempElderInfo.put("payment","payed");

            ArrayList<Relative> tempMonitos = verifyService.getMonitorsByElderId(contract.getElder_id());



            for (int i=0; i<2; i++){
                if (tempMonitos.size()!=0 && tempMonitos.size() > i){
                    String tempStr1 = "keeper" + Integer.toString(i+1) +"_name";
                    tempElderInfo.put(tempStr1,tempMonitos.get(i).getName());
                    String tempStr2 = "keeper" + Integer.toString(i+1) +"_id";
                    tempElderInfo.put(tempStr2,verifyService.getMonitorsByElderId(contract.getElder_id()).get(i).getId());
                }
            }
            //add to list
            System.out.println(tempElderInfo.toString());
            contractInfo.add(tempElderInfo);
        }
        System.out.println(contractInfo.toString());
        relatives = contractInfo;

        Map<String,Object> result = new HashMap<String, Object>();
        result.put("total",relatives.size());
        result.put("rows",relatives);
        return result;
    }

    /*
        功能：列出某个老人的合同情况和监护人
        参数：身份证号码
        返回：这个老人的合同和所有监护人
     */
    @RequestMapping("/contract/listOneElderContractAndMonitors")
    @ResponseBody
    public Map<String, Object> listOneElderContractAndMonitors(String elderId) {
        Map<String, Object> result = new HashMap<String, Object>();


        //get the elder contract from contract entity
        ArrayList<Contract> contracts = (ArrayList<Contract>) contractService.getContractsByElderId(elderId);

        //the list of result information together
        ArrayList<HashMap<String, Object>> contractInfo = new ArrayList<HashMap<String, Object>>();

        if (contracts.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "There is no contract available");
            return result;
        }

        for (Contract contract : contracts) {
            //the current elder information
            HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
            //elder info
            tempElderInfo.put("elderContract", contract);
            //relative info
            tempElderInfo.put("monitors", verifyService.getMonitorsByElderId(contract.getElder_id()));
            //elder name
            tempElderInfo.put("elderName", elderService.getElderById(contract.getElder_id()).getName());
            //add to list
            contractInfo.add(tempElderInfo);
        }

        result.put("error", false);
        result.put("info", contractInfo);

        return result;
    }

    /*
       功能：列出某个老人的合同情况和监护人
       参数：身份证号码
       返回：这个老人的合同和所有监护人
    */
    @RequestMapping("contract/listOneContractAndMonitorsByContractId")
    @ResponseBody
    public Map<String, Object> listOneContractAndMonitorsByContractId(int contractId) {
        Map<String, Object> result = new HashMap<String, Object>();


        //get the elder contract from contract entity
        Contract contract = contractService.getContractsById(contractId);
        //the list of result information together
        ArrayList<HashMap<String, Object>> contractInfo = new ArrayList<HashMap<String, Object>>();

        if (contract == null) {
            result.put("error", true);
            result.put("errorMsg", "There is no contract available");
            return result;
        }

        //the current elder information
        HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
        //elder info
        tempElderInfo.put("elderContract", contract);
        //relative info
        tempElderInfo.put("monitors", verifyService.getMonitorsByElderId(contract.getElder_id()));
        //elder name
        tempElderInfo.put("elderName", elderService.getElderById(contract.getElder_id()).getName());
        //add to list
        contractInfo.add(tempElderInfo);

        result.put("error", false);
        result.put("info", contractInfo);

        return result;
    }

    /*
       功能：列出某个老人的合同情况和监护人
       参数：身份证号码
       返回：这个老人的合同和所有监护人
    */
    @RequestMapping("contract/listOneContractAndMonitorsByMonitorId")
    @ResponseBody
    public Map<String, Object> listOneContractAndMonitorsByMonitorId(String monitorId) {
        Map<String, Object> result = new HashMap<String, Object>();

        //get the elder by monitor Id
        ArrayList<Elder> elders = verifyService.getAllEldersByMonitorId(monitorId);


        ArrayList<HashMap<String, Object>> allContractInfo = new ArrayList<HashMap<String, Object>>();

        if (elders.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "There is no related elder of this monitor available");
            return result;
        }
        for (Elder elder : elders) {
            //get the elder contract from contract entity
            ArrayList<Contract> contracts = (ArrayList<Contract>) contractService.getContractsByElderId(elder.getId());

            if (contracts.size() != 0) {
                for (Contract contract : contracts) {
                    //the current elder information
                    HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
                    //elder info
                    tempElderInfo.put("elderContract", contract);
                    //relative info
                    tempElderInfo.put("monitors", verifyService.getMonitorsByElderId(contract.getElder_id()));
                    //elder name
                    tempElderInfo.put("elderName", elderService.getElderById(contract.getElder_id()).getName());
                    //add to list
                    allContractInfo.add(tempElderInfo);
                }
            }
        }
        if (allContractInfo.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "There is no contract available");
        } else {
            result.put("error", false);
            result.put("info", allContractInfo);
        }
        return result;
    }

    /*
       功能：根据老人姓名列出所有符合条件的老人的合同信息和监护人
       参数：老人姓名
       返回：这个老人的合同和所有监护人
    */
    @RequestMapping("contract/listOneContractAndMonitorsByElderName")
    @ResponseBody
    public Map<String, Object> listOneContractAndMonitorsByElderName(String elderName) {
        Map<String, Object> result = new HashMap<String, Object>();

        //the list of result information together
        ArrayList<HashMap<String, Object>> contractInfo = new ArrayList<HashMap<String, Object>>();

        //get the elders with the given name
        ArrayList<Elder> elders = elderService.getAllEldersByElderName(elderName);

        for (Elder elder : elders) {
            //get the elder contract from contract entity
            ArrayList<Contract> contracts = (ArrayList<Contract>) contractService.getContractsByElderId(elder.getId());
            if (contracts.size() != 0) {
                for (Contract contract : contracts) {
                    //the current elder information
                    HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
                    //elder info
                    tempElderInfo.put("elderContract", contract);
                    //relative info
                    tempElderInfo.put("monitors", verifyService.getMonitorsByElderId(contract.getElder_id()));
                    //elder name
                    tempElderInfo.put("elderName", elderService.getElderById(contract.getElder_id()).getName());
                    //add to list
                    contractInfo.add(tempElderInfo);
                }
            }
        }
        if (contractInfo.size() == 0) {
            result.put("error", false);
            result.put("errorMsg", "There is no related elder contract of this elder name");
        } else {
            result.put("error", false);
            result.put("info", contractInfo);
        }
        return result;
    }

    /*
       功能：根据老人姓名列出所有符合条件的老人的合同信息和监护人
       参数：老人姓名
       返回：这个老人的合同和所有监护人
    */
    @RequestMapping("contract/listOneContractAndMonitorsByMonitorName")
    @ResponseBody
    public Map<String, Object> listOneContractAndMonitorsByMonitorName(String monitorName) {
        Map<String, Object> result = new HashMap<String, Object>();

        //the list of result information together
        ArrayList<HashMap<String, Object>> contractInfo = new ArrayList<HashMap<String, Object>>();

        //get the monitors with the given name
        ArrayList<Relative> monitors = verifyService.getAllMonitorsByName(monitorName);

        if (monitors == null) {
            result.put("error", true);
            result.put("errorMsg", "No related info about this monitor name");
            return result;
        }
        //get the elders of the selected monitors
        ArrayList<Elder> elders = new ArrayList<Elder>();

        for (Relative relative : monitors) {
            ArrayList<Elder> tempElders = verifyService.getAllEldersByMonitorId(relative.getId());
            for (int i = 0; i < tempElders.size(); i++) {
                elders.add(tempElders.get(i));
            }
        }

        if (elders.size() == 0) {
            result.put("error", true);
            result.put("errorMsg", "No related info about this monitor name");
            return result;
        }

        for (Elder elder : elders) {
            System.out.println(elder.getName());
            //get the elder contract from contract entity
            ArrayList<Contract> contracts = (ArrayList<Contract>) contractService.getContractsByElderId(elder.getId());
            if (contracts.size() != 0) {
                for (Contract contract : contracts) {
                    //the current elder information
                    HashMap<String, Object> tempElderInfo = new HashMap<String, Object>();
                    //elder info
                    tempElderInfo.put("elderContract", contract);
                    //relative info
                    tempElderInfo.put("monitors", verifyService.getMonitorsByElderId(contract.getElder_id()));
                    //elder name
                    tempElderInfo.put("elderName", elderService.getElderById(contract.getElder_id()).getName());
                    //add to list
                    contractInfo.add(tempElderInfo);
                }
            }
        }
        if (contractInfo.size() == 0) {
            result.put("error", false);
            result.put("errorMsg", "No related info about this monitor name");

        } else {
            result.put("error", false);
            result.put("info", contractInfo);
        }
        return result;
    }

    @RequestMapping("contract/upload")

    public String fileUpload(HttpServletRequest request, MultipartFile file) {
        // 判断文件是否为空

        System.out.println("进来了！1");
        if (file != null) {
            File convFile = new File(file.getOriginalFilename());
            try {
                convFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(file.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            ftpService.uploadFile(convFile);
            try {
                FileInputStream fis=new FileInputStream(convFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("success");


        }

        // 重定向
        return "import";
    }

    @RequestMapping("hr/upload")

    public String fileUpload(HttpServletRequest request, String employeeid) {
        // 判断文件是否为空


        System.out.println(employeeid);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
           int i=0;
            while (iter.hasNext()) {


                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    File convFile = new File(file.getOriginalFilename());

                    try {
                        convFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(convFile);
                        fos.write(file.getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ftpService.CreateDir(employeeid);
                    ftpService.uploadFileById(convFile, employeeid);


                    System.out.println("success");


                }

                // 重定向

            }


        }return "HR";
    }


    //根据法人的status和id获取他的所有company
    @RequestMapping(value = "/getCompanyByLegalPersonId")
    @ResponseBody
    public Map<String, Object> getCompanyByLegalPersonId(String sessionId,HttpSession httpSession) {
        Map<String, Object> result = new HashMap<String, Object>();

        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }

        String _status=(String)httpSession.getAttribute("sessionType");
        int status=Integer.parseInt(_status);
        String phoneNum=(String)httpSession.getAttribute("sessionId");
        String id=companyService.getLegaiPersonByPhone(phoneNum).getId();
        if ( id == null  || id == "") {
            result.put("error", true);
            result.put("errorMsg", "sorry! you should logon first!");
            return result;
        }else if (status != 2) {
            result.put("error", true);
            result.put("errorMsg", "sorry! you are not a legalperson,please login again!");
        } else {
            List<Company> companyList = companyService.getByLegalPerson(id);
            if(companyList.isEmpty()){
                result.put("error", true);
                result.put("errorMsg", "you got no company applied yet");
            }
            else {
                result.put("error",false);
                result.put("errorMsg", "get list successfully");
                result.put("companyList", companyList);
            }
        }
        return result;
    }


//    @RequestMapping("contract/upload")
//    @ResponseBody
//    public String fileUpload(HttpServletRequest request, MultipartFile file) {
//        // 判断文件是否为空
//
//        if(file!=null)
//        {
//            File convFile = new File(file.getOriginalFilename());
//            try {
//                convFile.createNewFile();
//                FileOutputStream fos = new FileOutputStream(convFile);
//                fos.write(file.getBytes());
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            ftpService.uploadFile(convFile);
//
//
//
//            System.out.println("success");
//
//
//        }
//
//        // 重定向
//        return "success";
//    }


    @RequestMapping("contract/download")
    public void fileDownload(HttpServletRequest request,HttpServletResponse response,String Cid) {
        // 判断文件是否为空


        File file = ftpService.getFileById(Cid);

        String filename = file.getName();// 获取日志文件名称
        InputStream fis = null;
        byte[] buffer = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.reset();
        // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-Length", "" + file.length());
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);// 输出文件
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream");

    }

    /*------------------2015.12.1 by Tommy------------------*/
    @RequestMapping("elderById")
    @ResponseBody
    public Elder getElderById(String elderId) {

        Elder result = (Elder)elderService.getElderById(elderId);

        return result;


    }



}
