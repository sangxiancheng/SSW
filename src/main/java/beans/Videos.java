package beans;

import java.util.List;
import java.util.Map;
import beans.DBUtil;
public class Videos {
	private int VideoID;//视频ID
	private String VideoName;//视频名称
	private String VideoSize;//视频大小
	private String VideoLocation;//视频存放位置
	private String VideoComments;//视频信息
	private String VideoKeys;//视频关键字
	private String name;//上传者
	private DBUtil db;
	public Videos(){
		db = new DBUtil();
	}
	public int getVideoID(){
		return VideoID;
	}
	public void setVideoID(int VideoID){
		this.VideoID = VideoID;
	}
	public String getVideoName(){
		return VideoName;
	}
	public void setVideoName(String VideoName){
		this.VideoName=VideoName;
	}
	public String getVideoSize(){
		return VideoSize;
	}
	public void setVideoSize(String VideoSize){
		this.VideoSize=VideoSize;
	}
	public String getVideoLocation(){
		return VideoLocation;
	}
	public void setVideoLocation(String VideoLocation){
		this.VideoLocation=VideoLocation;
	}
	public String getVideoComments(){
		return VideoComments;
	}
	public void setVideoComments(String VideoComments){
		this.VideoComments=VideoComments;
	}
	public String getVideoKeys(){
		return VideoKeys;
	}
	public void setVideoKeys(String VideoKeys){
		this.VideoKeys=VideoKeys;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}

}
