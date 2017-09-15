package com.OCare.controller;

import com.OCare.entity.Contract;
import com.OCare.entity.ElderCondition;
import com.OCare.service.ContractService;
import com.OCare.service.XMLFtpService;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark on 8/6/15.
 */
@Controller
@RequestMapping("/app")
public class ContractInterfaceController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private XMLFtpService ftpService;


    @RequestMapping("/contract/insert")
    @ResponseBody
    public Map<String, Object> insertContract(int id,int company_id,String elder_id,String startTime,String endTime,int status,String folder_name) throws ParseException {
        Map<String, Object> result = new HashMap<String, Object>();


        if (elder_id == null || elder_id.equals("")||startTime == null || startTime.equals("")||endTime == null || endTime.equals("")||folder_name == null || folder_name.equals("")){
            result.put("error", true);
            result.put("errorMsg", "Input is null");
            return result;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        Date start_time= sdf.parse(startTime);
        Date end_time= sdf.parse(endTime);

        Contract contract=new Contract();
        contract.setId(id);
        contract.setCompany_id(company_id);
        contract.setElder_id(elder_id);
        contract.setStart_time(start_time);
        contract.setEnd_time(end_time);
        contract.setStatus(status);
        contract.setFolder_name(folder_name);
        System.out.println(">>>>>>>>>>>test"+folder_name);
        boolean b=contractService.insertContract(contract);


        result.put("error", false);
        result.put("insert","insert succeed" );
        return result;
    }

    @ResponseBody
    @RequestMapping("/contract/all")
    public Map<String, Object> getAllContract(){
        Map<String, Object> result = new HashMap<String, Object>();

        List<Contract> list = contractService.getAllContracts();

        if(list == null || list.size() == 0){
            result.put("error", true);
            result.put("errorMsg", "No contracts");
            return result;
        }

        result.put("error", false);
        result.put("contracts", list);
        return result;
    }

    @ResponseBody
    @RequestMapping("/contract/id")
    public Map<String, Object> getAllContractById(String id){
        Map<String, Object> result = new HashMap<String, Object>();

        if (id == null || id.equals("")){
            result.put("error", true);
            result.put("errorMsg", "Input is null");
            return result;
        }

        Contract contract = contractService.getContractsById(Integer.parseInt(id));

        if (contract == null){
            result.put("error", true);
            result.put("errorMsg", "Contracts not exist");
            return result;
        }

        result.put("error", false);
        result.put("contracts", contract);
        return result;
    }

    @ResponseBody
    @RequestMapping("/contract/elderId")
    public Map<String, Object> getAllContractByElderId(String id) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (id == null || id.equals("")){
            result.put("error", true);
            result.put("errorMsg", "Input is null");
            return result;
        }

        List<Contract> list = contractService.getContractsByElderId(id);

        if (list == null || list.size() == 0){
            result.put("error", true);
            result.put("errorMsg", "Contracts not exist");
            return result;
        }

        result.put("error", false);
        result.put("contracts", list);
        return result;
    }

    @ResponseBody
    @RequestMapping("/elder/condition")
    public Map<String, Object> getElderConditionByElderId(String id) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (id == null || id.equals("")){
            result.put("error", true);
            result.put("errorMsg", "Input is null");
            return result;
        }

        List<ElderCondition> list = contractService.getElderConditionByElderId(id);

        if (list == null || list.size() == 0){
            result.put("error", true);
            result.put("errorMsg", "Condition not exist");
            return result;
        }

        result.put("error", false);
        result.put("contracts", list);
        return result;
    }


    @ResponseBody
    @RequestMapping("/contract/xmlTodoc")
    public Map<String, Object> xmlTodoc(String fileName) {
        Map<String, Object> result = new HashMap<String, Object>();
        String downloadResult = ftpService.downloadFile(fileName);
        result.put("downloadResult",downloadResult);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Document document = builder.parse("xmlTodoc/"+fileName + ".xml");

            File xmlfile = new File("xmlTodoc/"+fileName + ".xml");

            InputStream is = new FileInputStream(xmlfile);
            InputStreamReader reader = new InputStreamReader(is,"utf-8");
            InputSource source = new InputSource(reader);

            Document document = builder.parse(source);
            //读取完之后删除文件
            if (xmlfile.exists()) xmlfile.delete();

            Element root = document.getDocumentElement();
            Element Edit = (Element) root.getElementsByTagName("Edit").item(0);
            Element CheckBox = (Element) root.getElementsByTagName("CheckBox").item(0);
            Element TextView = (Element) root.getElementsByTagName("TextView").item(0);

            Element edit_1 = (Element) Edit.getElementsByTagName("edit_1").item(0);
            Element edit_2 = (Element) Edit.getElementsByTagName("edit_2").item(0);
            Element edit_3 = (Element) Edit.getElementsByTagName("edit_3").item(0);
            Element edit_4 = (Element) Edit.getElementsByTagName("edit_4").item(0);
            Element edit_5 = (Element) Edit.getElementsByTagName("edit_5").item(0);
            Element edit_6 = (Element) Edit.getElementsByTagName("edit_6").item(0);
            Element edit_7 = (Element) Edit.getElementsByTagName("edit_7").item(0);
            Element edit_8 = (Element) Edit.getElementsByTagName("edit_8").item(0);
            Element edit_9 = (Element) Edit.getElementsByTagName("edit_9").item(0);
            Element edit_10 = (Element) Edit.getElementsByTagName("edit_10").item(0);
            Element edit_11 = (Element) Edit.getElementsByTagName("edit_11").item(0);
            Element edit_12 = (Element) Edit.getElementsByTagName("edit_12").item(0);
            Element edit_13 = (Element) Edit.getElementsByTagName("edit_13").item(0);
            Element edit_14 = (Element) Edit.getElementsByTagName("edit_14").item(0);
            Element edit_15 = (Element) Edit.getElementsByTagName("edit_15").item(0);
            Element edit_16 = (Element) Edit.getElementsByTagName("edit_16").item(0);
            Element edit_17 = (Element) Edit.getElementsByTagName("edit_17").item(0);
            Element edit_18 = (Element) Edit.getElementsByTagName("edit_18").item(0);
            Element edit_19 = (Element) Edit.getElementsByTagName("edit_19").item(0);
            Element edit_20 = (Element) Edit.getElementsByTagName("edit_20").item(0);
            Element edit_21 = (Element) Edit.getElementsByTagName("edit_21").item(0);
            Element edit_22 = (Element) Edit.getElementsByTagName("edit_22").item(0);
            Element edit_23 = (Element) Edit.getElementsByTagName("edit_23").item(0);
            Element edit_24 = (Element) Edit.getElementsByTagName("edit_24").item(0);
            Element edit_25 = (Element) Edit.getElementsByTagName("edit_25").item(0);
            Element edit_26 = (Element) Edit.getElementsByTagName("edit_26").item(0);
            Element edit_27 = (Element) Edit.getElementsByTagName("edit_27").item(0);
            Element edit_28 = (Element) Edit.getElementsByTagName("edit_28").item(0);
            Element edit_29 = (Element) Edit.getElementsByTagName("edit_29").item(0);
            Element edit_30 = (Element) Edit.getElementsByTagName("edit_30").item(0);
            Element edit_31 = (Element) Edit.getElementsByTagName("edit_31").item(0);
            Element edit_32 = (Element) Edit.getElementsByTagName("edit_32").item(0);
            Element edit_33 = (Element) Edit.getElementsByTagName("edit_33").item(0);
            Element edit_34 = (Element) Edit.getElementsByTagName("edit_34").item(0);
            Element edit_35 = (Element) Edit.getElementsByTagName("edit_35").item(0);
            Element edit_36 = (Element) Edit.getElementsByTagName("edit_36").item(0);
            Element edit_37 = (Element) Edit.getElementsByTagName("edit_37").item(0);
            Element edit_38 = (Element) Edit.getElementsByTagName("edit_38").item(0);
            Element edit_39 = (Element) Edit.getElementsByTagName("edit_39").item(0);
            Element edit_40 = (Element) Edit.getElementsByTagName("edit_40").item(0);
            Element edit_41 = (Element) Edit.getElementsByTagName("edit_41").item(0);
            Element edit_42 = (Element) Edit.getElementsByTagName("edit_42").item(0);
            Element edit_43 = (Element) Edit.getElementsByTagName("edit_43").item(0);
            Element edit_44 = (Element) Edit.getElementsByTagName("edit_44").item(0);
            Element edit_45 = (Element) Edit.getElementsByTagName("edit_45").item(0);
            Element edit_46 = (Element) Edit.getElementsByTagName("edit_46").item(0);
            Element edit_47 = (Element) Edit.getElementsByTagName("edit_47").item(0);
            Element edit_48 = (Element) Edit.getElementsByTagName("edit_48").item(0);
            Element edit_49 = (Element) Edit.getElementsByTagName("edit_49").item(0);
            Element edit_50 = (Element) Edit.getElementsByTagName("edit_50").item(0);
            Element edit_51 = (Element) Edit.getElementsByTagName("edit_51").item(0);
            Element edit_52 = (Element) Edit.getElementsByTagName("edit_52").item(0);
            Element edit_53 = (Element) Edit.getElementsByTagName("edit_53").item(0);
            Element edit_54 = (Element) Edit.getElementsByTagName("edit_54").item(0);
            Element edit_55 = (Element) Edit.getElementsByTagName("edit_55").item(0);
            Element edit_56 = (Element) Edit.getElementsByTagName("edit_56").item(0);
            Element edit_57 = (Element) Edit.getElementsByTagName("edit_57").item(0);
            Element edit_58 = (Element) Edit.getElementsByTagName("edit_58").item(0);
            Element edit_59 = (Element) Edit.getElementsByTagName("edit_59").item(0);
            Element edit_60 = (Element) Edit.getElementsByTagName("edit_60").item(0);
            Element edit_61 = (Element) Edit.getElementsByTagName("edit_61").item(0);
            Element edit_62 = (Element) Edit.getElementsByTagName("edit_62").item(0);
            Element edit_63 = (Element) Edit.getElementsByTagName("edit_63").item(0);
            Element edit_64 = (Element) Edit.getElementsByTagName("edit_64").item(0);
            Element edit_65 = (Element) Edit.getElementsByTagName("edit_65").item(0);
            Element edit_66 = (Element) Edit.getElementsByTagName("edit_66").item(0);
            Element edit_67 = (Element) Edit.getElementsByTagName("edit_67").item(0);
            Element edit_68 = (Element) Edit.getElementsByTagName("edit_68").item(0);
            Element edit_69 = (Element) Edit.getElementsByTagName("edit_69").item(0);

            Element checkbox_1 = (Element) CheckBox.getElementsByTagName("checkbox_1").item(0);
            Element checkbox_2 = (Element) CheckBox.getElementsByTagName("checkbox_2").item(0);
            Element checkbox_3 = (Element) CheckBox.getElementsByTagName("checkbox_3").item(0);
            Element checkbox_4 = (Element) CheckBox.getElementsByTagName("checkbox_4").item(0);
            Element checkbox_5 = (Element) CheckBox.getElementsByTagName("checkbox_5").item(0);
            Element checkbox_6 = (Element) CheckBox.getElementsByTagName("checkbox_6").item(0);
            Element checkbox_7 = (Element) CheckBox.getElementsByTagName("checkbox_7").item(0);
            Element checkbox_8 = (Element) CheckBox.getElementsByTagName("checkbox_8").item(0);
            Element checkbox_9 = (Element) CheckBox.getElementsByTagName("checkbox_9").item(0);
            Element checkbox_10 = (Element) CheckBox.getElementsByTagName("checkbox_10").item(0);
            Element checkbox_11 = (Element) CheckBox.getElementsByTagName("checkbox_11").item(0);
            Element checkbox_12 = (Element) CheckBox.getElementsByTagName("checkbox_12").item(0);
            Element checkbox_13 = (Element) CheckBox.getElementsByTagName("checkbox_13").item(0);

            Element textview_1 = (Element) TextView.getElementsByTagName("textview_1").item(0);
            Element textview_2 = (Element) TextView.getElementsByTagName("textview_2").item(0);
            Element textview_3 = (Element) TextView.getElementsByTagName("textview_3").item(0);





            InputStream in = new FileInputStream("xmlTodoc/ContractTemp.doc");
            HWPFDocument doc = new HWPFDocument(in);
            Range range = doc.getRange();

            range.replaceText("${flag1}",edit_1.getTextContent());
            range.replaceText("${flag2}",edit_2.getTextContent());
            range.replaceText("${flag3}",edit_3.getTextContent());
            range.replaceText("${flag4}",edit_4.getTextContent());
            range.replaceText("${flag5}",edit_5.getTextContent());
            range.replaceText("${flag8}",edit_6.getTextContent());
            range.replaceText("${flag9}",edit_7.getTextContent());
            range.replaceText("${flag10}",edit_8.getTextContent());
            range.replaceText("${flag11}",edit_9.getTextContent());
            range.replaceText("${flag12}",edit_10.getTextContent());
            range.replaceText("${flag13}",edit_11.getTextContent());
            range.replaceText("${flag14}",edit_12.getTextContent());
            range.replaceText("${flag15}",edit_13.getTextContent());
            range.replaceText("${flag16}",edit_14.getTextContent());
            range.replaceText("${flag17}",edit_15.getTextContent());
            range.replaceText("${flag18}",edit_16.getTextContent());
            range.replaceText("${flag19}",edit_17.getTextContent());
            range.replaceText("${flag20}",edit_18.getTextContent());
            range.replaceText("${flag21}",edit_19.getTextContent());
            range.replaceText("${flag22}",edit_20.getTextContent());
            range.replaceText("${flag23}",edit_21.getTextContent());
            range.replaceText("${flag24}",edit_22.getTextContent());
            range.replaceText("${flag25}",edit_23.getTextContent());
            range.replaceText("${flag26}",edit_24.getTextContent());
            range.replaceText("${flag27}",edit_25.getTextContent());
            range.replaceText("${flag28}",edit_26.getTextContent());
            range.replaceText("${flag29}",edit_27.getTextContent());
            range.replaceText("${flag30}",edit_28.getTextContent());
            range.replaceText("${flag31}",edit_29.getTextContent());
            range.replaceText("${flag32}",edit_30.getTextContent());
            range.replaceText("${flag33}",edit_31.getTextContent());
            range.replaceText("${flag34}",edit_32.getTextContent());
            range.replaceText("${flag35}",edit_33.getTextContent());
            range.replaceText("${flag36}",edit_34.getTextContent());
            range.replaceText("${flag37}",edit_35.getTextContent());
            range.replaceText("${flag38}",edit_36.getTextContent());
            range.replaceText("${flag39}",edit_37.getTextContent());
            range.replaceText("${flag40}",edit_38.getTextContent());
            range.replaceText("${flag41}",edit_39.getTextContent());
            range.replaceText("${flag42}",edit_40.getTextContent());
            range.replaceText("${flag43}",edit_41.getTextContent());
            range.replaceText("${flag44}",edit_42.getTextContent());
            range.replaceText("${flag45}",edit_43.getTextContent());
            range.replaceText("${flag46}",edit_44.getTextContent());
            range.replaceText("${flag47}",edit_45.getTextContent());
            range.replaceText("${flag48}",edit_46.getTextContent());
            range.replaceText("${flag49}",edit_47.getTextContent());
            range.replaceText("${flag50}",edit_48.getTextContent());
            range.replaceText("${flag51}",edit_49.getTextContent());
            range.replaceText("${flag52}",edit_50.getTextContent());
            range.replaceText("${flag53}",edit_51.getTextContent());
            range.replaceText("${flag55}",edit_52.getTextContent());
            range.replaceText("${flag56}",edit_53.getTextContent());
            range.replaceText("${flag57}",edit_54.getTextContent());
            range.replaceText("${flag58}",edit_55.getTextContent());
            range.replaceText("${flag59}",edit_56.getTextContent());
            range.replaceText("${flag60}",edit_57.getTextContent());
            range.replaceText("${flag61}",edit_58.getTextContent());
            range.replaceText("${flag62}",edit_59.getTextContent());
            range.replaceText("${flag63}",edit_60.getTextContent());
            range.replaceText("${flag64}",edit_61.getTextContent());
            range.replaceText("${flag65}",edit_62.getTextContent());
            range.replaceText("${flag66}",edit_63.getTextContent());
            range.replaceText("${flag67}",edit_64.getTextContent());
            range.replaceText("${flag68}",edit_65.getTextContent());
            range.replaceText("${flag69}",edit_66.getTextContent());
            range.replaceText("${flag70}",edit_67.getTextContent());
            range.replaceText("${flag71}",edit_68.getTextContent());
            range.replaceText("${flag72}",edit_69.getTextContent());

            range.replaceText("${flag6}",textview_1.getTextContent());
            range.replaceText("${flag7}",textview_2.getTextContent());
            range.replaceText("${flag54}",textview_3.getTextContent());

            range.replaceText("${check1}",checkbox_1.getTextContent());
            range.replaceText("${check2}",checkbox_2.getTextContent());
            range.replaceText("${check3}",checkbox_3.getTextContent());
            range.replaceText("${check4}",checkbox_4.getTextContent());
            range.replaceText("${check5}",checkbox_5.getTextContent());
            range.replaceText("${check6}",checkbox_6.getTextContent());
            range.replaceText("${check7}",checkbox_7.getTextContent());
            range.replaceText("${check8}",checkbox_8.getTextContent());
            range.replaceText("${check9}",checkbox_9.getTextContent());
            range.replaceText("${check10}",checkbox_10.getTextContent());
            range.replaceText("${check11}",checkbox_11.getTextContent());
            range.replaceText("${check12}",checkbox_12.getTextContent());
            range.replaceText("${check13}",checkbox_13.getTextContent());





            OutputStream os = new FileOutputStream("xmlTodoc/" + fileName +".doc");
            doc.write(os);
            in.close();
            os.close();

            String uploadResult = ftpService.uploadDocFile(fileName);
            result.put("uploadResult",uploadResult);

            //上传完之后删除掉本地生成的doc文件
            //            File docfile = new File("xmlTodoc/" + fileName +".doc");
            //            if (docfile.exists()) docfile.delete();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}



