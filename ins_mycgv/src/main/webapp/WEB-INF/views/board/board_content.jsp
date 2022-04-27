<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
</head>
<body>
	<!--  header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board_content">
			<h1 class="title">게시판</h1>
			<table class="content_layout">
				<tr>
					<th>번호</th>
					<td>${rno}</td>
					<th>날짜</th>
					<td>${vo.bdate }</td>
					<th>조회수</th>
					<td>${vo.bhits }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5">${vo.btitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5">
						${vo.bcontent }<br><br><br>
						<c:if test="${vo.bsfile != null}">
							<img src="http://localhost:9000/ins_mycgv/resources/upload/${vo.bsfile}"
							     width="100px" height="100px">
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<a href="board_update.do?bid=${vo.bid}&rno=${rno}"><button type="button" class="btn_style2">수정</button></a>
						<a href="board_delete.do?bid=${vo.bid}&rno=${rno}"><button type="button" class="btn_style2">삭제</button></a>
						<a href="board_list.do"><button type="button" class="btn_style2">리스트</button></a>
						<a href="http://localhost:9000/ins_mycgv/index.do"><button type="button" class="btn_style2">홈으로</button></a>
					</td>
				</tr>
			</table>
		</section>
		
	</div>
	
	<!--  footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>