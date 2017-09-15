package com.OCare.service;

import com.OCare.entity.ofMucMember;
import com.OCare.entity.ofMucRoom;

import java.util.List;

/**
 * Created by mark on 10/27/15.
 */
public interface openFireService {
    //先通过name取到roomId
    public Object findRoomidByName(String name);
    //通过jid取到affiliation
    public Object findAffByPhoneNum(String phoneNumber);

    //传说中的管理员查看某房间中的所有成员
    public Object findMembersByPhoneNum(String phoneNum, String roomId);
    public Object findRoomsById(String roomId);
    //某人查看某房间中的某一成员
    public Object findMemberByPhoneNumAndRoomId(String phoneNum, String roomId);

    /*
        功能：拿到成员或者管理员所在房间内的所有成员的电话号码
     */
    public Object getPeopleByPhoneNumAndRoomId(String phoneNum, int roomId);

    //根据电话查看所在房间
    public List<ofMucMember> getRoomIdbyPhone(String phone);
    //根据RoomId查到ofMucRoom
    public List<ofMucRoom> getRoomByid(long id);
}
