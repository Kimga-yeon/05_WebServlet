<%@page import="com.kh.wsp.notice.model.vo.Notice"%>
<%@page import="com.kh.wsp.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
/*댓글*/

.replyWrite>table {
	width: 90%;
	margin-top : 100px;
}

#replyContentArea {
	width: 90%;
}

#replyContentArea>textarea {
	resize: none;
	width: 100%;
}

#replyBtnArea {
	width: 100px;
	text-align: center;
}

.rWriter {
	display : inline-block;
	margin-right: 30px;
	vertical-align: top;
}

.rDate {
	display : inline-block;
	font-size: 0.5em;
	color: gray;
}

#replyListArea {
	list-style-type: none;
}

.rContent, .btnArea{
	display: inline-block;
	box-sizing: border-box;
}

.rContent {
	height: 100%;
	width : 84.5%;
}

.btnArea {
	height: 100%;
	width : 15%;
	text-align: right;
}


</style>
<div id="reply-area ">

	<!-- 댓글 작성 부분 -->
	<div class="replyWrite">
		<table align="center">
			<tr>
				<td id="replyContentArea"><textArea rows="3" id="replyContent"></textArea>
				</td>
				<td id="replyBtnArea">
					<button class="btn btn-primary" id="addReply">댓글<br>등록</button>
				</td>
			</tr>
		</table>
	</div>


	<!-- 댓글 출력 부분 -->
	<div class="replyList mt-5 pt-2">
		<ul id="replyListArea">
			 <li class="reply-row" id="1">
				<div>
					<p class="rWriter">작성자</p> 
					<p class="rDate">
						작성일  : 2020년 07월 14일 15:12:20<br>
						마지막 수정 날짜 : 2020년 07월 15일 16:17:51
					</p>
				</div>
				
				<p class="rContent">
					Web Server Project 댓글 기능 구현중입니다.<br>
					댓글이 쫌 어려워요 ㅠㅠ
				</p>
				<div class="btnArea">
					<button class="btn btn-primary" id="updateReply">수정</button>
					<button class="btn btn-primary" id="deleteReply">삭제</button>
				</div>
			</li>
			<hr> 
		</ul>
	</div>

	
</div>

<script>
<%
	// notice, loginMember를 정상적으로 사용할 수 있으나
	// 이클립스 에러 때문에 임시 변수를 사용
	Member tempMember = (Member)session.getAttribute("loginMember");
	Notice tempNotice = (Notice)request.getAttribute("notice");
%>

// 로그인이 되어있는 경우 회원 아이디를 별도 저장해둠.
var loginMemberId;
<% if(tempMember != null){%>
loginMemberId = "<%= tempMember.getMemberId()%>";
	
<%}%>


//-----------------------------------------------------------------------------------------
// 페이지 로딩 완료 시 댓글 목록 호출
$(function(){
	selectReplyList(<%=tempNotice.getNoticeNo()%>);
});

//-----------------------------------------------------------------------------------------
// 댓글 목록 불러오기
function selectReplyList(boardNo){
	var url = "<%=request.getContextPath()%>/reply/selectList.do";
	
	console.log(boardNo);
	
	$.ajax({
		url : url,
		data : {"boardNo" : boardNo},
		dataType : "JSON" , 
		success : function(rList){
			console.log(rList);
			
			// 댓글 목록이 추가되는 영역을 초기화함.
			$("#replyListArea").html("");
			
			
			$.each(rList, function(i){
			
			// 댓글 하나를 감싸고 있는 li 태그 생성
			var $li = $("<li>").addClass("reply-row");
			
			// 작성자, 작성일, 수정일 영역 div 생성
			var $div = $("<div>");
			
				// 작성자
				var $rWriter = $("<a>").addClass("rWriter idSelect").html(rList[i].memberId);
				
				// 작성일, 수정일
				var $rDate = $("<p>").addClass("rDate")
							.html("작성일 : " + rList[i].replyCreateDate + "<br>"
									+ "마지막 수정일 : " + rList[i].replyModifyDate);
				
				// 작성자, 작성일, 수정일을 div에 추가하기
				$div.append($rWriter, $rDate);
				
			
			
			// 댓글 내용 영역 p태그 생성
			var $rContent = $("<p>").addClass("rContent").html(rList[i].replyContent);
			
			// 댓글 수정, 삭제 버튼 영역
			var $btnArea = $("<div>").addClass("btnArea");
			
			// 현재 댓글에 작성자와 로그인한 멤버의 아이디가 같을 경우 버튼을 추가
			if(rList[i].memberId == loginMemberId){
				
				// *** 추가되는 버튼에 onclick 이벤트를 부여하여
				// 클릭 시 수정, 삭제가 되는 함수를 호출하게 만듦.
				var $showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick","showUpdateReply(this, "+rList[i].replyNo+")");
				var $deleteReply = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick","deleteReply("+rList[i].replyNo+")");
				
				$btnArea.append($showUpdate, $deleteReply);
				
			}
			
			// 댓글 경계선
			var $hr = $("<hr>");
			
			// $li에 $div, $rContent , $btnArea 추가하기
			$li.append($div, $rContent , $btnArea);
			
			// 댓글을 화면에 배치
			$("#replyListArea").append($li, $hr);
			
		});
			
			
		},error : function(){
			console.log("ajax 통신 실패");
		}
	});
}


//-----------------------------------------------------------------------------------------
// 댓글 등록

$("#addReply").on("click", function(){
	
	// 로그인이 되어있는지 확인 
	<% if(tempMember == null){ %>
		alert("로그인 후 이용해 주세요.");
	<% }else{ %>
		
		// 작성된 댓글 내용 얻어옴.
		var replyContent = $("#replyContent").val();
		
		// 댓글이 작성 되어 있는지 유효성 검사 진행
		if(replyContent.trim().length == 0){
			alert("댓글 작성 후 클릭해주세요.");
			$("#replyContent").focus();
			
		}else{
			// 로그인도 되어있고, 댓글도 작성되어 있는 경우 
			var url = "<%=request.getContextPath() %>/reply/insertReply.do";
			
			// 작성자 : 전역변수 loginMemberId 사용
			// 글번호 
			var parentBoardNo = "<%=tempNotice.getNoticeNo() %>";
			
			
			$.ajax({
				url : url,
				type : "POST",
				data : {"memberId" : loginMemberId, 
						"parentBoardNo" : parentBoardNo, 
					    "replyContent" : replyContent},
				success : function(result){ 
					alert(result);
					$("#replyContent").val("");
					// 삽입된 내용을 화면에서 지움 
			
					// 갱신된 DB 내용을 다시 조회하여 화면 댓글 목록을 갱신함.
					selectReplyList(parentBoardNo);
				}, error : function(){	    
				   console.log("ajax 통신 실패");
				   }
			});
		}
	
	<% } %>
	
});


//-----------------------------------------------------------------------------------------
// 댓글 삭제

function deleteReply(replyNo){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var url = "<%=request.getContextPath()%>/reply/deleteReply.do";
		
		$.ajax({ 
			url :  url,
			type : "GET",
			data :  {"replyNo" : replyNo},
			success : function(result){
				alert(result);
				
				// 댓글 목록 갱신 
				selectReplyList(/*게시글 번호*/<%=tempNotice.getNoticeNo()%>);
				
			},
			error : function(){
				console.log("ajax 통신 실패");
			}
			
			
		});
	}
}



</script>