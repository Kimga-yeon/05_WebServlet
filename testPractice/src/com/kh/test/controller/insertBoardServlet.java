package com.kh.test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.test.model.dao.BoardDAO;
import com.kh.test.model.vo.TestBoard;

@WebServlet("/board/boardWrite.do")
public class insertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		TestBoard board = new TestBoard();
		board.setTitle(replaceParameter(title));
		board.setContent(replaceParameter(content));
		
		try {
			
			int result = new BoardDAO().insertBoard(board);
			
			if(result > 0) {
				response.sendRedirect("boardList.do");
			}else {
				throw new Exception("글 작성 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		
		// 크로스 사이트 스크립트 방지를 위한 메소드 
		private String replaceParameter(String param) {
			String result = param;
			
			if(param != null) {
				result = result.replaceAll("<", "&lt;");
				result = result.replaceAll(">", "&gt;");
				result = result.replaceAll("&", "&amp;");
				result = result.replaceAll("\"", "&quot;");
								//		바꾸기 전, 바꾼 후 
			}
			return result;
		}

}
