package com.kh.wsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.wsp.board.model.service.BoardService;
import com.kh.wsp.board.model.vo.Attachment;
import com.kh.wsp.board.model.vo.Board;
import com.kh.wsp.board.model.vo.PageInfo;
import com.kh.wsp.common.MyFileRenamePolicy;
import com.kh.wsp.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

import sun.awt.RepaintArea;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		// http://localhost:8080/wsp/notice/list.do
		
		String contextPath = request.getContextPath();
		// http://localhost:8080/wsp
		
		String command = uri.substring((contextPath + "/board").length());
		
		// forword용 변수 
		String path = null;
		RequestDispatcher view = null;
		
		
		
		// 메세지 전달용 변수 
		String status = null; 
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		try {
			BoardService boardService = new BoardService();
			
			// 게시판은 기본적으로 게시판 타입(종류), 현재 페이지 정보를 유지 
			// 게시판 타입을 쿼리스트링에서 얻어오기
			int boardType = Integer.parseInt(request.getParameter("type"));
			
			// 게시판 목록 현재 페이지를 쿼리스트링에서 얻어오기
			String currentPage = request.getParameter("cp");
			
			
			// 게시글 목록 조회
			if(command.equals("/list.do")) {
				errorMsg = "게시글 목록 조회";
			
				// 페이징 처리를 위한 데이터를 계산하는 Service 호출
				PageInfo pInfo = boardService.getPageInfo(currentPage, boardType);
				
				// 현재 페이지와, 한 페이지에 보여질 게시글 수를 이용하여
				// 게시글 목록에 보여져야 할 게시글만 List 형태로 조회해오기.
				
				List<Board> bList = boardService.selectList(pInfo);

				// 썸네일 이미지 목록 조회
				List<Attachment> fList = boardService.selectFileList(pInfo);
				
				
				path = "/WEB-INF/views/board/boardList.jsp";
				request.setAttribute("pInfo", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("fList", fList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			
			// 게시글 작성 화면 이동 
			else if(command.equals("/insertForm.do")) {
				path = "/WEB-INF/views/board/boardInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			
			// 게시글 등록(게시글 + 파일)
			else if(command.equals("/insert.do")) {
				
				// enctype = "multipart/form-data"
				
				// 1_1. 전송 파일의 크기(용량) 제한(byte)단위
				int maxSize = 1024 * 1024 * 10; // 10MB	
				
				// 1_2. 전송된 파일을 저장할 위치 경로를 지정
				// Servlet Container 경로 추출 -> WebContent 폴더 위치
				String root = request.getSession().getServletContext().getRealPath("/");
				// C:\workspace\5_WebServer\wsp\WebContent
				
				// uploadImages 폴더 위치 추출
				String filePath = root + "resources\\uploadImages";
				// C:\workspace\5_WebServer\wsp\WebContent\resources\\uploadImages
				
				// 2. 파일명 변환 및 저장 작업
				/*
				 * 사용자가 업로드한 파일명을 그대로 저장하지 않는 것이 일반적임.
				 * 왜? 
				 *   - 같은 파일명으로 인한 덮어쓰기 방지
				 *   - 한글 또는 특수문자로 파일명이 있을 경우 서버, 배포 환경에 따라 문제의 요지가 될 수 있음.
				 *   
				 *   --> cos.jar 내부에 파일명을 바꿀 수 있는 클래스가 존재함.
				 * 		- DefaultFileRenamePolicy : 기본 제공하는 파일명 변경 객체
				 * 		- FileRenamePolicy 상속 후 rename() 메소드 오버라이딩 
				 * 
				 * */
				
				
				// 2_1. MultipartRequest 객체 생성 
				// -> MultipartRequest 객체가 생성되는 시점에 
				// 	  전송받은 파일들의 이름이 변경되며, 지정된 경로에 바로 저장이 됨.
				MultipartRequest mRequest 
					= new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
											// 기존 요청, 파일저장경로 , 용량제한, 일반텍스트 인코딩, 파일명 변경 개겣
				
				
				// 2_2. 파일 외에 게시판 제목, 내용 , 카테고리, 작성자 아이디 얻어오기
				// enctype이 Multipart인 경우 request.getParameter()를 이용하여 
				// 파라미터를 얻어올 수 없음. -> MultipartRequest.getParameter()를 사용 
				String boardTitle = mRequest.getParameter("title");
				String boardContent = mRequest.getParameter("content");
				String categoryName = mRequest.getParameter("category");
				
				// session에서 로그인된 아이디 얻어오기
				// -> session 은 요청 데이터가 아니므로 기존에 사용하던
				// 	  HttpServletRequest request를 이용하여 얻어옴.
				String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
				
				// 전달받은 문자 데이터를 Board VO 객체에 저장 (+boardType)
				Board board = new Board(boardTitle, boardContent, memberId, categoryName, boardType);
						
				
				// 2_3. 파일 정보를 저장할 List 생성 
				List<Attachment> fList = new ArrayList<Attachment>();
				
				// MultipartRequest 에 담겨있는 파일 정보를 하나씩 꺼내옴
				// Enumeration : Iterator 과거 버전 
					// Iterator : 컬렉션 객체에 저장된 요소 반복 접근자 
				
				
				Enumeration<String> files = mRequest.getFileNames(); 
						// 전송된 파일의 name 속성값을 모두 반환 - > 파일이 역순으로 반환됨.
				
				Attachment temp = null; // 임시 참조 변수 
				while(files.hasMoreElements()) {
					//files.hasMereElements() : 다음 반복 접근할 요소가 있으면 true
					
					String name = files.nextElement();
					// files.nextElement() : 다음 요소를 얻어옴.
					
					// 요청 객체 중 전달받은 name 속성을 가진 요소 있을 경우 
					// (파일이 지정된 name 속성을 가진 input태그에 잘 올려져 있는가?)
					if(mRequest.getFilesystemName(name) != null) {
						// mRequest.getFilesystemName(name)
						// -> name 속성값과 일치하는 input 태그 요소의 
						//    작성된 파일명을 변환한 (rename)한 값을 반환
						
						temp = new Attachment();
						
						temp.setFileOriginName(mRequest.getOriginalFileName(name));
						temp.setFileChangeName(mRequest.getFilesystemName(name));
						
						// name 속성에 따라 파일 레벨 지정
						// img1 -> 0 (썸네일), img2 -> 1, img3 -> 2, img4 -> 3
						int fileLevel = 0;
						switch (name) {
						case "img1" : fileLevel = 0; break;
						case "img2" : fileLevel = 1; break;
						case "img3" : fileLevel = 2; break;
						case "img4" : fileLevel = 3; break;
						}
					temp.setFileLevel(fileLevel);
					
					// 파일이 저장되어 있는 경로를 추가 
					temp.setFilePath(filePath);
					
					fList.add(temp);
					
					}
				}
				
				int result = boardService.insertBoard(board,fList);
				
				if(result > 0) {
					status = "success";
					msg = "게시글 등록 성공";
					//path = "list.do?type="  + boardType;
					path = "view.do?type="+boardType+"&cp=1&no="+result;
				}else {
					status = "error";
					msg = "게시글 등록 실패";
					path = request.getHeader("referer");
					
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
				
			}

			// 게시글 상세 조회 Controller 
			else if(command.equals("/view.do")) {
	            int boardNo = Integer.parseInt(request.getParameter("no"));
	            
	            // 1. 게시글 조회
	            Board board = boardService.selectBoard(boardNo);
	            
	            // 2. 게시글 조회 성공 시 이미지 조회
	            if(board != null) {
	               List<Attachment> fList = boardService.selectFiles(boardNo);
	               
	               if(!fList.isEmpty()) {
	                  // 해당 게시글에 등록된 이미지가 있을 경우
	                  request.setAttribute("fList", fList);
	               }
	               path = "/WEB-INF/views/board/boardView.jsp";
	               request.setAttribute("board", board);
	               view = request.getRequestDispatcher(path);
	               view.forward(request, response);
	            }else {
	               status = "error";
	               msg = "게시글 조회 실패";
	               request.getSession().setAttribute("status", status);
	               request.getSession().setAttribute("msg", msg);
	               response.sendRedirect(request.getHeader("referer"));
	            }
	         }
			
			// 게시글 삭제 
			else if(command.equals("/delete.do")) {
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				int result = boardService.deleteBoard(boardNo);
				
				if(result > 0 ) {
					status = "success";
					msg = "게시글이 삭제되었습니다.";
					path = "list.do?type="+boardType;
					
				}else {
					status = "error";
					msg = "게시글 삭제 실패";
					path = request.getHeader("referer");
					
					
				}
				
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
				
				
				
			}
			
			// 게시글 수정 화면 구성
			else if(command.equals("/updateForm.do")) {
				
				// 게시글 번호 얻어오기 
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				// 업데이트를 위한 게시글 정보 조회 서비스 호출 
				// -> 제목, 내용 , 카테고리 
				Board board = boardService.updateView(boardNo);
				
				if(board != null) {
					List<Attachment> fList = boardService.selectFiles(boardNo);
					
					if(!fList.isEmpty()) {
						request.setAttribute("fList", fList);
					}
					path =  "/WEB-INF/views/board/boardUpdateForm.jsp";
					request.setAttribute("board", board);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
					
				}
				
			}
			// 게시글 수정
	         else if(command.equals("/update.do")) {
	            
	            // MultipartRequest 생성에 필요한 데이터 준비
	            // 1) 용량 제한
	            int maxSize = 1024 * 1024 *10; //10Mb
	            
	            // 2) 파일 저장 경로
	            String root = request.getSession().getServletContext().getRealPath("/");
	            
	            String filePath = root + "resources\\uploadImages\\";
	            
	            // MultipartRequest 객체 생성
	            MultipartRequest mRequest =
	                  new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	         
	            // 파일을 제외한 글번호, 제목, 내용, 카테고리 얻어오기
	            int boardNo = Integer.parseInt(mRequest.getParameter("no"));
	            String boardTitle = mRequest.getParameter("title");
	            String boardContent = mRequest.getParameter("content");
	            String categoryName = mRequest.getParameter("category");
	            
	            Board board = new Board(boardNo, boardTitle, boardContent, categoryName);
	            
	            // 전달받은 파일 정보를 저장할 리스트 생성
	            List<Attachment> fList = new ArrayList<Attachment>();
	            
	            Enumeration<String> files = mRequest.getFileNames();
	            // 폼에서 전송된 파일들의 name 속성값을 반복접근하는 객체
	            
	            Attachment temp = null;
	            while(files.hasMoreElements()) {
	               String name = files.nextElement();
	               // 현재 접근한 요소의 값(name 속성값) 저장
	               
	               // getFilesystemName 조회하면 변경된이름이 조회됨.
	               if(mRequest.getFilesystemName(name) != null) {
	                  temp= new Attachment();
	                  
	                  temp.setFileOriginName(mRequest.getOriginalFileName(name));
	                  temp.setFileChangeName(mRequest.getFilesystemName(name));
	                  
	                       // name 속성에 따라 fileLevel을 부여
	                       // img1 - 0(썸네일) / img2 - 1(1번칸) / img3 - 2(2번칸) / img4 - 3(3번칸)
	                       int fileLevel = 0;
	                       switch(name) {
	                       case "img1" : fileLevel = 0; break;
	                       case "img2" : fileLevel = 1; break;
	                       case "img3" : fileLevel = 2; break;
	                       case "img4" : fileLevel = 3; break;
	                       }
	                       
	                       temp.setFileLevel(fileLevel);
	                       
	                       temp.setFilePath(filePath);
	                       
	                       fList.add(temp);
	               }
	            }
	            
	            int result = boardService.updateBoard(board, fList);
	            
	            if(result>0) {
	               status = "success";
	               msg = "게시글 수정 성공";
	               path = "view.do?type="+boardType + "&cp=" + currentPage +"&no=" + result;
	            }else {
	               status = "error";
	               msg = "게시글 수정 실패";
	               path = request.getHeader("referer");
	            }
	            
	            request.getSession().setAttribute("status", status);
	            request.getSession().setAttribute("msg", msg);
	            response.sendRedirect(path);
	            
	         }
		}catch(Exception e){
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg + "과정에서 오류가 발생했습니다.");
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
