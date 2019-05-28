package com.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO {
	DataSource dataSource = null;

	public EmpDAO() {
		/* Constructor: to initialize object setting */
		try {
			Context context = new InitialContext();

			/* WebContent -> META-INF -> context.xml */
			/* <Resource name="jdbc/MemberDataBaseS"/> */
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/MemberDataBaseS");
		} catch (NamingException e) {
			/* e.printStackTrace(); */
			System.out.println(e);
		}
	}

	/* 查詢 */
	private static final String SELECT_BY_ID = "SELECT id, date, job_category, "
			+ "industry, employees_number, total_salary, recurrent_salary, "
			+ "non_recurring_salary FROM CareerSurvey WHERE id = ?";

	/* 查詢 */
	public EmpBean selectById(int id) throws SQLException {
		EmpBean empBeanResult = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				empBeanResult = new EmpBean();
				empBeanResult.setId(resultSet.getInt("id"));/* id 編號 */
				empBeanResult.setDate(resultSet.getDate("date"));/* date 日期 */
				empBeanResult.setJob_category(resultSet.getString("job_category"));/* job_category 職類別 */
				empBeanResult.setIndustry(resultSet.getString("industry"));/* industry 行業別 */
				empBeanResult.setEmployees_number(resultSet.getInt("employees_number"));/* employees_number 受僱員工人數 */
				empBeanResult.setTotal_salary(resultSet.getInt("total_salary"));/* total_salary 總薪資 */
				empBeanResult.setRecurrent_salary(resultSet.getInt("recurrent_salary"));/* recurrent_salary 經常性薪資 */
				empBeanResult.setNon_recurring_salary(
						resultSet.getInt("non_recurring_salary"));/* non_recurring_salary 非經常性薪資 */
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			/* release resources */
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
		return empBeanResult;
	}// end of selectById method

	/* 新增 */
	private static final String INSERT = "INSERT INTO CareerSurvey VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

	/* 新增 */
	public EmpBean insert(EmpBean empBean) throws SQLException {
		EmpBean empBeanResult = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);

			preparedStatement.setInt(1, empBean.getId());// id 編號 1
			preparedStatement.setDate(2, empBean.getDate());// date 日期 2
			preparedStatement.setString(3, empBean.getJob_category());// job_category 職類別 3
			preparedStatement.setString(4, empBean.getIndustry());// industry 行業別 4
			preparedStatement.setInt(5, empBean.getEmployees_number());// employees_number 受僱員工人數 5
			preparedStatement.setInt(6, empBean.getTotal_salary());// total_salary 總薪資 6
			preparedStatement.setInt(7, empBean.getRecurrent_salary());// recurrent_salary 經常性薪資 7
			preparedStatement.setInt(8, empBean.getNon_recurring_salary());// non_recurring_salary 非經常性薪資 8

			int i = preparedStatement.executeUpdate();
			if (i == 1) {
				empBeanResult = this.selectById(empBean.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
		return empBeanResult;
	}// end of insert method

	/* 刪除 */
	private static final String DELETE = "DELETE FROM CareerSurvey WHERE id = ?";

	/* 刪除 */
	public int delete(int id) throws SQLException {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
		return result;
	}

	/* 修改 */
	private static final String UPDATE = "UPDATE CareerSurvey SET date = ?, "
			+ "job_category = ?, industry = ?, employees_number = ?, " + "total_salary = ?, recurrent_salary = ?, "
			+ "non_recurring_salary = ? WHERE id = ?";

	/* 修改 */
	public EmpBean update(EmpBean empBean) throws SQLException {
		EmpBean empBeanResult = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setDate(1, empBean.getDate());// date 日期
			preparedStatement.setString(2, empBean.getJob_category());// job_category 職類別
			preparedStatement.setString(3, empBean.getIndustry());// industry 行業別
			preparedStatement.setInt(4, empBean.getEmployees_number());// employees_number 受僱員工人數
			preparedStatement.setInt(5, empBean.getTotal_salary());// total_salary 總薪資
			preparedStatement.setInt(6, empBean.getRecurrent_salary());// recurrent_salary 經常性薪資
			preparedStatement.setInt(7, empBean.getNon_recurring_salary());// non_recurring_salary 非經常性薪資
			preparedStatement.setInt(8, empBean.getId());// id 編號

			int i = preparedStatement.executeUpdate();
			if (i == 1) {
				empBeanResult = this.selectById(empBean.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
		return empBeanResult;
	}// end of insert method
}// end of EmpDAO class
