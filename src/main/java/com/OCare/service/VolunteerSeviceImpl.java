package com.OCare.service;

import com.OCare.dao.VolunteerDAO;
import com.OCare.entity.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by жиЪщ on 2015/12/10.
 */
@Service("VolunteerService")
@Transactional
public class VolunteerSeviceImpl implements VolunteerService {
    @Autowired
    private VolunteerDAO volunteerDAO;
    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerDAO.queryAll();
    }
}
