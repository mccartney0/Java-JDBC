package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = DB.getConnection();
//
//			// Starting Statement
//			st = con.createStatement();
//
//			rs = st.executeQuery("select * from seller");
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DB.closeResultSet(rs);
//			DB.closeStatement(st);
//			DB.closeConnection();
//		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Connection conn = null;
		PreparedStatement st = null;
				
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					//Placeholder
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			//Insert into first "?"
			st.setString(1, "Julian Oliveira");
			st.setString(2, "julian123@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("15/55/1996").getTime()));
			st.setDouble(4, 5500);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					//Column 1
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id + " Rows affected: " + rowsAffected);
				}
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
