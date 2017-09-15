package com.OCare.service;

import com.OCare.dao.AlarmHistoryDao;
import com.OCare.entity.AlarmHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ma on 2015/11/10.
 */
@Transactional
@Service("AlarmHistoryService")
public class AlarmHistoryServiceImp implements AlarmHistoryService  {

    @Autowired
    private AlarmHistoryDao alarmHistoryDao;


    @Override
    public List<AlarmHistory> getAlarmHistory() {

        List<AlarmHistory> listAll;
        listAll=(List<AlarmHistory>)alarmHistoryDao.queryAll();

  return listAll;
}


}