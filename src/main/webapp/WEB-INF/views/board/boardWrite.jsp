<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${view} Write</h1>
	
	<form action="${board}Write" method="POST">
	<table>
		<tr>
			
			<td>TITLE</td>
			<td>WRITER</td>
			<td>content</td>
		</tr>
		
		<tr>
			<td><input type="text" name="title"></td>
			<td><input type="text" name="writer"></td>	
			<td><textarea col="" row="" name="content"></textarea></td>
		</tr>
		
	</table>
		<button>submit</button>
	</form>

</body>
</html>