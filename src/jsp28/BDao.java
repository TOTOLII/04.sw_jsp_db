package jsp28;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.jsp.jstl.sql.Result;
import javax.sql.DataSource;

import com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm;

import sun.security.util.PropertyExpander.ExpandException;

public class BDao {
	
	DataSource dataSource; //데이터베이스는 어디서든 불러올수 있도록 전역변수 설정 
	
	public BDao() {
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g"); //lookup : 데이터를 찾은 후 사용할 수 있도록 객체를 반환해 주는 메소드
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void write(String bName, String bTitle, String bContent) {
		System.out.println("bName : " + bName);
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "
					+ "     values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			preparedStatement = connection.prepareStatement(query);
			System.out.println("sucess");
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>(); //dtos라는 배열 객체를 하나 생성
		Connection connection = null; //데이터베이스 접근을 위한 객체
		PreparedStatement preparedStatement = null; //쿼리문을 실행하기 위한 객체 생성
		ResultSet resultSet = null; //결과값을 얻어오기 위한 객체생성
		
		try {
			connection = dataSource.getConnection(); 
			
			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, "
					+ "bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bStep = resultSet.getInt("bStep");
				int bGroup = resultSet.getInt("bGroup");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close(); //사용한 변수 초기화
				if(preparedStatement != null) preparedStatement.close(); //사용한 변수 초기화
				if(connection != null) connection.close(); //사용한 변수 초기화
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace(); 
			}
		}
		return dtos; //요청했던 객체인 BListCommand폼의 dao.list();에게 요청값을 반환
	}
	
	public BDto contentView(String strID) {
		
		System.out.println("BDao contentview");
		upHit(strID);
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = dataSource.getConnection();
			System.out.println("db connection ok");
			String query = "select * from mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId =?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
			
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "delete from mvc_board where bId =?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null)	connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BDto reply_view(String str) {
		
		BDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(str));
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);	
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent)"
					+ "values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
		
	private void replyShape (String strGroup, String strStep) {
			
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			//그룹이 같고 원래 글 보다 높은 그룹에 해당되는 객체들의 스탭을 올려줌
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null) preparedStatement.close();
					if (connection != null) connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	 }
		
		private void upHit(String bId) {
			Connection  connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, bId);
				
				int rn = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}
	}