
<%@page import="com.kh.wsp.notice.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

	

<%
	List<Notice> list = (List<Notice>)request.getAttribute("list");
%> 	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
    <style>
    	.pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
	</style>
	
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="container">
    <div class="my-5">
    	<h1>공지사항</h1>
      	<table class="table table-hover table-striped my-5" id="list-table">
          <thead>
            <tr>
                <th>글번호 </th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일</th>
            </tr>
           </thead>
            
					<tbody>
						<!-- 
						공지사항 글이 존재하지 않을 수도 있다.  
						list는 DAO에서 무조건 생성되어 반환이 된다.
						일치하는 조건의 데이터가 없으면 [], 있으면 내용을 포함하여 반환한다.
						따라서 null이냐 아니냐의 조건이 아니라 비었는지 안비었는지의 조건으로 해야한다.			
						-->
						<% if(list.isEmpty()){ %>
							<tr>
								<td colspan="5">존재하는 공지사항이 없습니다.</td>
							</tr>
						<% }else{ %>
							<% for(Notice notice : list ){ %>
								<tr>
								  <td><%=notice.getNoticeNo() %></td>
								  <td>
								  <%=notice.getNoticeTitle() %>
								  [<%=notice.getReplyCount() %>]
								  </td>
								  
								  <td><%=notice.getMemberId() %></td>
								  <td><%=notice.getReadCount() %></td>
								  <td><%=notice.getNoticeModifyDate() %></td>
							<% } %>
						<% } %>
            </tbody>
          </table>
       </div>
       
       <%-- 로그인 된 계정이 관리자 등급일 경우에만 "글쓰기" 버튼 노출 --%>
       <% if(loginMember != null && loginMember.getMemberGrade().equals("A")) {  %>
	   <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm.do';">글쓰기</button>
       <% } %>
	        
       <div class="my-5" style="clear: both;">
           <ul class="pagination">
               <li>
                   <a class="page-link" href="#">&lt;</a>
               </li>
               <li>
                   <a class="page-link" href="#">1</a>
               </li>
               <li>
                   <a class="page-link" href="#">2</a>
               </li>
               <li>
                   <a class="page-link" href="#">3</a>
               </li>
               <li>
                   <a class="page-link" href="#">4</a>
               </li>
               <li>
                   <a class="page-link" href="#">5</a>
               </li>
               <li>
                   <a class="page-link" href="#">&gt;</a>
               </li>
           </ul>
       </div>
       
       <div class="mb-5">
          <form action="search" method="GET" class="text-center" id="searchForm">
              <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
                  <!-- <option value="title" selected>글제목</option> -->
                  <option value="title">글제목</option>
                  <option value="content">내용</option>
                  <option value="titcont">제목+내용</option>
              </select>
              <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
              <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
          </form>
       </div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	
	<script>
		// 공지사항 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				var noticeNo = $(this).parent().children().eq(0).text();
				
				// 쿼리스트링을 이용하여 get방식으로 글번호를 전달
				location.href = "<%=request.getContextPath()%>/notice/view.do?no=" + noticeNo;
				
				
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");

			
			});
			
		});
		
		
	</script>
	
	
</body>
</html>
