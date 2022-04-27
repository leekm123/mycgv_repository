<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/ins_mycgv/resources/js/jquery-3.6.0.min.js"></script>
<style>
	#upload {
		position:relative;
		/*border:1px solid red;*/
		left:89px;	top:0px;
		background-color:white;
		width:150px;
		display:inline-block;
	}
</style>
<script>
	$(document).ready(function(){
		$("input[type=file]").change(function(){
		
			if(window.FileReader){
				var fname =  $(this)[0].files[0].name;
				$("#upload").text(fname);
			}
		});		
		
	});
</script>
</head>
<body>
	<!--  header -->
	<jsp:include page="../../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board_write">
			<h1 class="title">관리자 - 공지사항</h1>
			<form name="notice_update" action="notice_update.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="nid" value="${vo.nid }">
				<input type="hidden" name="nsfile" value="${vo.nsfile }">				
				<input type="hidden" name="nfile" value="${vo.nfile }">	
							
				<table class="content_layout">
					<tr>
						<th>제목</th>
						<td><input type="text" name="ntitle" value="${vo.ntitle }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="ncontent">${vo.ncontent}</textarea></td>
					</tr>
					<tr>
						<th>파일</th>
						<td>
							<input type="file" name="file1">
							<span id="upload">${vo.nfile }</span>
						</td>
					</tr>
					<tr>					
						<td colspan="2">
							<button type="submit" class="btn_style2">수정완료</button>
							<button type="reset" class="btn_style2">취소</button>
							<a href="notice_content.do?nid=${vo.nid }&rno=${rno}"><button type="button" class="btn_style2">이전페이지</button></a>
							<a href="notice_list.do"><button type="button" class="btn_style2">리스트</button></a>
						</td>
					</tr>
				</table>
			</form>
		</section>
		
	</div>
	
	<!--  footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>