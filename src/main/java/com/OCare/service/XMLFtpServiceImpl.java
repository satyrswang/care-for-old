package com.OCare.service;

import it.sauronsoftware.ftp4j.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * Created by Ma on 2015/11/18.
 */

@Service
@Transactional
public class XMLFtpServiceImpl implements XMLFtpService{
    @Override
    public String downloadFile(String fileName) {
        try {
            FTPClient client = new FTPClient();
            client.connect("202.120.163.167", 21);
            client.login("ocare", "ocare");
            client.changeDirectory("Contracts");
            client.changeDirectory(fileName);
            System.out.println(client);


            File file = new File("xmlTodoc/"+ fileName + ".xml");
            System.out.print("path:>>>>>>>>>>>>>"+file.getPath());
            if (!file.exists())
                file.createNewFile();
            client.download(fileName + ".xml",file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String uploadDocFile(String fileName) {
        try {
            FTPClient client = new FTPClient();
            client.connect("202.120.163.167", 21);
            client.login("ocare", "ocare");
            client.changeDirectory("Contracts");
            client.changeDirectory(fileName);
            System.out.println(client);
            File file = new File("xmlTodoc/" + fileName + ".doc");
            client.upload(file);
            file.delete();
            client.disconnect(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
