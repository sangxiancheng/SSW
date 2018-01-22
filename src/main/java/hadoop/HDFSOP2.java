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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.FileStatus;
import org.apache.struts2.ServletActionContext;

import com.bik.action.Files;

public class HDFSOP2 {
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

	public void createFile(String pathString) {
		org.apache.hadoop.conf.Configuration  configuration=
				new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(pathString);//璁剧疆鏂板缓鏂囦欢澶圭殑璺緞

		try {
			fileSystem=FileSystem.get(configuration);
			fileSystem.mkdirs(path);//鏂板缓涓�涓枃浠跺す锛宮kdirs鏄疕DFS鏈韩鎻愪緵鐨勬柟娉�

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

	public List<Files> showFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);

		return getFile(path,fs,list);
	}

	public List<Files> getFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		//HttpServletRequest request=ServletActionContext.getRequest();
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
		//request.setAttribute("location", location);
		//String username=(String)request1.getSession().getAttribute("username");
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

	public void renameFile(String src,String location,String dst) throws IOException{
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path srcPath=new Path("/user/"+location+"/"+src);
		Path dstPath=new Path("/user/"+location+"/"+dst);
		System.out.println(srcPath+"@@@@@@@@@"+dstPath);
		boolean flag=false;
		try{
			fs.rename(srcPath,dstPath);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();  
		}finally{
			fs.close();
			System.out.println(flag);
		}
	}

