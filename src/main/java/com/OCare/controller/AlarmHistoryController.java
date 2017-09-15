package com.OCare.controller;

import com.OCare.entity.AlarmHistory;
import com.OCare.service.AlarmHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ma on 2015/11/10.
 */
@Controller
@RequestMapping("/app/alarm")
public class AlarmHistoryController {

    @Autowired
    private AlarmHistoryService alarmHistoryService;

    /*
       功能：于子涵
     */
    @RequestMapping("/get/set")
    @ResponseBody
    public Map<String,Object> listAlarmHistoryBySet(int limit,int offset) {

        Map<String, Object> result1 = new HashMap<String, Object>();



        offset=offset+1;
        List<AlarmHistory> result= (List<AlarmHistory>) alarmHistoryService.getAlarmHistory();
        List<AlarmHistory> list = new ArrayList<AlarmHistory>();


        int set=limit*(offset-1);
        if((set+limit)<=result.size()) {
            list = result.subList(set, set + limit);
        }else if(set<=result.size())
        {
            list = result.subList(set, result.size());
        }


        result1.put("total",result.size());
        result1.put("rows",result);


        return result1;
    }

    @RequestMapping("/get/all")
    @ResponseBody
    public Map<String,Object> listAllAlarmHistory() {

        Map<String, Object> result1 = new HashMap<String, Object>();




        List<AlarmHistory> result= (List<AlarmHistory>) alarmHistoryService.getAlarmHistory();


        result1.put("total",result.size());
        result1.put("rows",result);


        return result1;
    }
}
