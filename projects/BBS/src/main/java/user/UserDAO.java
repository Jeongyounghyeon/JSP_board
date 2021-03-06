package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;			//데이터 베이스에 접근할 수 있게 하는 객체
	private PreparedStatement pstmt;	//텍스트 SQL 호출
	private ResultSet rs;				//정보를 담을 수 있는 객체
	

	//실제로 mysql에 접속하게 해주는 부분
	public UserDAO( ) {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "907914";
			
			// 드라이버 로딩 : mysql 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");	
			// 드라이버들이 읽히기만 하면 자동 객체가 생성되고 DriverManager에 등록된다.
			
			//DriverManager.getConnection(연결문자열, DB_ID, DB_PW) 으로 Connection 객체를 생성
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		//SQL 문장에 ?를 설정해 준 뒤 set String으로 ?를 채워줌
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);		//받은 userID를 ?에 채움
			rs = pstmt.executeQuery();		//SQL문 결과값을 rs에 저장
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;	//로그인 성공
				}
				else
					return 0;	//비밀번호 불일치
			}
			return -1;	//아이디가 없음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; 	//데이터베이스 오류
	}
	
	public int join(User user) {
        String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserGender());
            pstmt.setString(5, user.getUserEmail());
            return pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }
}