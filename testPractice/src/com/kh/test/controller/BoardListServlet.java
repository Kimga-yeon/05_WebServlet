package com.kh.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.test.model.dao.BoardDAO;
import com.kh.test.model.vo.TestBoard;

@WebServlet("/board/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			List<TestBoard> list = new BoardDAO().selectList();
			request.setAttribute("list", list);
		
			String path = "/WEB-INF/views/boardList.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
