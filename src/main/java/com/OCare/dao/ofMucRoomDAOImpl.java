package com.OCare.dao;

import com.OCare.entity.ofMucRoom;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mark on 10/27/15.
 */
@Repository
public class ofMucRoomDAOImpl extends IGeneralDAOImpl<ofMucRoom> implements ofMucRoomDAO {
    public ofMucRoomDAOImpl() {
        super(ofMucRoom.class);
    }

    @Override
    public List<ofMucRoom> getRoomByName(String name) {
        String hql = "from ofMucRoom where name = :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m", name);
        List<ofMucRoom> list=query.list();

        return list;
    }

    @Override
    public List<ofMucRoom> getRoomsById(long id) {
        String hql = "from ofMucRoom where roomId = :m";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("m", id);
        return query.list();
    }
}
