package com.OCare.controller;

import com.OCare.dao.IGeneralDAO;
import com.OCare.dao.LegalPersonDAO;
import com.OCare.entity.Company;
import com.OCare.entity.LegalPerson;
import com.OCare.entity.MySessionContext;
import com.OCare.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by mark on 8/2/15.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private LegalPersonDAO legalPersonDAO;
    @Autowired
    private IGeneralDAO <LegalPerson> iGeneralDAO;

    /**
     * 功能：创建一个机构
     * @param name 公司名称
     * @param legalPerson 法人的身份证号码
     * @param phone 公司的电话
     * @param address 公司的地址
     * @return null
     */
    @RequestMapping(value="/create")
    @ResponseBody
    public String createCompany(String name, String legalPerson, String phone, String address){
        companyService.createCompany(name, legalPerson, phone, address);
        return "company";
    }

    //
    @RequestMapping(value="/sessionCompanyId")
    @ResponseBody
    public void sessionCompany(int id,HttpSession httpSession){
        httpSession.setAttribute("sessionCompanyId", id);
    }

    /**
     * 功能：能过名字查找公司
     * @param name：公司的名称（不支持模糊查找）
     * @param model：返回值集合
     * @return null
     */

    @RequestMapping(value="/name/{name}", method = RequestMethod.GET)
    public String getCompanyByName(@PathVariable String name, Model model){
        Company company=companyService.getByName(name);
        String lp=company.getLegal_person_id();
        LegalPerson legalPerson=iGeneralDAO.queryById(lp);
        model.addAttribute("company", companyService.getByName(name));
        model.addAttribute("lpImg",legalPerson.getImage());
        return "agentVerify";
    }

    //根据公司id查看status
    @RequestMapping(value="getStatusById")
    public int getStatusById(int id){
        Company company=companyService.getCompanyById(id);
        int status=company.getStatus();
        return status;
    }


    /**
     * 功能：通过法人的身份证号码查找公司
     * @param legalPerson 法人身份证号码
     * @param model 返回值集合
     * @return null
     */
    @RequestMapping("/legalperson")
    public String getCompanyByLegalPerson(String legalPerson, Model model){
        model.addAttribute("companies", companyService.getByLegalPerson(legalPerson));
        return "homepage";
    }


    /**
     * 功能：列出所有正在申请中的公司
     * @param model 返回值集合
     * @return null
     */
    @RequestMapping("/list")
    public String listUnapproveCompanies(Model model){
        model.addAttribute("list", companyService.unapproveCompanies());
        return "agentApplyList";
    }



    /**
     * 功能：列出所有的公司
     */
    @RequestMapping("/companyList")
    public String listAllCompanies(Model model,String sessionId,HttpSession httpSession){
        //Map<String, Object> result = new HashMap<String, Object>();
        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }

        String status=(String)httpSession.getAttribute("sessionType");
        if(status.equals("Admin")) {
            List<Company> companyList=companyService.getAllCompany();
            model.addAttribute("list",companyList);
            return "AllCompany";
        }else{
            return "index";
        }

    }


    @RequestMapping("/companyListA")
    @ResponseBody
    public Map<String, Object> listAllCompaniesA(String sessionId,HttpSession httpSession){
        Map<String, Object> result = new HashMap<String, Object>();

        if(!(sessionId==""||sessionId==null)) {
            MySessionContext myc = MySessionContext.getInstance();
            httpSession = myc.getSession(sessionId);
        }
        String status;
        status=(String)httpSession.getAttribute("sessionType");

       //String status="admin";
        if(status==null||status==""){
            result.put("error", true);
            result.put("errorMsg", "NO authority");
            return result;
        }
        if(status.equals("Admin")) {
            List<Company> companyList=companyService.getAllCompany();
            result.put("error", false);
            result.put("companyList", companyList);
            return result;
        }
        result.put("error", true);
        result.put("errorMsg", "NO authority");
        return result;
    }

    /**
     * 功能：同意申请
     * @param id 公司的编号
     * @return Null
     */
    @RequestMapping("/agreeCompany")
    @ResponseBody
    public  Map<String, Object> agreeApply(String id){
        Map<String, Object> result = new HashMap<String, Object>();

        Company company=companyService.getCompanyById(Integer.parseInt(id));
        if(company==null){
            result.put("error", true);
            result.put("errorMsg", "Company Not Exist");
            return result;
        }
        companyService.changeStatus(Integer.parseInt(id), 102);
        result.put("error", false);
        result.put("changeMsg", "agree successfully");
        return result;
    }

    /**
     * 功能：拒绝申请
     * @param id 公司的编号
     * @return Null
     */
    @RequestMapping("/rejectCompany")
    @ResponseBody
    public Map<String, Object> rejectApply(String id){
        Map<String, Object> result = new HashMap<String, Object>();

        Company company=companyService.getCompanyById(Integer.parseInt(id));
        if(company==null){
            result.put("error", true);
            result.put("errorMsg", "Company Not Exist");
            return result;
        }
        companyService.changeStatus(Integer.parseInt(id), 103);
        result.put("error", false);
        result.put("changeMsg", "reject successfully");
        return result;
    }


}
