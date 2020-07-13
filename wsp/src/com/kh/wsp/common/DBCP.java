package com.kh.wsp.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCP {
   /*   DataBase Connection Pool
    *  - 기존 JDBCTemplate은 클라이언트의 요청마다
    *      드라이버 로드 작업, Connection 객체 생성/반환 작업에 대한 코드와 동작을 
    *      매번 실행해야 하는 비효율적인 모습을 보여줬다.
    * 
    * - DBCP는 WAS(Tomcat, Servlet Container)가
    *   DB와의 연결(Connection 객체)을 미리 여러개 만들어 가지고 있다가 
    *   클라이언트의 요청 시 마다 만들어둔 연결을 가져가 사용하게 하고
    *   사용이 완료되면 반환하게 하는 기술.
    *   (Connection 객체를 만들어 웅덩이에 모아 놓은 모양이라고해서 
    *    Connection Pool 이라고 함.)
    * 
    * 
    * */
   
   public static Connection getConnection() throws NamingException, SQLException {
         // JNDI(Java Naming and Directory Interface API)
         /*디렉터리 서비스에 접근하는데 사용하는 API
         어플리케이션은 JNDI를 사용하여 서버의 resource를 찾는다.
         특히 JDBC resource를 data source라고 부른다.
         
         Resource를 서버에 등록할 때 고유한 JNDI 이름을 붙이는데, JNDI 이름은 디렉터리 경로 형태를 가진다.
         예를 들어 data source의 JNDI 이름은 'jdbc/mydb' 형식으로 짓는다.
         
          서버에서 'jdbc/oracle'라는 DataSource를 찾으려면 
         'java:comp/env/jdbc/oracle'라는 JNDI 이름으로 찾아야 한다. 
         즉 lookup() 메소드에 'java:comp/env/jdbc/oracle'를 인자값으로 넘긴다.
         */
      
      // Servers 폴더에 존재하는 context.xml 파일을 찾는 작업
      Context initContext = new InitialContext();
      Context envContext = (Context)initContext.lookup("java:/comp/env");
      
      DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
      // DataSource : DriverManager를 대체하는 객체로
      // Connection 생성, Connection Pool 구현, 관리하는 객체
      return ds.getConnection();
   }
   
   
}