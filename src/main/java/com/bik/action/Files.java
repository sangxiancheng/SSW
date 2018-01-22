package com.bik.action;

public class Files {
	//filename��ʾ�ļ���
	private String filename;
	//�ļ���С
	private String size;
	//�޸�ʱ��
	private String time;
	//��ǰλ��
	private String location;
	//�û���Ŀ¼
	private String home;
	//���filenameֵ 
	public String getFilename() {  
		return filename;  
	}  
	//����filenameֵ  
	public void setFilename(String filename) {  
		this.filename = filename;  
	}

	public String getSize(){
		return size;
	}
	public void setSize(String size){
		this.size=size;
	}

	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}

	public String getHome() {  
		return home;  
	}  
	public void setHome(String home) {  
		this.home = home;  
	}

	public String getLocation() {  
		return location;  
	}  
	public void setLocation(String location) {  
		this.location = location;  
	}
}
