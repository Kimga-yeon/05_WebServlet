package com.gy.wsp.board.controller;

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

import com.gy.wsp.board.model.service.BoardService;
import com.gy.wsp.board.model.vo.Attachment;
import com.gy.wsp.board.model.vo.Board;
import com.gy.wsp.board.model.vo.PageInfo;
import com.gy.wsp.common.MyFileRenamePolicy;
import com.gy.wsp.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		String ContextPath = request.getContextPath();
		
		String command = uri.substring((ContextPath + "/board").length());
		
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
			
			//int boardType = Integer.parseInt(request.getParameter("type"));
			String currentPage = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				errorMsg = "게시글 목록 조회";
				 
				PageInfo pInfo = boardService.getPageInfo(currentPage );
				
				List<Board>  bList = boardService.selectList(pInfo);
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
			
			else if(command.equals("/insert.do")) {
				int maxSize = 1024 * 1024 * 10; 
				
				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources\\uploadImages";
				
				MultipartRequest mRequest 
				 = new MultipartRequest(request, filePath, maxSize , "UTF-8", new MyFileRenamePolicy());
				
				String boardTitle = mRequest.getParameter("title");
				String boardContent = mRequest.getParameter("content");
				String categoryName = mRequest.getParameter("category");
				
				String memberEmail = ((Member)request.getSession().getAttribute("loginMember")).getMemberEmail();
				
				Board board = new Board(boardTitle, boardContent, memberEmail, categoryName);
				
				List<Attachment> fList = new ArrayList<Attachment>();
						
				Enumeration<String> files = mRequest.getFileNames();
				
				Attachment temp = null;
				while(files.hasMoreElements()) {
					String name = files.nextElement();
					
					if(mRequest.getFilesystemName(name) != null) {
						temp = new Attachment();
						
						temp.setFileOriginName(mRequest.getOriginalFileName(name));
						temp.setFileChangeName(mRequest.getFilesystemName(name));
						
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
				
				int result = boardService.insertBoard(board,fList);
				
				if(result > 0) {
					status = "success";
					msg = "게시글 등록 성공";
					path = "list.do?cp=1&no=" + result;
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
					
					System.out.println(board);
					System.out.println(fList);
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
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
