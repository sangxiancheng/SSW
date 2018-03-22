package hadoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import java.io.ByteArrayOutputStream;

import com.bik.action.Files;

public class HdfsOp {
	public void createFile(String pathString) {
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(pathString);

		try {
			fileSystem=FileSystem.get(configuration);
			fileSystem.mkdirs(path);

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{

			try {

				fileSystem.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
	public void FileSize(long size,Files files){
		if(size>=1048576)
		{
			BigDecimal bg = new BigDecimal((double)size*1.0/(1024*1024));
			files.setSize(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+" MB");
		}
		else
		{
			BigDecimal bg = new BigDecimal((double)size*1.0/1024);
			files.setSize(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+" KB");
		}
	}
	public List<Files> showFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);

		return getFile(path,fs,list);
	}
	public List<Files> showAllFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);

		return getAllFile(path,fs,list);
	}
	public List<Files> getAllFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir()){
				Path p = new Path(fileStatus[i].getPath().toString());
				getAllFile(p,fs,list);

			}else{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				list.add(files);
			}
		}
		return list;
	}

	public List<Files> getFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		HttpServletRequest request1=ServletActionContext.getRequest();
		String filename,home=(String)request1.getSession().getAttribute("username"),location=path.toString().replaceAll("/user/", "");
		if(location.equals(home))
			location=location+"/";

		location=URLEncoder.encode(location,"UTF-8");
		Cookie cookie=new Cookie("location",location);
		cookie.setPath("/");
		ServletActionContext.getResponse().addCookie(cookie);
		location=URLDecoder.decode(location,"UTF-8");

		String prelocation=URLEncoder.encode(location.substring(0, location.lastIndexOf("/")),"UTF-8");
		Cookie cookie1=new Cookie("prelocation",prelocation);
		cookie1.setPath("/");
		ServletActionContext.getResponse().addCookie(cookie1);
		long size;
		if(fileStatus.length==0){
			Files files=new Files();
			files.setHome(home);
			files.setLocation(location);
			list.add(files);
		}
		else{
			for(int i=0;i<fileStatus.length;i++){
				filename=fileStatus[i].getPath().getName().toString();
				if(!fileStatus[i].isDir()){
					size=fileStatus[i].getLen();
					Files files=new Files();
					files.setFilename(filename);
					FileSize(size, files);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					files.setTime(sf.format(fileStatus[i].getModificationTime()));
					files.setLocation(location);
					files.setHome(home);
					System.out.println(home);
					System.out.println(location);
					System.out.println(fileStatus[i].getPath().toString());
					list.add(files);
				}
				else{
					Files files=new Files();

					files.setFilename(filename);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					files.setTime(sf.format(fileStatus[i].getModificationTime()));
					files.setSize("null");
					files.setLocation(location);
					files.setHome(home);
					System.out.println(home);
					System.out.println(location);
					System.out.println(fileStatus[i].getPath().toString());
					list.add(files);
				}
			}
		}
		return list;
	}
	public void createFile(Configuration conf, String filePath, byte[] contents) throws IOException {
		 FileSystem fs = FileSystem.get(conf);
		 Path path = new Path(filePath);
		 FSDataOutputStream outputStream = fs.create(path);
		 outputStream.write(contents);
		 outputStream.close();
		 fs.close();
	}
	public void createFile(Configuration conf, String filePath, String fileContent) throws IOException {
		 createFile(conf, filePath, fileContent.getBytes());
	}
	public boolean exits(Configuration conf, String path) throws IOException {//判断路径是否存在
		FileSystem fs = FileSystem.get(conf);
		return fs.exists(new Path(path));
	}
	public boolean createDirectory(Configuration conf, String dirName) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		Path dir = new Path(dirName);
		boolean result = fs.mkdirs(dir);
		fs.close();
		return result;
	}
	public void copyFromLocalFile(Configuration conf, String localFilePath, String remoteFilePath) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		Path localPath = new Path(localFilePath);
		Path remotePath = new Path(remoteFilePath);
		fs.copyFromLocalFile(false, true, localPath, remotePath);
		fs.close();
	}
	
	public String readFile(Configuration conf, String filePath) throws IOException {
		String fileContent = null;
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(filePath);
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try{
			inputStream = fs.open(path);
			outputStream = new ByteArrayOutputStream(inputStream.available());
			IOUtils.copyBytes(inputStream, outputStream, conf);
			fileContent = outputStream.toString();
		} finally {
			IOUtils.closeStream(inputStream);
            IOUtils.closeStream(outputStream);
            fs.close();
		}
            
            return fileContent;
	}
	public void copyFromLocalFile1(Configuration conf, String localFilePath, String remoteFilePath) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		Path localPath = new Path(localFilePath);
		Path remotePath = new Path(remoteFilePath);
		fs.copyFromLocalFile(false, true, localPath, remotePath);
		fs.close();
	}
	public String copyToHDFS(InputStream inputStream,String stringPath){

		org.apache.hadoop.conf.Configuration configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(stringPath);
		System.out.print(path);
		FSDataOutputStream outStream = null; 
		long between;
		try {	
			fileSystem=FileSystem.get(configuration);
			if(fileSystem == null) {
				System.out.println("fileSystem is null!");
				return "ERROR";
			}
			outStream = fileSystem.create(path);
			if(outStream == null) {
				System.out.println("out is null!");
				return "ERROR";
			}
			Date n1 = new Date();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date begin = sdFormat.parse(sdFormat.format(n1));
			Date n2 = new Date();
			Date end = sdFormat.parse(sdFormat.format(n2));
			between = (end.getTime()-begin.getTime());
			System.out.println("上传时间为"+between);
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			outStream.flush();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		finally
		{
			if(outStream!=null)
				try {
					outStream.close();
					fileSystem.close();
					return "SUCCESS";
				} catch (Exception e2) {
					e2.printStackTrace();
					return "ERROR";
				}

		}
		return "SUCCESS";
	}

	public String downLoadFile(String localPath,String HDFSPath) {

		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(HDFSPath);
		FSDataInputStream in=null;
		OutputStream out = null;
		try {
			fileSystem=FileSystem.get(configuration);
			in =  fileSystem.open(path);
			File file = new File(localPath);
			if(file.exists())
				return "reduplicate";
			else { 

				String dir = localPath.substring(0, localPath.lastIndexOf('\\'));
				file = new File(dir);
				if(!file.exists())
					file.mkdirs();	 
				file = new File(localPath);	 
				file.createNewFile();
				out = new FileOutputStream(localPath);

				byte[] buffer = new byte[1024];
				int length = 0;
				while((length = in.read(buffer))>0){
					out.write(buffer, 0, length);
				}
			} 

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		finally{
			if (out!=null) {
				try {
					out.close();
					in.close();
					fileSystem.close();
				} catch (IOException e) {

					e.printStackTrace();
					return "error";
				}
			}	
		}
		return "success";  
	}


	public String downLoad(String localPath,String HDFSPath) {

		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(HDFSPath);
		FSDataInputStream in=null;
		OutputStream out = null;
		try {
			fileSystem=FileSystem.get(configuration);
			in =  fileSystem.open(path);
			File file = new File(localPath);


			String dir = localPath.substring(0, localPath.lastIndexOf('\\'));
			file = new File(dir);
			if(!file.exists())
				file.mkdirs();	 
			file = new File(localPath);	 
			file.createNewFile();
			out = new FileOutputStream(localPath);

			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = in.read(buffer))>0){
				out.write(buffer, 0, length);
			}


		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		finally{
			if (out!=null) {
				try {
					out.close();
					in.close();
					fileSystem.close();
				} catch (IOException e) {

					e.printStackTrace();
					return "error";
				}
			}	
		}
		return "success";  
	}

	public String toLocalIOUtils(String HDFSPath,String localPath)throws Exception {

		Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(HDFSPath);
		int buffer=1024;

		File file = new File(localPath);
		FileOutputStream fo = new FileOutputStream(file);	   
		fileSystem=FileSystem.get(configuration);
		FSDataInputStream fi =fileSystem.open(path,buffer);
		long between = 0;

		Date n1 = new Date();

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date begin = sdFormat.parse(sdFormat.format(n1));
		IOUtils.copyBytes(fi, fo, configuration);
		Date n2 = new Date();

		Date end = sdFormat.parse(sdFormat.format(n2));

		between = (end.getTime()-begin.getTime());
		System.out.println(between);

		return "success";  

	}	
	public	void toLocalFileSystem(String src,String dst)
	{
		Configuration configuration= new Configuration();
		FileSystem fileSystem =null;
		try {

			fileSystem=FileSystem.get(configuration);

			Path HDFS = new Path(src);
			Path local = new Path(dst);
			long between = 0;
			Date n1 = new Date();

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date begin = sdFormat.parse(sdFormat.format(n1));

			fileSystem.copyToLocalFile(HDFS, local);

			Date n2 = new Date();

			Date end = sdFormat.parse(sdFormat.format(n2));

			between = (end.getTime()-begin.getTime());
			System.out.println(between);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void main(String[] args) throws Exception {
		HdfsOp cp = new HdfsOp();
		String myFileFileName="1.txt";
		String userName = "username";
		String localPath = "e:\\1.txt";	

		long between;

		Date n1 = new Date();

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date begin = sdFormat.parse(sdFormat.format(n1));

		InputStream inputStream = new FileInputStream(new File(localPath));
		cp.copyToHDFS(inputStream, userName);
		// cp.downLoad(localPath,HDFS);

		Date n2 = new Date();

		Date end = sdFormat.parse(sdFormat.format(n2));

		between = (end.getTime()-begin.getTime());

		System.out.println(between);

	}

}
