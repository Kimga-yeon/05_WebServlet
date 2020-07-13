package com.kh.wsp.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.wsp.member.model.vo.Member;
import com.kh.wsp.notice.model.service.NoticeExService;
import com.kh.wsp.notice.model.vo.Notice;

@WebServlet("/noticeEx/*")
public class NoticeExController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();

		String contextPath = request.getContextPath();

		String command = uri.substring((contextPath + "/noticeEx").length());

		String path = null;
		RequestDispatcher view = null;

		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = "과정에서 오류가 발생했습니다.";


		try {
			NoticeExService service = new NoticeExService();

			// ===== 공지사항 목록 조회 Controller =====
			if(command.equals("/list.do")) {
				errorMsg = "공지사항 목록 조회" + errorMsg;

				List<Notice> list = service.selectList();

				path = "/WEB-INF/views/notice/noticeList.jsp";
				request.setAttribute("list", list);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			// ===== 공지사항 등록 화면 이동 Controller =====
			else if(command.equals("/insertForm.do")) {
				path = "/WEB-INF/views/notice/noticeInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			// ===== 공지사항 등록 Controller =====
			else if(command.equals("/insert.do")) {
				HttpSession session = request.getSession();

				String noticeTitle = request.getParameter("title");
				String noticeContent = request.getParameter("content");
				String memberId 
				= ((Member)session.getAttribute("loginMember")).getMemberId();

				Notice notice = new Notice(noticeTitle, noticeContent, memberId);

				int result = service.insertNotice(notice);

				if(result > 0) {
					status = "success";
					msg = "공지사항이 등록되었습니다.";
					path = "view.do?no=" + result;
					// 추후 작성한 글로 바로 이동하도록 수정 예정
				}else {
					status = "error";
					msg = "공지사항 등록 실패";
					path = "list.do";
				}

				session.setAttribute("status", status);
				session.setAttribute("msg", msg);
				response.sendRedirect(path);

			}

			else if(command.equals("/view.do")) {
				errorMsg = "공지사항 상세 조회" + errorMsg;

				int noticeNo = Integer.parseInt(request.getParameter("no"));

				Notice notice = service.selectNotice(noticeNo);

				if(notice != null) { // 조회결과가 있을 경우
					path = "/WEB-INF/views/notice/noticeView.jsp";
					request.setAttribute("notice", notice);

					view = request.getRequestDispatcher(path);
					view.forward(request, response);

				}else { // 조회 결과가 없을 경우
					request.getSession().setAttribute("status","error");
					request.getSession().setAttribute("msg","존재하지 않는 글입니다.");
					response.sendRedirect("list.do");
				}

			}


		}catch(Exception e) {
			e.printStackTrace();
			path = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);

			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
