package com.bik.action;

import java.io.InputStream;   
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;  
import com.opensymphony.xwork2.ActionSupport; 

public class DownloadAction  extends ActionSupport{   
    private String VideoName; 
    private String name1;      
    public String getVideoName() {  
        return VideoName;  
    }   
    public void setVideoName(String VideoName) { 
        this.VideoName = VideoName;  
    }  
    public String getName1(){
    	return name1;
    }
    public void setName1(String name){
    	this.name1=name;
    }
    public InputStream getInputStream() {
    	org.apache.hadoop.conf.Configuration configuration= new Configuration();
    	
		FileSystem fileSystem =null;
		Path path=new Path("/ssw/"+name1+"/"+VideoName);
		FSDataInputStream in=null;
		try{
			fileSystem=FileSystem.get(configuration);
			in =  fileSystem.open(path);
			if(in == null) {
				System.out.println("File not found in hadoop current dir.");
			} 
		}catch (Exception e) {
				e.printStackTrace();
		}
        return in;
    } 
    public String execute() throws Exception {  
        return SUCCESS;  
    }  
  
}  