<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

		$(function(){
			
			var count=0;
			var index=0;
			$("#add").click(function(){
				if(count <5){
					var s = '<div id="d'+index+'">';
						s= s+'<input type="file" name="f1"><span class="del" title="d'+index+'">X</span>';
						$("#result").append(s);
						count++;
						index++;
				}else{
					alert("fail");
				}
			});
			
			$("#result").on("click",".del",function(){
				var id =$(this).attr("title");
				$("#"+id).remove();
				count--;
				index--;
			});
			
		});

</script>

</head>
<body>
	<h1>${board} Write</h1>

	<input type="hidden" id="board" value="${board}">
	<form action="${board}Write" method="POST" enctype="mulitpart/form-data">
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
	
	<input type="button" value="File Add" id="add">
	<div id="result"></div>
		<input type="submit" name="submit" value="Submit">
	</form>

</body>
</html>