<%@page import="domain.items.ItemsDao"%>
<%@page import="domain.items.dto.SaveReqDto"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String saveDirectory = application.getRealPath("/item/img");
	int maxPostSize = 1024*10000*5; //10kb
	out.println(saveDirectory);
	/* 실제 저장하고 싶은 위치가 아니라 다른곳에있는  uploading 이 찍힘. */
		// saveDirectory가 찍히는데 모르겠음.
	MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize,"UTF-8");
	String fileName = mr.getFilesystemName("ofile");
	String exe = fileName.substring(fileName.lastIndexOf("."));
	String now = new SimpleDateFormat("yyyyMMdd_Hmss").format(new Date());
	String newFilename = now+exe;
	File oldFile = new File(saveDirectory+File.separator+fileName);
	File newFile = new File(saveDirectory+File.separator+newFilename);
	oldFile.renameTo(newFile);
	String userId = mr.getParameter("userId");
	String title = mr.getParameter("title");
	String scontent = mr.getParameter("scontent");
	String lcontent = mr.getParameter("lcontent");
	int price = Integer.parseInt(mr.getParameter("price"));
	int quantity = Integer.parseInt(mr.getParameter("quantity"));
	SaveReqDto dto = new SaveReqDto();
	dto.setId(userId);
	dto.setTitle(title);
	dto.setScontent(scontent);
	dto.setLcontent(lcontent);
	dto.setQuantity(quantity);
	dto.setOfile(newFilename);
	dto.setPrice(price);
	ItemsDao dao = new ItemsDao();
	int result = dao.insert(dto);
	response.sendRedirect("items?cmd=itemslist");
	
			

%>