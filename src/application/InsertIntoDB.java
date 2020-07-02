package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class InsertIntoDB {

	public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Connection conn = null;
		PreparedStatement st = null;
				
		try {
			conn = DB.getConnection();
			/*
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
			*/
			
			st = conn.prepareStatement(
					"insert into department (Name) values ('D5'),('D6')",
					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			int lineCounter = 0;
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					lineCounter++;
					//Column 1
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id + " Rows affected: " + lineCounter);
				}
			}
		} catch (SQLException  e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
