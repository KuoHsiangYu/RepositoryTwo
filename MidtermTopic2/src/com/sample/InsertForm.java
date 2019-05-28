package com.sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertForm
 */
@WebServlet("/InsertForm")
public class InsertForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("InsertForm Servlet doGet method");
		doPost(request, response);
	}

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

		System.out.println("InsertForm Servlet doPost method");

		int singleChoice = 0;
		if (request.getParameter("insert_name") != null) {
			// 全部要檢查
			singleChoice = 1;// 新增
			System.out.println("singleChoice = " + singleChoice);
			checkForm(request, response, singleChoice);
		} else if (request.getParameter("update_name") != null) {
			// 只需檢查一項, PRIMARY KEY
			// 如果主索引鍵以外沒有其他選項，則不予修改。
			singleChoice = 2;// 修改
			System.out.println("singleChoice = " + singleChoice);
			checkForm(request, response, singleChoice);
		} else if (request.getParameter("delete_name") != null) {
			// 只需檢查一項, PRIMARY KEY
			singleChoice = 3;// 刪除
			System.out.println("singleChoice = " + singleChoice);
			checkForm(request, response, singleChoice);
		} else if (request.getParameter("select_name") != null) {
			// 只需檢查一項, PRIMARY KEY
			singleChoice = 4;// 查詢
			System.out.println("singleChoice = " + singleChoice);
			checkForm(request, response, singleChoice);
		} else {
			System.out.println("#1 error. The POST method request is not issued by the "
					+ "specified four buttons[insert_name, update_name, " + "delete_name, select_name].");
			singleChoice = -1;
			RequestDispatcher requestDispatcherIndex = request.getRequestDispatcher("index.jsp");
			requestDispatcherIndex.forward(request, response);
		}

		// System.out.println("insert_name = " + request.getParameter("insert_name"));
		// System.out.println("update_name = " + request.getParameter("update_name"));
		// System.out.println("update_name = " + request.getParameter("delete_name"));
		// System.out.println("update_name = " + request.getParameter("select_name"));

	}// end of doPost method

	private void checkForm(HttpServletRequest request, HttpServletResponse response, int singleChoice)
			throws ServletException, IOException {
		if (singleChoice == 1) {
			insertData(request, response, singleChoice);
		} else if (singleChoice == 2) {
			updateData(request, response, singleChoice);
		} else if (singleChoice == 3) {
			deleteData(request, response, singleChoice);
		} else if (singleChoice == 4) {
			selectData(request, response, singleChoice);
		} else {
			System.out.println("error! The singleChoice is not issued by [1, 2, 3, 4].");
			return;
		}
	}// end of checkForm

	private void selectData(HttpServletRequest request, HttpServletResponse response, int singleChoice)
			throws ServletException, IOException {
		/* 查詢一筆資料 */

		/* 返回原始頁面 */
		RequestDispatcher requestDispatcherIndex = request.getRequestDispatcher("index.jsp");

		String str = "";
		Map<String, String> errorMessage = new HashMap<String, String>();
		request.setAttribute("ErrorMsg", errorMessage);

		int id = 0;/* 編號 PRIMARY KEY 1 */
		/* 1 */
		str = request.getParameter("id_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("id", "編號欄位 PRIMARY KEY 不可為空");
			requestDispatcherIndex.forward(request, response);
			return;
		} else {
			try {
				id = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(e);
				errorMessage.put("id", "編號欄位 PRIMARY KEY 格式錯誤");
				requestDispatcherIndex.forward(request, response);
				return;
			}
		} // end of else level=1

		EmpBean empBean = null;
		/* 先查詢這個 PRIMARY KEY 是否有存在在資料庫裡面，並把資料撈出來。 */
		try {
			EmpDAO empDAO = new EmpDAO();
			empBean = empDAO.selectById(id);
			request.setAttribute("memberBean", empBean);
			request.setAttribute("buttonType", singleChoice);
			if (empBean == null) {
				errorMessage.put("exception", "查無資料，請重新檢查您輸入的 PRIMARY KEY 是否正確。");
				requestDispatcherIndex.forward(request, response);
				return;
			}
			RequestDispatcher requestDispatcherSuccess = request.getRequestDispatcher("InsertMemberSuccess.jsp");
			requestDispatcherSuccess.forward(request, response);
			return;
		} catch (SQLException e) {
			System.out.println(e);
			errorMessage.put("exception", "資料庫存取錯誤");
			requestDispatcherIndex.forward(request, response);
			return;
		}
	}// end of selectData method

	private void deleteData(HttpServletRequest request, HttpServletResponse response, int singleChoice)
			throws ServletException, IOException {
		/* 刪除一筆資料 */

		/* 返回原始頁面 */
		RequestDispatcher requestDispatcherIndex = request.getRequestDispatcher("index.jsp");

		String str = "";

		Map<String, String> errorMessage = new HashMap<String, String>();
		request.setAttribute("ErrorMsg", errorMessage);

		int id = 0;/* 編號 PRIMARY KEY 1 */

		/* 1 */
		str = request.getParameter("id_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("id", "編號欄位 PRIMARY KEY 不可為空");
			requestDispatcherIndex.forward(request, response);
			return;
		} else {
			try {
				id = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("id", "編號欄位 PRIMARY KEY 格式錯誤");
				requestDispatcherIndex.forward(request, response);
				return;
			}
		} // end of else level=1

		EmpBean empBeanOld = null;

		/* 先查詢這個 PRIMARY KEY 是否有存在在資料庫裡面，並把資料撈出來。 */
		try {
			EmpDAO empDAO = new EmpDAO();
			empBeanOld = empDAO.selectById(id);
			request.setAttribute("memberBean", empBeanOld);
			request.setAttribute("buttonType", singleChoice);
			if (empBeanOld == null) {
				errorMessage.put("exception", "查無資料，請重新檢查您輸入的 PRIMARY KEY 是否正確。");
				requestDispatcherIndex.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			System.out.println(e);
			errorMessage.put("exception", "資料庫存取錯誤");
			requestDispatcherIndex.forward(request, response);
			return;
		}

		/* 開始執行刪除資料的程序 */
		try {
			EmpDAO empDAO = new EmpDAO();
			int n = empDAO.delete(id);
			if (n < 1) {
				errorMessage.put("exception", "刪除資料失敗，請重新檢查您輸入的 PRIMARY KEY 是否正確。");
				requestDispatcherIndex.forward(request, response);
				return;
			}
			request.setAttribute("memberBean", empBeanOld);
			request.setAttribute("buttonType", singleChoice);
			RequestDispatcher requestDispatcherSuccess = request.getRequestDispatcher("InsertMemberSuccess.jsp");
			requestDispatcherSuccess.forward(request, response);
			return;
		} catch (SQLException e) {
			System.out.println(e);
			errorMessage.put("exception", "資料庫存取錯誤");
			requestDispatcherIndex.forward(request, response);
			return;
		}

	}// end of deleteData method

	private void updateData(HttpServletRequest request, HttpServletResponse response, int singleChoice)
			throws ServletException, IOException {
		/* 修改一筆資料 */

		/* 返回原始頁面 */
		RequestDispatcher requestDispatcherIndex = request.getRequestDispatcher("index.jsp");

		String str = "";

		Map<String, String> errorMessage = new HashMap<String, String>();
		request.setAttribute("ErrorMsg", errorMessage);

		int id = 0;/* 編號 PRIMARY KEY 1 */
		java.sql.Date date = null;/* 日期 2 */
		String job_category = "";/* 職類別 3 */
		String industry = "";/* 行業別 4 */
		int employees_number = 0;/* 受僱員工人數 5 */
		int total_salary = 0;/* 總薪資 6 */
		int recurrent_salary = 0;/* 經常性薪資 7 */
		int non_recurring_salary = 0;/* 非經常性薪資 8 */

		/* 1 */
		str = request.getParameter("id_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("id", "編號欄位 PRIMARY KEY 不可為空");
			requestDispatcherIndex.forward(request, response);
			return;
		} else {
			try {
				id = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("id", "編號欄位 PRIMARY KEY 格式錯誤");
				requestDispatcherIndex.forward(request, response);
				return;
			}
		}

		EmpBean empBeanOld = null;

		/* 先查詢這個 PRIMARY KEY 是否有存在在資料庫裡面，並把資料撈出來。 */
		try {
			EmpDAO empDAO = new EmpDAO();
			empBeanOld = empDAO.selectById(id);
			request.setAttribute("memberBean", empBeanOld);
			request.setAttribute("buttonType", singleChoice);
			if (empBeanOld == null) {
				errorMessage.put("exception", "查無資料，請重新檢查您輸入的 PRIMARY KEY 是否正確。");
				requestDispatcherIndex.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			System.out.println(e);
			errorMessage.put("exception", "資料庫存取錯誤");
			requestDispatcherIndex.forward(request, response);
			return;
		}

		/* 2 */
		str = request.getParameter("date_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			date = empBeanOld.getDate();
		} else {
			try {
				date = java.sql.Date.valueOf(str);
			} catch (Exception e) {
				System.out.println(e);
				errorMessage.put("date", "日期欄位格式錯誤");
			}
		}

		/* 3 */
		job_category = request.getParameter("job_category_name");
		job_category = job_category.trim();
		if (job_category == null || job_category.trim().length() < 1) {
			job_category = empBeanOld.getJob_category();
		}

		/* 4 */
		industry = request.getParameter("industry_name");
		industry = industry.trim();
		if (industry == null || industry.trim().length() < 1) {
			industry = empBeanOld.getIndustry();
		}

		/* 5 */
		str = request.getParameter("employees_number_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			employees_number = empBeanOld.getEmployees_number();
		} else {
			try {
				employees_number = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("employees_number", "受僱員工人數欄位 格式錯誤");
			}
		}

		/* 6 */
		str = request.getParameter("total_salary_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			total_salary = empBeanOld.getTotal_salary();
		} else {
			try {
				total_salary = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("total_salary", "總薪資欄位 格式錯誤");
			}
		}

		/* 7 */
		str = request.getParameter("recurrent_salary_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			recurrent_salary = empBeanOld.getRecurrent_salary();
		} else {
			try {
				recurrent_salary = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("recurrent_salary", "經常性薪資欄位 格式錯誤");
			}
		}

		/* 8 */
		str = request.getParameter("non_recurring_salary_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			non_recurring_salary = empBeanOld.getNon_recurring_salary();
		} else {
			try {
				non_recurring_salary = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("non_recurring_salary", "非經常性薪資欄位 格式錯誤");
			}
		}

		/*
		 * If there is an error, call the view component and send back the error
		 * message.
		 */
		if (!errorMessage.isEmpty()) {
			requestDispatcherIndex.forward(request, response);
			return;
		}

		EmpBean empBeanNew = new EmpBean();
		empBeanNew.setId(id);
		empBeanNew.setDate(date);
		empBeanNew.setJob_category(job_category);
		empBeanNew.setIndustry(industry);
		empBeanNew.setEmployees_number(employees_number);
		empBeanNew.setTotal_salary(total_salary);
		empBeanNew.setRecurrent_salary(recurrent_salary);
		empBeanNew.setNon_recurring_salary(non_recurring_salary);

		/* 修改資料 */
		try {
			EmpDAO empDAO = new EmpDAO();
			/* 同時回傳修改完畢後資料的內容 */
			EmpBean empBeanResult = empDAO.update(empBeanNew);
			if (empBeanResult == null) {
				errorMessage.put("exception", "查無資料，請重新檢查您輸入的 PRIMARY KEY 是否正確。");
				requestDispatcherIndex.forward(request, response);
				return;
			}
			request.setAttribute("memberBean", empBeanResult);
			request.setAttribute("buttonType", singleChoice);
			RequestDispatcher requestDispatcherSuccess = request.getRequestDispatcher("InsertMemberSuccess.jsp");
			requestDispatcherSuccess.forward(request, response);
			return;
		} catch (SQLException e) {
			System.out.println(e);
			errorMessage.put("exception", "資料庫存取錯誤");
			requestDispatcherIndex.forward(request, response);
			return;
		}
	}// end of updateData method

	private void insertData(HttpServletRequest request, HttpServletResponse response, int singleChoice)
			throws ServletException, IOException {
		/* 新增一筆資料 */
		/* This function checks all the contents of the form */

		RequestDispatcher requestDispatcherIndex = request.getRequestDispatcher("index.jsp");

		String str = "";

		// PrintWriter out2 = response.getWriter();
		// @SuppressWarnings("unused")
		// HttpSession session = request.getSession();
		Map<String, String> errorMessage = new HashMap<String, String>();
		request.setAttribute("ErrorMsg", errorMessage);

		int id = 0;/* 編號 PRIMARY KEY 1 */
		java.sql.Date date = null;/* 日期 2 */
		String job_category = "";/* 職類別 3 */
		String industry = "";/* 行業別 4 */
		int employees_number = 0;/* 受僱員工人數 5 */
		int total_salary = 0;/* 總薪資 6 */
		int recurrent_salary = 0;/* 經常性薪資 7 */
		int non_recurring_salary = 0;/* 非經常性薪資 8 */

		/* 讀取使用者所輸入，由瀏覽器送來的 id_name 欄位內的資料，注意大小寫 */
		/*
		 * Read the data entered by the user in the id_name field sent by the browser,
		 * pay attention to case.
		 */
		/* 1 */
		str = request.getParameter("id_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("id", "編號欄位 PRIMARY KEY 不可為空");
		} else {
			try {
				id = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("id", "編號欄位 PRIMARY KEY 格式錯誤");
			}
		}

		/* 2 */
		str = request.getParameter("date_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("date", "日期欄位 不可為空");
		} else {
			try {
				date = java.sql.Date.valueOf(str);
			} catch (Exception e) {
				System.out.println(e);
				errorMessage.put("date", "日期欄位格式錯誤");
			}
		}

		/* 3 */
		job_category = request.getParameter("job_category_name");
		job_category = job_category.trim();
		if (job_category == null || job_category.trim().length() < 1) {
			errorMessage.put("job_category", "職類別欄 不可為空");
		}

		/* 4 */
		industry = request.getParameter("industry_name");
		industry = industry.trim();
		if (industry == null || industry.trim().length() < 1) {
			errorMessage.put("industry", "行業別 不可為空");
		}

		/* 5 */
		str = request.getParameter("employees_number_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("employees_number", "受僱員工人數 不可為空");
		} else {
			try {
				employees_number = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("employees_number", "受僱員工人數欄位 格式錯誤");
			}
		}

		/* 6 */
		str = request.getParameter("total_salary_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("total_salary", "總薪資 不可為空");
		} else {
			try {
				total_salary = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("total_salary", "總薪資欄位 格式錯誤");
			}
		}

		/* 7 */
		str = request.getParameter("recurrent_salary_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("recurrent_salary", "經常性薪資 不可為空");
		} else {
			try {
				recurrent_salary = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("recurrent_salary", "經常性薪資欄位 格式錯誤");
			}
		}

		/* 8 */
		str = request.getParameter("non_recurring_salary_name");
		str = str.trim();
		if (str == null || str.trim().length() < 1) {
			errorMessage.put("non_recurring_salary", "非經常性薪資 不可為空");
		} else {
			try {
				non_recurring_salary = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println(e);
				errorMessage.put("non_recurring_salary", "非經常性薪資欄位 格式錯誤");
			}
		}

		/*
		 * If there is an error, call the view component and send back the error
		 * message.
		 */
		if (!errorMessage.isEmpty()) {
			requestDispatcherIndex.forward(request, response);
			return;
		}
		/* MemberBean 扮演封裝輸入資料的角色 */
		/* MemberBeans play the role of encapsulating input data. */
		EmpBean empBean = new EmpBean();
		empBean.setId(id);
		empBean.setDate(date);
		empBean.setJob_category(job_category);
		empBean.setIndustry(industry);
		empBean.setEmployees_number(employees_number);
		empBean.setTotal_salary(total_salary);
		empBean.setRecurrent_salary(recurrent_salary);
		empBean.setNon_recurring_salary(non_recurring_salary);

		try {
			EmpDAO empDAO = new EmpDAO();
			EmpBean empBeanOut = empDAO.insert(empBean);
			request.setAttribute("memberBean", empBeanOut);
			request.setAttribute("buttonType", singleChoice);
			RequestDispatcher requestDispatcherSuccess = request.getRequestDispatcher("InsertMemberSuccess.jsp");
			requestDispatcherSuccess.forward(request, response);
			return;
		} catch (SQLException e) {
			System.out.println(e);
			if (e.getMessage().indexOf("重複的索引鍵") != -1 || e.getMessage().indexOf("Duplicate entry") != -1) {
				errorMessage.put("id", "帳號重複，請重新輸入帳號");
			} else {
				errorMessage.put("exception", "資料庫存取錯誤");
			}
			requestDispatcherIndex.forward(request, response);
			return;
		}
	}// end of insertData method

}// end of InsertForm class

//
//
//doPost method
//System.out.println("insert_name = " + request.getParameter("insert_name"));
//System.out.println("update_name = " + request.getParameter("update_name"));
//System.out.println("update_name = " + request.getParameter("delete_name"));
//System.out.println("update_name = " + request.getParameter("select_name"));
//out2.println("insert_name = " + request.getParameter("insert_name") + "<br>");
//out2.println("update_name = " + request.getParameter("update_name") + "<br>");
//out2.println("update_name = " + request.getParameter("delete_name") + "<br>");
//out2.println("update_name = " + request.getParameter("select_name") + "<br>");
//out2.flush();
//out2.close();
//