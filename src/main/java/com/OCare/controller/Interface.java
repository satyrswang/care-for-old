package com.OCare.controller;

import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fowafolo on 15/7/31.
 */
@Controller
@RequestMapping("/interface")
public class Interface {

    @RequestMapping("")
    @ResponseBody
    public Map<String , String> json(){
        Map<String,String> result = new HashMap<String, String>();
        result.put("杨春雨", "蠢驴");
        result.put("杨春雨", "逗比");
        return result;
    }

    @RequestMapping("/json")
    @ResponseBody
    public Map<String, Object> hello(){
        Map<String,Object> result = new HashMap<String, Object>();
        String s = "Mark";
        ArrayList<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        Map<String,String> abc = new HashMap<String, String>();
        abc.put("杨春雨", "蠢驴");
        abc.put("杨春", "逗比");

        result.put("first", s);
        result.put("second", list);
        result.put("third", abc);

        return result;
    }
}
