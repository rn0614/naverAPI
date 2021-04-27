<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>도서목록</title>
		<style>
			#wrap {margin:0 auto; text-align:center;}
			table {text-align:left;}
		</style>
		
	</head>
	<body>
		<div id="wrap">
			<form action ="bookList">
				<input type="text" name="keyword">
				<input type="submit" name="검색">
			</form>
		<hr>
		<table width="100%">
			<c:forEach items="${bookList }" var="bk">
			<tr><td rowspan="4"><img src="${bk.image }"></td>
				   <td colspan="3" width="800">${bk.title }</td></tr>
			<tr><td>${bk.author }</td><td>${bk.publisher }</td><td>${bk.pubdate }</td></tr>
			<tr><td>${bk.isbn }</td><td>${bk.price }</td><td>${bk.discount }</td></tr>
			<tr><td colspan="3" width="800">"${bk.description }"</td></tr>			
			<tr><td colspan="4" bgColor="red" width="100%"></td></tr>	
			</c:forEach>
		
		</table>
		</div>
	</body>
</html>