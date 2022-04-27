$(document).ready(function(){

	/***************
	 * 로그인 - 폼체크
	 * @returns
	 */
	$("#btnLogin").click(function(){
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}else if($("#pass").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#pass").focus();
			return false;
		}else{
			login_form.submit();
		}
	});
	
	
	/**************
	 * 회원가입 - 폼체크
	 ************/
	$("#btnJoin").click(function(){
		
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}else if($("#pass").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#pass").focus();
			return false;
		}else if($("#cpass").val() == ""){
			alert("패스워드 확인을 입력해주세요");
			$("#cpass").focus();
			return false;
		}else if($("#name").val() ==""){
			alert("이름을 입력해주세요");
			$("#name").focus();
			return false;
		}else if($("input[name='gender']:checked").length == 0){
			alert("성별을 입력해주세요");
			return false;
		}else if($("#email1").val() == ""){
			alert("이메일을 입력해주세요");
			$("#email1").focus();
			return false;
		}else if($("#email2").val() == ""){
			alert("이메일주소를 선택해주세요");
			$("#email3").focus();
			return false;
		}else if($("#hp1").val() == "choice"){
			alert("핸드폰 번호를 선택해주세요");
			$("#hp1").focus();
			return false;
		}else if($("#hp2").val() == ""){
			alert("핸드폰 번호를 선택해주세요");
			$("#hp2").focus();
			return false;
		}else if($("#hp3").val() == ""){
			alert("핸드폰 번호를 선택해주세요");
			$("#hp3").focus();
			return false;
		}else if($("input[name='hobby']:checked").length == 0){
			alert("취미를 선택해주세요");
			$("input[name='hobby']:first").focus();
			return false;
		}else if($("#intro").val() == ""){
			alert("자기소개를 입력해주세요");
			$("#intro").focus();
			return false;
		}else{
			join_form.submit();
		}			
	});

	
	
	/************************
	  회원가입폼 - 이메일 주소 선택 
	*************************/
	function emailCheck(){
		var emailAddr = document.getElementById("email3").value;
		
		if(emailAddr == "choice"){
			alert("다른 주소를 선택해주세요");
			document.getElementById("email2").value = "";
		}else{
			document.getElementById("email2").value = emailAddr;	
			
		}
	}
	
	/********************************
	  회원가입폼 - 패스워드, 패스워드확인 비교
	*********************************/
	function passCheck(){
		var pass = document.getElementById("pass");
		var cpass = document.getElementById("cpass");
		var msg = document.getElementById("msg");
		msg.style.fontSize ="13px";
				
		if(pass.value != "" && cpass.value != ""){
			if(pass.value == cpass.value){
				msg.innerHTML = "패스워드가 동일합니다";
				msg.style.color ="blue";
				return true;
			}else{
				msg.innerHTML = "패스워드가 동일하지 않습니다";
				msg.style.color ="red";
				pass.value = "";
				cpass.value = "";
				pass.focus();
				return false;
			}
		}
		
	}
	
	/*******************
	 * 게시판 - 글쓰기 폼체크
	 ********************/
	function boardFormCheck(){
		var btitle = document.getElementById("btitle");
		
		if(btitle.value ==""){
			alert("제목을 입력해주세요");
			btitle.focus();
			return false
		}else{
			board_write.submit();
		}
	}
	
	
	
	/*******************
	 * 공지사항 - 글쓰기 폼체크
	 ********************/
	function noticeFormCheck(){
		var ntitle = document.getElementById("ntitle");
		
		if(ntitle.value == ""){
			alert("제목을 입력해주세요");
			ntitle.focus();
			return false;
		}else{
			notice_write.submit();
		}
	}


}); //ready function


















