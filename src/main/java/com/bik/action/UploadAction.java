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
import beans.Videos;

import com.opensymphony.xwork2.ActionSupport; 

public class UploadAction  extends ActionSupport{   
    private String username;//username属性用来封装用户名  
    private File myFile;//myFile属性用来封装上传的文件 
    private String fileLocation; 
    private String keys;
    private String comments;
    private String myFileContentType;// myFileContentType属性用来封装上传文件的类型  
    private String myFileFileName;//myFileFileName属性用来封装上传文件的文件名  
    Videos video = new Videos();
    
    public String getUsername() { //获得username值  
        return username;  
    }  
    public void setUsername(String username) {   //设置username值 
        this.username = username;  
    }  
  
      
    public File getMyFile() {  //获得myFile值
        return myFile;  
    }   
    public void setMyFile(File myFile) {  //设置myFile值 
        this.myFile = myFile;  
    }
    
    
    public String getKeys(){
    	return keys;
    }
    public void setKeys(String keys){
    	this.keys = keys;
    }
    
    
    public String getComments(){
    	return comments;
    }
    public void setComments(String comments){
    	this.comments = comments;
    }
    
    
    public String getFileLocation(){
    	return fileLocation;
    }
    public void setFileLocation(String fileLocation){
    	this.fileLocation=fileLocation;
    }
  
     
    public String getMyFileContentType() {  //获得myFileContentType值 
        return myFileContentType;  
    }    
    public void setMyFileContentType(String myFileContentType) {  //设置myFileContentType值
        this.myFileContentType = myFileContentType;  
    }  
      
    
    public String getMyFileFileName() {  //获得myFileFileName值
        return myFileFileName;  
    }     
    public void setMyFileFileName(String myFileFileName) { //设置myFileFileName值 
        this.myFileFileName = myFileFileName;  
    }  
  
    public String execute() throws Exception {  
        HdfsOp hdfsOp = new HdfsOp();
        //org.apache.hadoop.conf.Configuration conf = new Configuration();
        System.out.println(username);
        String path="/ssw/"+username+"/"+myFileFileName;
        InputStream is = new FileInputStream(myFile); 
        String result1=hdfsOp.copyToHDFS(is, path);
        System.out.println(result1);
        is.close();
        //System.out.println(username+myFileFileName);
        //String hdfsPath = "user/"+username+"/"+myFileFileName;
        //System.out.println(hdfsPath);
        //hdfsOp.copyFromLocalFile(conf, locationDir, newDir);
        //基于myFile创建一个文件输入流  
        //String fileContent = "Hi,hadoop. I love you";
        //String newFileName = newDir + "/myfile.txt";
        //hdfsOp.createFile(conf, newFileName, fileContent);
        //System.out.println(newFileName + " 创建成功");
        //System.out.println(newFileName + " 的内容为:\n" + hdfsOp.readFile(conf, newFileName));
        try{
        video.setName(username);
        video.setVideoname(myFileFileName);
        video.setVideokeys(keys);
        video.setVideolocation(username);
        video.setVideocomments(comments);
        video.setVideosize(myFile.length()/1024);
        int result2=video.addVideo();
        System.out.println(result2);
        if(result1.equals("ERROR")||result2!=1)
        	return ERROR;
        else
        	return SUCCESS;
        }catch(Exception e){
        	e.printStackTrace();
        }  
       return SUCCESS;
    }
  
}  