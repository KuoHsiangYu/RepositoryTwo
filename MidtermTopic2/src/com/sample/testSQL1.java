package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testSQL1
 */
/* This is servlet file */
/*
 * The main purpose of this program is to test whether the SQL Server 2017
 * DataBase has been successfully connected.
 */
/* DAO (JDBC Program) for Accessing DataBase(like: SQL Server 2017) */
@WebServlet("/testSQL1")
public class testSQL1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EmpDAO empDAO = new EmpDAO();
			EmpBean empBean = empDAO.selectById(2);
			System.out.println("empBean = " + empBean.toString());
			PrintWriter out2 = response.getWriter();
			out2.println("empBean = " + empBean.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
