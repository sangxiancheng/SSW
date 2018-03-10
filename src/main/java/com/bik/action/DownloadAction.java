package com.bik.action;


import java.io.InputStream;  
  
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
  
import com.opensymphony.xwork2.ActionSupport; 
import javax.swing.JOptionPane;

public class DownloadAction  extends ActionSupport{  
    // username属性用来封装用户名  
    private String VideoName;  
          
   //获得FileFileName值  
    public String getVideoName() {  
        return VideoName;  
    }  
  
    //设置FileName值  
    public void setVideoName(String VideoName) { 
        this.VideoName = VideoName;  
    }  

    public InputStream getInputStream() {
    	org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(VideoName);
		FSDataInputStream in=null;
		try{
			fileSystem=FileSystem.get(configuration);
			in =  fileSystem.open(path);
			//fileSystem.copyToLocalFile(path, new Path("/home/user"));
			if(in == null) {
				System.out.println("File not found in hadoop current dir.");
			} 
		}catch (Exception e) {
				e.printStackTrace();
				//JOptionPane.showMessageDialog(null, "标题【出错啦】", "年龄请输入数字", JOptionPane.ERROR_MESSAGE);
		}
        return in;
    }
  
    public String execute() throws Exception {  

        return SUCCESS;  
    }  
  
}  