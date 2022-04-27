<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/ins_mycgv/resources/js/mycgv_javascript.js"></script>
</head>
<body>
	<!--  header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board_write">
			<h1 class="title">게시판</h1>
			<form name="board_write" action="board_write.do" method="post" enctype="multipart/form-data">
				<table class="content_layout">
					<tr>
						<th>제목</th>
						<td><input type="text" name="btitle" id="btitle"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="bcontent"></textarea></td>
					</tr>
					<tr>
						<th>파일</th>
						<td><input type="file" name="file1"></td>
					</tr>
					<tr>					
						<td colspan="2">
							<button type="button" class="btn_style2" onclick="boardFormCheck()">저장</button>
							<button type="reset" class="btn_style2">취소</button>
							<a href="board_list.do"><button type="button" class="btn_style2">리스트</button></a>
							<a href="http://localhost:9000/ins_mycgv/index.do"><button type="button" class="btn_style2">홈으로</button></a>
						</td>
					</tr>
				</table>
			</form>
		</section>
		
	</div>
	
	<!--  footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>