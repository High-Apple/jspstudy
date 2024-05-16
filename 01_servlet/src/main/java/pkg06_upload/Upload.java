package pkg06_upload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
                  maxFileSize = 1024 * 1024 * 5,
                  maxRequestSize = 1024 * 1204 * 50)

//realpath 는 D:\HN\JAVAstudyjspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_servlet

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  //업로드 경로 (톰캣 내부 경로)
	  String uploadPath = request.getServletContext().getRealPath("upload");
	  // 프로젝트 시작 지점부터 끝까지 저장되어야 하는 값들이 servletContext에 저장된다.
	  File uploadDir = new File(uploadPath);
	  if(!uploadDir.exists()) {
	    uploadDir.mkdirs();
	  }
	  
	  String originalFilename = null;
	  String filesystemName = null;
	  
	  // 첨부된 파일 정보 
    //(((+Header : content-disposition 값을 통해 파일인지 아닌지 확인할 수 있다. 이걸 getPart로 알아온다
	  
	  Collection<Part> parts = request.getParts();
	  for(Part part : parts) {
	    //System.out.println(part.getName() + "," + part.getContentType() + "," + part.getSize() + "," + part.getSubmittedFileName()); //name 속성이 출력되는 걸 확인
	    //System.out.println(part.getHeader("Content-Disposition"));
	    if(part.getHeader("Content-Disposition").contains("filename")) {
	      if(part.getSize() > 0) {
	        originalFilename = part.getSubmittedFileName();
	      }
	    }
	    // 전송된 파일의 파일명 만들기.
      if(originalFilename != null) {
        int point = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(point);  //jpg
        String fileName = originalFilename.substring(0, point); //animal1
        filesystemName = fileName + "_" + System.currentTimeMillis() + extName;
      }
      // 파일시스템명이 null이 아니라면 저장하겠다(null이 같이 출력되니까)
      if(filesystemName != null) {
        part.write(uploadPath + File.separator + filesystemName); //File.separator 서버에 따라 경로구분자 다른거 알아서 처리해주겠다
      }
	  }
	   // 응답
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>"); //deployment assembly에서 본 그 경로
    out.println("<hr>");
    out.println("<div>첨부파일명 : " + originalFilename + "</div>");
    out.println("<div>저장파일명 : " + filesystemName +"</div>");
    out.println("<div>저장경로 : " + uploadPath + "</div>" );
    out.println("<hr>");
    

    File[] files = uploadDir.listFiles();
    for(File file : files) {
      String fileName1 = file.getName(); // 파일명_1234567890.jpg
      String ext = fileName1.substring(fileName1.lastIndexOf("."));
      String fileName2 = fileName1.substring(0, fileName1.lastIndexOf("_"));
      out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName1, "UTF-8") + "\">" + fileName2 + ext + "</a></div>");
    }
    out.flush();
    out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
