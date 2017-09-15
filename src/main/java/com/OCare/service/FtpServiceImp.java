package com.OCare.service;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

/**
 * Created by fordevelopment on 15/11/11.
 */
@Service
@Transactional
public class FtpServiceImp implements FtpService {

    @Override
    public String uploadFile(File file) {
        try {
            // 创建客户端
            FTPClient client = new FTPClient();
            // 不指定端口，则使用默认端口21
            client.connect("202.120.163.167", 21);
            // 用户登录
            client.login("ocare", "ocare");
            // 打印地址信息
            System.out.println(client);
            client.changeDirectory("contract");
            client.upload(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public File getFileById(String id) {

        return null;
    }
    //by qian
    @Override
    public String uploadFileById(File file, String id) {

        try {
            // 创建客户端
            FTPClient client = new FTPClient();
            // 不指定端口，则使用默认端口21
            client.connect("202.120.163.167", 21);
            // 用户登录
            client.login("ocare", "ocare");
            // 打印地址信息
            System.out.println(client);

            client.changeDirectory("employee");
//            if(client.currentDirectory().equals(isDirExist(client,id))) {
//                client.createDirectory(id);
//            }
            client.changeDirectory(id);

            client.upload(file);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    //by qian
    @Override
    public String uploadFileForLp(File file, String id) {

        try {
            // 创建客户端
            FTPClient client = new FTPClient();
            // 不指定端口，则使用默认端口21
            client.connect("202.120.163.167", 21);
            // 用户登录
            client.login("ocare", "ocare");
            // 打印地址信息
            System.out.println(client);

            client.changeDirectory("legalperson");

//            if(client.currentDirectory().equals(isDirExist(client,id))) {
//                client.createDirectory(id);
//            }
            client.changeDirectory(id);

            client.upload(file);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    //by qian
    @Override
    public String CreateDir(String dir) {

        String root;
        try {
            // 创建客户端
            FTPClient ftpClient = new FTPClient();
            // 不指定端口，则使用默认端口21
            ftpClient.connect("202.120.163.167", 21);
            // 用户登录
            ftpClient.login("ocare", "ocare");
            // 打印地址信息
            ftpClient.changeDirectory("employee");
            ftpClient.createDirectory(dir);
            root=ftpClient.currentDirectory();
        } catch (IOException e) {
            return "error";
        } catch (FTPIllegalReplyException e) {
            return "error";
        } catch (FTPException e) {
            return "error";
        }

        System.out.println(root);
        return root;
    }


    //by qian
    @Override
    public String CreateLp(String dir) {

        String root;
        try {
            // 创建客户端
            FTPClient ftpClient = new FTPClient();
            // 不指定端口，则使用默认端口21
            ftpClient.connect("202.120.163.167", 21);
            // 用户登录
            ftpClient.login("ocare", "ocare");
            // 打印地址信息
            //ftpClient.createDirectory("legalperson");
            ftpClient.changeDirectory("legalperson");
            ftpClient.createDirectory(dir);
            root=ftpClient.currentDirectory();
        } catch (IOException e) {
            return "error";
        } catch (FTPIllegalReplyException e) {
            return "error";
        } catch (FTPException e) {
            return "error";
        }

        System.out.println(root);
        return root;
    }

    @Override
    public String CreateCompany(String dir) {
        String root;
        try {
            // 创建客户端
            FTPClient ftpClient = new FTPClient();
            // 不指定端口，则使用默认端口21
            ftpClient.connect("202.120.163.167", 21);
            // 用户登录
            ftpClient.login("ocare", "ocare");
            // 打印地址信息
            //ftpClient.createDirectory("company");
            ftpClient.changeDirectory("company");
            ftpClient.createDirectory(dir);
            root=ftpClient.currentDirectory();
        } catch (IOException e) {
            return "error";
        } catch (FTPIllegalReplyException e) {
            return "error";
        } catch (FTPException e) {
            return "error";
        }

        System.out.println(root);
        return root;
    }

    @Override
    public String uploadFileForCompany(File file, String id) {
        try {
            // 创建客户端
            FTPClient client = new FTPClient();
            // 不指定端口，则使用默认端口21
            client.connect("202.120.163.167", 21);
            // 用户登录
            client.login("ocare", "ocare");
            // 打印地址信息
            System.out.println(client);
            client.changeDirectory("company");
//            if(client.currentDirectory().equals(isDirExist(client,id))) {
//                client.createDirectory(id);
//            }
            client.changeDirectory(id);

            client.upload(file);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