	public List<Files> showSearchedFile(String pathString,String filename) 
			throws IOException {
		List<Files> list=new ArrayList<Files>();//鐢ㄤ簬瀛樻斁鏂囦欢淇℃伅鐨刲ist
		org.apache.hadoop.conf.Configuration  configuration=
				new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);//璁剧疆鏂囦欢璺緞
		return getSearchedFile(path,fs,filename,list);//鏂规硶璋冪敤
	}

	public List<Files> getSearchedFile(Path path,FileSystem fs,
			String filename,List<Files> list) 
					throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			System.out.println(filename);
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(0,t.lastIndexOf("."));//鍘婚櫎鏂囦欢鍚庣紑鍚嶏紝浠呰緭鍏ユ枃浠跺悕鍗冲彲鎼滅储
			if(s1.contains(filename)||t.contains(filename))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");//鏍煎紡鍖栨棩鏈�
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				list.add(files);

			}
			else {
				if(fileStatus[i].isDir())//濡傛灉鏄枃浠跺す锛屽垯閫掑綊璋冪敤鏈柟娉曪紝杩涘叆鏂囦欢澶圭户缁悳绱�
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getSearchedFile(p,fs,filename,list);
				}
			}
		}
		return list;
	}

	public List<Files> showDocFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>(); 
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getDocFile(path,fs,list);

	}

	public List<Files> getDocFile(Path path,FileSystem fs,List<Files> list) 
			throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));	
			if(s1.equals(".doc")||s1.equals(".docx"))//妫�娴嬫枃浠跺悗缂�鍚�
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");//鏍煎紡鍖栨棩鏈�
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				list.add(files);//灏嗘枃浠朵俊鎭姞鍏ist涓�

			}
			else {
				if(fileStatus[i].isDir())//鑻ユ槸鏂囦欢澶癸紝鍒欓�掑綊璋冪敤
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getDocFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showImageFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getImageFile(path,fs,list);

	}

	public List<Files> getImageFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".jpg")||s1.equals(".jpeg")||s1.equals(".png")||s1.equals(".gif")||s1.equals(".bmp"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getImageFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showPdfFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getPdfFile(path,fs,list);

	}

	public List<Files> getPdfFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".pdf"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getPdfFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showPptFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getPptFile(path,fs,list);
	}

	public List<Files> getPptFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".ppt")||s1.equals(".jpeg")||s1.equals(".pptx"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getPptFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showVedioFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getVedioFile(path,fs,list);

	}

	public List<Files> getVedioFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".avi")||s1.equals(".mp4")||s1.equals(".wmv")||s1.equals(".mpg")||s1.equals(".mkv")||s1.equals(".rmvb")||s1.equals(".mov")||s1.equals(".mpeg")||s1.equals(".flv"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getVedioFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showAudioFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getAudioFile(path,fs,list);
	}

	public List<Files> getAudioFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".mp3")||s1.equals(".wav")||s1.equals(".ape")||s1.equals(".flac")||s1.equals(".wma")||s1.equals(".acc")||s1.equals(".ogg"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getAudioFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showRarFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getRarFile(path,fs,list);

	}

	public List<Files> getRarFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".rar")||s1.equals(".zip")||s1.equals(".7z")||s1.equals(".iso")||s1.equals(".tar")||s1.equals(".gz")||s1.equals(".rpm"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getRarFile(p,fs,list);
				}
			}
		}
		return list;
	}

	public List<Files> showCodeFile(String pathString) throws IOException {
		List<Files> list=new ArrayList<Files>();
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Path path=new Path(pathString);
		return getCodeFile(path,fs,list);
	}

	public List<Files> getCodeFile(Path path,FileSystem fs,List<Files> list) throws IOException {
		FileStatus[] fileStatus = fs.listStatus(path);
		String s1;
		long size;
		for(int i=0;i<fileStatus.length;i++){
			String t=fileStatus[i].getPath().getName().toString();
			size=fileStatus[i].getLen();
			if(fileStatus[i].isDir())
				s1="0";
			else
				s1=t.substring(t.lastIndexOf("."));
			if(s1.equals(".c")||s1.equals(".cpp")||s1.equals(".java")||s1.equals(".cs")||s1.equals(".py")||s1.equals(".jsp")||s1.equals(".asp")||s1.equals(".aspx")||s1.equals(".php")||s1.equals(".html"))
			{
				Files files=new Files();
				files.setFilename(t);
				FileSize(size, files);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				files.setTime(sf.format(fileStatus[i].getModificationTime()));
				System.out.println(t);
				list.add(files);
			}
			else {
				if(fileStatus[i].isDir())
				{
					Path p = new Path(fileStatus[i].getPath().toString());
					getCodeFile(p,fs,list);
				}
			}
		}
		return list;
	}


	public void deleteFile(String pathString) {
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
		Path path=new Path(pathString);//璁剧疆瑕佸垹闄ょ殑鏂囦欢鐨勮矾寰�

		try {
			fileSystem=FileSystem.get(configuration);
			fileSystem.delete(path,true);//鍒犻櫎鏂囦欢

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fileSystem.close();//鍏抽棴FileSystem
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/*		
	public void lToH(String input,String output) {
		org.apache.hadoop.conf.Configuration  configuration= new Configuration();
		FileSystem fileSystem =null;
	    Path inpath=new Path(input);
	    Path outpath=new Path(output);
	    long between;
	    try {
	    	fileSystem=FileSystem.get(configuration);
	    	  Date n1 = new Date();

				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date begin = sdFormat.parse(sdFormat.format(n1));
	    	fileSystem.copyFromLocalFile(inpath, outpath);
                      Date n2 = new Date();

			Date end = sdFormat.parse(sdFormat.format(n2));

			between = (end.getTime()-begin.getTime());

			System.out.println(between);


		} catch (Exception e) {

		}

	}
	 */

	public void copyToHDFS(InputStream inputStream,String pathString){

		org.apache.hadoop.conf.Configuration  configuration= new Configuration();

		FileSystem fileSystem =null;
		Path path=new Path(pathString);
		FSDataOutputStream outStream = null; 
		long between;

		try {	
			fileSystem=FileSystem.get(configuration);
			if(fileSystem == null) {
				System.out.println("fileSystem is null!");
				return;
			}

			outStream = fileSystem.create(path);
			if(outStream == null) {
				System.out.println("out is null!");
				return;
			}

			Date n1 = new Date();

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date begin = sdFormat.parse(sdFormat.format(n1));

			//IOUtils.copyBytes(inputStream, outStream, configuration);

			Date n2 = new Date();

			Date end = sdFormat.parse(sdFormat.format(n2));

			between = (end.getTime()-begin.getTime());

			System.out.println(between);

			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			outStream.flush();
			inputStream.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			if(outStream!=null)
				try {
					outStream.close();
					fileSystem.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

		}

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
		String HDFSPath = "1.txt";
		String localPath = "e:\\1.txt";	

		long between;

		Date n1 = new Date();

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date begin = sdFormat.parse(sdFormat.format(n1));

		InputStream inputStream = new FileInputStream(new File(localPath));
		cp.copyToHDFS(inputStream, HDFSPath);
		// cp.downLoad(localPath,HDFS);

		Date n2 = new Date();

		Date end = sdFormat.parse(sdFormat.format(n2));

		between = (end.getTime()-begin.getTime());

		System.out.println(between);

	}

}
