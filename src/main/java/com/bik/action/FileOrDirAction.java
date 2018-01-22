package com.bik.action;

import hadoop.HdfsOp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileOrDirAction extends ActionSupport{
	private String FileName;
	private String FileSize;
	private String location1;
	
	public String getFileName(){
		return FileName;
	}
	public void setFileName(String FileName){
		this.FileName=FileName;
	}
	
	public String getLocation1(){
		return location1;
	}
	public void setLocation1(String location1){
		this.location1=location1;
	}
	
	public String getFileSize(){
		return FileSize;
	}
	public void setFileSize(String FileSize){
		this.FileSize=FileSize;
	}
	
	
	public String execute() throws Exception {  
	      
    	String FileName = ServletActionContext.getRequest().getParameter("FileName");
    	String FileSize = ServletActionContext.getRequest().getParameter("FileSize");
    	String location1 = ServletActionContext.getRequest().getParameter("location1");
    	
        FileName=java.net.URLDecoder.decode(FileName,"utf-8");
        FileSize=java.net.URLDecoder.decode(FileSize,"utf-8");
        location1=java.net.URLDecoder.decode(location1,"utf-8");
        
        System.out.println("6"+location1+"!!!!!!!!!!"+FileName+"@@@@@@@"+FileSize);
    	FileName=java.net.URLEncoder.encode(FileName,"utf-8");
    	FileSize=java.net.URLEncoder.encode(FileSize,"utf-8");
    	location1=java.net.URLEncoder.encode(location1,"utf-8");
    	
    	if(FileSize.equals("null"))
    		return SUCCESS;
    	else
    		return INPUT;
    }  

}
