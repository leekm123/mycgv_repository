<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
<script>
	var login_result = "${login_result}";
	if(login_result == "succ"){
		alert("로그인에 성공하셨습니다.");
	}
</script>
</head>
<body>
		
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>

	
	<!-- content -->
	<div class="carousel">
		<section>
			<img src="http://localhost:9000/ins_mycgv/resources/images/carousel.jpg">
		</section>
	</div>
	<div class="content">
		<section>
			<div><img src="http://localhost:9000/ins_mycgv/resources/images/h3_movie_selection.gif"></div>
			<article>
				<iframe width="740" height="388" src="https://www.youtube.com/embed/bQm0_3lgfvo" 
				title="YouTube video player" frameborder="0"
				allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
				allowfullscreen></iframe>
				<img src="http://localhost:9000/ins_mycgv/resources/images/240x388.jpg">
			</article>			
		</section>
		<section>
			<div><img src="http://localhost:9000/ins_mycgv/resources/images/h3_event.gif"></div>
			<article>
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_1.jpg">
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_2.jpg">
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_3.jpg">
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_4.jpg">
			</article>
			<article>
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_5.jpg">
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_6.jpg">
				<img src="http://localhost:9000/ins_mycgv/resources/images/img_7.png">
			</article>
			<article>
				<!-- 공지사항 -->
			</article>
		</section>		
	</div>
	
	<!--  footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>