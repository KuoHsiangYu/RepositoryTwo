package com.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectName2
 */
@WebServlet("/SelectName2")
public class SelectName2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int count = 1;
		System.out.println("SelectName2 Servlet doGet method");
		PrintWriter out2 = response.getWriter();
		String user = "sa";
		String password = "passw0rd";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://localhost:1433;DatabaseName=SQL_Homework";
			connection = DriverManager.getConnection(connectionURL, user, password);
			String queryStatement = "SELECT id, job_category FROM CareerSurvey";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(queryStatement);

			int id = 1;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<select name='ename'>");
			while (resultSet.next()) {
				count = count + 1;
				id = resultSet.getInt("id");
				stringBuilder.append("<option value='");
				stringBuilder.append(String.valueOf(id));
				stringBuilder.append("'>");
				stringBuilder.append(String.format("%03d", id));
				stringBuilder.append("-");
				stringBuilder.append(resultSet.getString("job_category"));
			}
			stringBuilder.append("</select>");
			out2.println("您的 PRIMARY KEY 必須從 " + count + " 開始。<br>");
			out2.println(stringBuilder.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			out2.flush();
		} // end of finally
	}// end of doGet method

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SelectName2 Servlet doPost method");
		doGet(request, response);
	}// end of doPost method

}// end of SelectName2 class