<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/ins_mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		
		//회원탈퇴 신청/취소 처리
		$("#join_status").click(function(){
			
			//var bname = $("#join_status").text();
			var status = 0;
			if($("#join_status").text() == "취소"){  //신청:0, 취소:1
				status = 1;
			}	
			
			if(confirm("정말로 회원탈퇴를 신청/취소 하시겠습니까?")){
				//cgv_member 테이블에서 test라는 계정의 join_status 값을 1로 수정
				//url---> join_status.do
				$.ajax({
					url:"join_status.do?id=hong&status="+status, //test,1
					success : function(result){
						if(result ==1){
							alert("처리가 완료되었습니다");
							if(status == 0){
								$("#join_status").text("취소");
							}else{
								$("#join_status").text("신청");
							}
						}
					}
				});	
			}
			
		});
		
	});
</script>
</head>
<body>
	<!--  header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="member_content">
			<h1 class="title">마이페이지</h1>
			<table class="content_layout">
				<tr>					
					<th style="width:150px">아이디</th>
					<td style="width:100px">${vo.id }</td>
					<th style="width:100px">성명</th>
					<td>${vo.name }</td>
					<th style="width:100px">성별</th>
					<td>${vo.gender }</td>
				</tr>
				<tr>
					<th>취미</th>
					<td colspan="5">${vo.hobbylist }</td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td colspan="5">${vo.hp }</td>
				</tr>
				<tr>
					<th>회원탈퇴신청</th>
					<td colspan="5">
						<c:choose>
							<c:when test="${vo.join_status == 0 }">
								<button type="button" id="join_status">신청</button>
							</c:when>
							<c:otherwise>
								<button type="button" id="join_status">취소</button>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="6">					
						<a href="#"><button type="button" class="btn_style2">정보수정하기</button></a>					
					</td>
				</tr>
			</table>
		</section>
		
	</div>
	
	<!--  footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>