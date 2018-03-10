package com.bik.action;

import hadoop.HdfsOp;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import org.apache.struts2.ServletActionContext;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import com.opensymphony.xwork2.ActionSupport; 

public class UploadAction  extends ActionSupport{  
    // username属性用来封装用户名  
    private String username;  
      
    // myFile属性用来封装上传的文件  
    private File myFile;
    private String filePath;
      
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
    public String getFilePath(){
    	return filePath;
    }
    public void setFilePath(String filePath){
    	this.filePath=filePath;
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
        org.apache.hadoop.conf.Configuration conf = new Configuration();
        //System.out.println(username+myFileFileName);
        //String hdfsPath = "user/"+username+"/"+myFileFileName;
        //System.out.println(hdfsPath);
        String path="/user/"+username+"/"+myFileFileName;
        InputStream is = new FileInputStream(myFile); 
        hdfsOp.copyToHDFS(is, path);
        is.close();
        //hdfsOp.copyFromLocalFile(conf, locationDir, newDir);
        //基于myFile创建一个文件输入流  
        
        //String fileContent = "Hi,hadoop. I love you";
        //String newFileName = newDir + "/myfile.txt";
        //hdfsOp.createFile(conf, newFileName, fileContent);
        //System.out.println(newFileName + " 创建成功");
        //System.out.println(newFileName + " 的内容为:\n" + hdfsOp.readFile(conf, newFileName));
        
        return SUCCESS;  
    }  
  
}  