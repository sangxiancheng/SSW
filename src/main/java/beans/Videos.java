package beans;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import beans.DBUtil;
import hadoop.HdfsOp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.InputStream;
public class Videos {
	private String videoid;//视频ID
	private String videoname;//视频名称
	private float videosize;//视频大小
	private String videolocation;//视频存放位置
	private String videocomments;//视频信息
	private String videokeys;//视频关键字
	private String name;//上传者
	private DBUtil db;
	public Videos(){
		db = new DBUtil();
	}
	public String getVideoid(){
		return videoid;
	}
	public void setVideoid(String videoid){
		this.videoid = videoid;
	}
	public String getVideoname(){
		return videoname;
	}
	public void setVideoname(String videoname){
		this.videoname=videoname;
	}
	public float getVideosize(){
		return videosize;
	}
	public void setVideosize(float videosize){
		this.videosize=videosize;
	}
	public String getVideolocation(){
		return videolocation;
	}
	public void setVideolocation(String videolocation){
		this.videolocation=videolocation;
	}
	public String getVideocomments(){
		return videocomments;
	}
	public void setVideocomments(String videocomments){
		this.videocomments=videocomments;
	}
	public String getVideokeys(){
		return videokeys;
	}
	public void setVideokeys(String videokeys){
		this.videokeys=videokeys;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	//添加video信息
	public int addVideo(){
		int result = 0;
		String size=String.valueOf(videosize);
		String sql = "insert into videotable(VideoName,VideoSize,VideoLocation,VideoComments,VideoKeys,VideoDate,Name) values(?,?,?,?,?,now(),?)";
		String[] params = {videoname,size,videolocation,videocomments,videokeys,name};
		result = db.update(sql, params);
		return result;
	}
	//获取所有video信息
	public List getAllVideos(){
		List videos = null;
		String sql = "select VideoID,VideoName,VideoSize,VideoLocation,VideoComments,VideoKeys,date(VideoDate),Name from videotable order by date(VideoDate) DESC";
		videos = db.getList(sql, null);
		return videos;
	}
	//通过上传者查询videos信息
	public List getVideosByName(){
		List videos = null;
		String sql = "select VideoID,VideoName,VideoSize,VideoLocation,VideoComments,VideoKeys,date(VideoDate),Name from videotable where Name=? order by date(VideoDate) DESC";
		String[] params={name};
		videos = db.getList(sql, params);
		return videos;
	}
	//通过VideoID查询videos信息
	public Map getVideoByVideoID(){
		Map videos = null;
		String sql = "select VideoID,VideoName,VideoSize,VideoLocation,VideoComments,VideoKeys,date(VideoDate),Name from videotable where VideoID=?";
		//System.out.print(videoid);
		String[] params={videoid};
		videos = db.getMap(sql, params);
		return videos;
	}
	//通过VideoName查询videos信息
	public List getVideosByVideoName(){
		List videos = null;
		String sql = "select VideoID,VideoName,VideoSize,VideoLocation,VideoComments,VideoKeys,date(VideoDate),Name from videotable where VideoName like concat('%',?,'%') order by date(VideoDate) DESC";
		String[] params={videoname};
		videos = db.getList(sql, params);
		return videos;
	}
	//通过VideoKeys查询videos信息
	public List getVideosByVideoKeys(){
		List videos = null;
		String sql = "select VideoID,VideoName,VideoSize,VideoLocation,VideoComments,VideoKeys,date(VideoDate),Name from videotable where VideoKeys like concat('%',?,'%') order by date(VideoDate) DESC";
		String[] params={videokeys};
		videos = db.getList(sql, params);
		return videos;
	}
	
	//通过VideoID修改video信息
	public int updateVideo(){
		int result = 0;
		String size=String.valueOf(videosize);
		String sql = "update videotable set VideoName=?,VideoSize=?,VideoLocation=?,VideoComments=?,VideoKeys=?,VideoDate=now(),Name=? where VideoID=?";
		String[] params = {videoname,size,videolocation,videocomments,videokeys,name,videoid};
		result = db.update(sql, params);
		return result;
	}
	//删除video信息
	public boolean delVideo(){
		
		Boolean result2=true;
		
		//HdfsOp hdfsOp = new HdfsOp();
		org.apache.hadoop.conf.Configuration configuration= new Configuration();
		FileSystem fileSystem =null;
        //org.apache.hadoop.conf.Configuration conf = new Configuration();
        System.out.println(videoname);
        Path path=new Path("/ssw/"+name+"/"+videoname);
        System.out.println(path);
        try{
        	fileSystem=FileSystem.get(configuration);
        	result2 = fileSystem.delete(path,true);
        	System.out.println(result2);
        }catch(Exception e){
        	e.printStackTrace();
        	result2=false;
        }
        String sql = "delete from videotable where VideoID=?";
		String[] params = {videoid};
		int result1 = db.update(sql, params);
		System.out.println(result1);
		if(result1==1&&result2==true){
			return true;
		}else
			return false;
	} 

}
