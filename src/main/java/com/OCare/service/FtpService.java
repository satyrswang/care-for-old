package com.OCare.service;


import java.io.File;

/**
 * Created by fordevelopment on 15/11/11.
 */
public interface FtpService {
    String uploadFile(File file);
    File getFileById(String id);

    String uploadFileById(File file, String id);
    String CreateDir(String dir);
    String uploadFileForLp(File file,String id);
    String CreateLp(String dir);
    String CreateCompany(String dir);
    String uploadFileForCompany(File file,String id);

}
