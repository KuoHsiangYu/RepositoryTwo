package com.sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestInsertForm2
 */
@WebServlet("/TestInsertForm2")
public class TestInsertForm2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Setting the Encoding of Input Data */
		request.setCharacterEncoding("UTF-8");

		/* Setting the encoding of the output data to the web page */
		response.setContentType("text/html; charset=UTF-8");
		int singleChoice = 0;
		if (request.getParameter("insert_name") != null) {
			// 全部要檢查
			singleChoice = 1;
		} else if (request.getParameter("update_name") != null) {
			// 只需檢查一項, PRIMARY KEY
			// 如果主索引鍵以外沒有其他選項，則不予修改。
			singleChoice = 2;
		} else if (request.getParameter("delete_name") != null) {
			// 只需檢查一項, PRIMARY KEY
			singleChoice = 3;
		} else if (request.getParameter("select_name") != null) {
			// 只需檢查一項, PRIMARY KEY
			singleChoice = 4;
		} else {
			System.out.println("#1 error. The POST method request is not issued by the "
					+ "specified four buttons[insert_name, update_name, " + "delete_name, select_name].");
			singleChoice = -1;
			return;
		}
		request.setAttribute("buttonType", singleChoice);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/InsertMemberSuccess.jsp");
		requestDispatcher.forward(request, response);
	}

}
