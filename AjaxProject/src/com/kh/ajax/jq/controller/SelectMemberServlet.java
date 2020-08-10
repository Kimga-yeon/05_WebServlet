package com.kh.ajax.jq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.ajax.jq.model.service.MemberService;
import com.kh.ajax.jq.model.vo.Member;

@WebServlet("/jqAjax4.do")
public class SelectMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		String id = request.getParameter("input");
		
		JSONObject jsonUser = new JSONObject();
		
		try {
			Member member = new MemberService().selectMember(id);
			
//			if(member != null) {
//				jsonUser.put("id",member.getMemberId());
//				jsonUser.put("name",member.getMemberName());
//				jsonUser.put("email",member.getMemberEmail());
//				jsonUser.put("interest",member.getMemberInterest());
//				jsonUser.put("enrolldate",member.getMemberEnrollDate().toString());
//				
//				
//			}
			
			response.setCharacterEncoding("UTF-8");
			
			//response.getWriter().print(jsonUser);
			
			// GSON ( Google JSON)
			//new Gson().toJson(member, response.getWriter());
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			gson.toJson(member, response.getWriter());
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
