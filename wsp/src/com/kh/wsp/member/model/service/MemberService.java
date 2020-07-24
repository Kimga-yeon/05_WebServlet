package com.kh.wsp.member.model.service;

import static com.kh.wsp.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import com.kh.wsp.member.model.dao.MemberDAO;
import com.kh.wsp.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao;
	public MemberService() throws FileNotFoundException, IOException{
		dao = new MemberDAO();
	}
	   // Dynamic Web Project는  JDBC 프로젝트와 구조와 동작 방법이 다름
	   // JDBC 프로젝트 
	   // -> 작성한 소스 코드 + 실행을위한 .class파일 + 외부 입출력 파일등
	   //    모든 파일이 Java프로젝트 안에 존재함.
	   // == 프로그램 구동 중에도  언제든지 프로젝트 폴더에 접근할 수 있고, 내부의 변화를 확인할 수 있음.
	   
	   // Dynamic Web Project
	   // -> 소스 코드를 작성하는 부분(src)과, 
	   //    컴파일된 코드를 이용하여 클라이언트에게 동적 웹 서비스를 제공(배포)하는 부분(WebContent)로 나뉨
	   //       --> JDBC 프로젝트에 해당하는 부분이  src 폴더에 해당함.
	   // 두 폴더는 독립된 폴더로 빌드 또는 배포 시작, 서버 reload된 경우에만 
	   // src폴더 소스 코드가 컴파일 되어 WebContent/WEB-INF/classes 폴더에 저장되는 경우를 제외하면 연결 될 일이 없음.
	   // 서버 배포 중 reload통해 연결된다 하더라도 동적으로 화면을 구성되는데 사용되는 .class 파일을 제외하고
	   // 실시간으로 반영되지 않음. == 배포 중 src 폴더에서 .properties를 변환해도 적용되지 않음.
	
	   /** 로그인용 Service
	    * @param member
	    * @return loginMember
	    * @throws Exception
	    */
	   public Member loginMember(Member member) throws Exception{
	      // 커넥션 얻어오기
	      Connection conn = getConnection();
	      
	      // dao 호출
	      Member loginMember = dao.loginMember(conn, member);
	      
	      // 커넥션 반환
	      conn.close();
	      
	      // 결과 반환
	      return loginMember;
	   }

		/** 회원 가입용 Service
		 * @param member
		 * @return result
		 * @throws Exception
		 */
		public int signUp(Member member) throws Exception {
			Connection conn = getConnection();
			int result = dao.signUp(conn, member);
			
			// 트랜잭션 처리
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			conn.close();

			return result;
			
			
		}

		/** 아이디 중복 체크용 Service
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public int idDupCheck(String id) throws Exception{
			Connection conn = getConnection();
			
			int result = dao.idDupCheck(conn,id);
			conn.close();
			
			return result;
		}

		/** 회원 정보 수정 Service
		 * @param member
		 * @return result
		 * @throws Exception
		 */
		public int updateMember(Member member) throws Exception{
			Connection conn = getConnection();
			int result = dao.updateMember(conn,member);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			conn.close();
			
			return result;
		}

		/** 비밀번호 수정 Service
		 * @param member
		 * @param newPwd
		 * @return
		 * @throws Exception
		 */
		public int updatePwd(Member member, String newPwd) throws Exception {
			
			Connection conn = getConnection();
					
			// 1. 현재 비밀번호 일치여부 확인
			int result = dao.checkPwd(conn,member);
			
			if(result > 0) { // 현재 비밀번호 일치
				// 2. 일치할 경우 비밀번호 수정
				
				// member객체 재활용
				member.setMemberPwd(newPwd);
				
				result = dao.updatePwd(conn, member);
				
				if(result > 0) conn.commit();
				else		   conn.rollback();
				
			}else { // 현재 비밀번호 불일치
				
				result = -1; //  비밀번호 불일치 코드로 -1 사용
				
				
			}
			conn.close();
			
						
			return result;
		}
		
		
		
		public int updatePwd2(Member member, String newPwd) throws Exception {
			
			Connection conn = getConnection();
					
		
			int result = dao.updatePwd(conn, member, newPwd); //오버로딩
			
			if(result > 0 ) conn.commit();
			else			conn.rollback();
			
			conn.close();
			return result;
			
			
						
		}

		/** 회원 정보 조회 Service
		 * @param memberId
		 * @return	selectMember
		 * @throws Exception
		 */
		public Member selectMember(String memberId) throws Exception{
			Connection conn = getConnection();
			
			Member selectMember = dao.selectMember(conn,memberId);
			
			conn.close();
			
			return selectMember;
		}
		

//		
//		/** 회원 탈퇴용 Service
//		 * @param member
//		 * @param newPwd
//		 * @return result
//		 * @throws Exception
//		 */
//		public int deleteMember(Member member, String newPwd) throws Exception {
//		
//		
//		
//		}
}	   