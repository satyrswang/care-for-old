package com.OCare.controller;


import com.OCare.dao.CompanyDAO;
import com.OCare.entity.Employee;
import com.OCare.service.EmployeeService;
import com.OCare.service.FtpService;
import com.OCare.service.RelativeService;
import com.OCare.service.VolunteerService;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;



/**
 * Created by mark on 7/12/15.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private RelativeService relativeService;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private FtpService ftpService;


    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/hr")
    public String hrHome() {
        return "HR";
    }


    @RequestMapping("/volunteer")
    public String volunteerList(Model model)
    {
        model.addAttribute("volunteers",volunteerService.getAllVolunteers());
        return "volunteer";
    }

    @RequestMapping("/relative")
    public String relativeList(Model model)
    {
        model.addAttribute("relatives",relativeService.getAllRelatives());
        return "relative";
    }

    @RequestMapping("/hr/insert")
    @ResponseBody
    public Map<String,Object> hrInsert(String name,String address,String phone,String identity,String position,String department,String superior,String start_time,String finish_time) {
        Map<String, Object> result = new HashMap<String, Object>();
        double x=Math.random()*10000;
        if (name == null || address==null|| position==null|| identity==null|| phone==null|| department==null|| superior==null|| start_time==null|| finish_time==null){
            result.put("error", true);
            result.put("errorMsg", "There is no available data");
            return result;
        }
else {
            Employee employee = employeeService.createEmployee(x + "", 2, name, phone, address, start_time, finish_time, "xxx", "xxx", position, department, superior, "xxx", "xxx");
            result.put("error", false);
            result.put("created_name", employee.getId());
            return result;
        }
    }


    @RequestMapping("/hr/entry")
    public String hrEntry() {
        return "entry";
    }

    @RequestMapping("/hr/import")
    public String hrImport() {
        return "import";
    }

    @RequestMapping("/hr/leave")
    public String hrLeave() {
        return "leave";
    }

    @RequestMapping("/hr/table")
    public String hrTable() {
        return "table";
    }

    @RequestMapping("/ht/table")
    public String htTable() {
        return "htgl";
    }




    @RequestMapping("/hr/remove")
    @ResponseBody
    public Map<String, Object> removeemployeeById(String employeeid) {
        Employee employee=employeeService.getEmployeeById(employeeid);
        Map<String, Object> result = new HashMap<String, Object>();

        String xxx= employee.getPhone();
        String xxx1= employee.getDepartment();
        result.put("employee_id", employee.getId());
        result.put("employee_name",employee.getName());
        result.put("employee_phone",employee.getPhone());
        result.put("employee_position",employee.getPosition());
        result.put("employee_department",employee.getDepartment());
        result.put("employee_superior",employee.getSuperior());
        return result;


    }
    public static Workbook create(InputStream inp) throws IOException, InvalidFormatException {
        // If clearly doesn't do mark/reset, wrap up
        if(! inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }

        if(POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if(POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }

    @RequestMapping("hr/upload")

    public String fileUpload(HttpServletRequest request, MultipartFile file,Model model) throws IOException, InvalidFormatException {
        // 判断文件是否为空

        List<Employee> employeeList= new ArrayList<Employee>();
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
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            POIFSFileSystem fs = null;
            Workbook wb = null;
            try {
                System.out.println(convFile.getAbsolutePath());
                InputStream in = new FileInputStream(convFile.getAbsolutePath());
                wb= WorkbookFactory.create(in);
//                URL u=new URL("ftp://202.120.163.167/contract/"+convFile.getPath());//这个是ftp文件完整地址
//                URLConnection urlconn=u.openConnection();
//                urlconn.getInputStream();//获得InputStream流。
                fs = new POIFSFileSystem(in);
//                wb = new HSSFWorkbook(fs);

                 sheet = wb.getSheetAt(0);

            } catch (FileNotFoundException e) {
                System.out.println("未找到指定路径的文件!");
                e.printStackTrace();
            } catch (IOException e) {
                wb=WorkbookFactory.create(new FileInputStream(convFile.getAbsolutePath()));
                sheet = wb.getSheetAt(0);

            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
//            try {
//                fs = new POIFSFileSystem(new FileInputStream(convFile.getPath()));
//                wb = new HSSFWorkbook(fs);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



            int company_id;
            String name = "";
            String address= "";
            String phone= "";
            String identity= "";
            String position= "";
            String department= "";
            String superior= "";
            String status="";
            String start_time= "";
            String finish_time= "";
            int rowNum, cellNum;
            int i, j;
            // 获取总行数
            String[] str ;
            rowNum = sheet.getLastRowNum();
            for (i = 0; i <= rowNum; i++) {
                row = sheet.getRow(i);
                cellNum = row.getLastCellNum();
                str = new String[cellNum];
                for (j = 0; j < cellNum; j++) {
                    cell = row.getCell((short) j);
                    if (i==0){
                        break;
                    }
                    else if(j==0&&cell.getCellType()!=Cell.CELL_TYPE_NUMERIC){
                        str[0]="";
                            break;

                    }
                    else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                        str[j] = String.valueOf((int) cell.getNumericCellValue());
                    }
                    else{
                        str[j] = String.valueOf(cell.getStringCellValue());
                    }

                }
                if(i!=0 && !str[0].equals("")&& str[0]!=null){                                                     //(String employeeId, int companyId, String name, String phone,String address, String department, String position, String start,String end,int status, String password,String image,String superior, String workExperience,String workDetail,String last);
                    Employee employeefind = employeeService.getEmployeeById(str[0]);
                    if (employeefind!=null){
                       continue;
                    }

                    else {
                        Employee employee = employeeService.createEmployee(str[0], Integer.parseInt(str[1]), str[2], str[4], str[10], str[5], str[7], str[8], str[9], Integer.parseInt(str[11]), getRandomPass(8), "noimg", str[6], str[13],str[14],"?");
                        if(employee!=null){
                            employeeList.add(employee);
                        }
                    }

                }
            }
            model.addAttribute("new_employee",employeeList);
            System.out.println("success");


        }

        // 重定向
        return "import_new";
    }
    @RequestMapping("/ht/upload")
    public String htUpload() {
        return "contractUpload";
    }

    public static String getRandomPass(int pwd_len){
        //35是因为数组是从0开始的，26个字母+10个数字
        final int  maxNum = 36;
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < pwd_len){
            //生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }

        return pwd.toString();
    }

}
