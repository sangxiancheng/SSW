package com.bik.action;

import hadoop.HdfsOp;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import org.apache.struts2.ServletActionContext;   
import com.opensymphony.xwork2.ActionSupport; 

public class UploadAction  extends ActionSupport{  
    // username属性用来封装用户名  
    private String username;  
      
    // myFile属性用来封装上传的文件  
    private File myFile;  
      
    // myFileContentType属性用来封装上传文件的类型  
    private String myFileContentType;  
  
    // myFileFileName属性用来封装上传文件的文件名  
    private String myFileFileName;  
    
    //获得username值  
    public String getUsername() {  
        return username;  
    }  
  
    //设置username值  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    //获得myFile值  
    public File getMyFile() {  
        return myFile;  
    }  
  
    //设置myFile值  
    public void setMyFile(File myFile) {  
        this.myFile = myFile;  
    }  
  
    //获得myFileContentType值  
    public String getMyFileContentType() {  
        return myFileContentType;  
    }  
  
    //设置myFileContentType值  
    public void setMyFileContentType(String myFileContentType) {  
        this.myFileContentType = myFileContentType;  
    }  
  
    //获得myFileFileName值  
    public String getMyFileFileName() {  
        return myFileFileName;  
    }  
  
    //设置myFileFileName值  
    public void setMyFileFileName(String myFileFileName) {  
        this.myFileFileName = myFileFileName;  
    }  
  
    public String execute() throws Exception {  
        HdfsOp hdfsOp = new HdfsOp();
        //基于myFile创建一个文件输入流  
        InputStream is = new FileInputStream(myFile); 
        hdfsOp.copyToHDFS(is, this.getMyFileFileName());
        is.close();
        return SUCCESS;  
    }  
  
}  