package com.sample;

public class EmpBean {
	// int, date, varchar(60), varchar(30), int, int, int, int。
	// id, date, job_category, industry, employees_number, total_salary,
	// recurrent_salary, non_recurring_salary。
	// -- id INT PRIMARY KEY, --編號
	// -- date DATE, --日期
	// -- job_category VARCHAR(60), --職類別
	// -- industry VARCHAR(30), --行業別
	// -- employees_number INT, --受僱員工人數
	// -- total_salary INT, --總薪資
	// -- recurrent_salary INT, --經常性薪資
	// -- non_recurring_salary INT --非經常性薪資

	private int id;// 編號 PRIMARY KEY
	private java.sql.Date date;// 日期
	private String job_category;// 職類別
	private String industry;// 行業別
	private int employees_number;// 受僱員工人數
	private int total_salary;// 總薪資
	private int recurrent_salary;// 經常性薪資
	private int non_recurring_salary;// 非經常性薪資

	public EmpBean() {
	}

	public EmpBean(int id, java.sql.Date date, String job_category, String industry, int employees_number,
			int total_salary, int recurrent_salary, int non_recurring_salary) {
		this.id = id;
		this.date = date;
		this.job_category = job_category;
		this.industry = industry;
		this.employees_number = employees_number;
		this.total_salary = total_salary;
		this.recurrent_salary = recurrent_salary;
		this.non_recurring_salary = non_recurring_salary;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("EmpBean [id=");
		stringBuilder.append(id);
		stringBuilder.append(", date=");
		stringBuilder.append(date);
		stringBuilder.append(", job_category=");
		stringBuilder.append(job_category);
		stringBuilder.append(", industry=");
		stringBuilder.append(industry);
		stringBuilder.append(", employees_number=");
		stringBuilder.append(employees_number);
		stringBuilder.append(", total_salary=");
		stringBuilder.append(total_salary);
		stringBuilder.append(", recurrent_salary=");
		stringBuilder.append(recurrent_salary);
		stringBuilder.append(", non_recurring_salary=");
		stringBuilder.append(non_recurring_salary);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public String getJob_category() {
		return job_category;
	}

	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getEmployees_number() {
		return employees_number;
	}

	public void setEmployees_number(int employees_number) {
		this.employees_number = employees_number;
	}

	public int getTotal_salary() {
		return total_salary;
	}

	public void setTotal_salary(int total_salary) {
		this.total_salary = total_salary;
	}

	public int getRecurrent_salary() {
		return recurrent_salary;
	}

	public void setRecurrent_salary(int recurrent_salary) {
		this.recurrent_salary = recurrent_salary;
	}

	public int getNon_recurring_salary() {
		return non_recurring_salary;
	}

	public void setNon_recurring_salary(int non_recurring_salary) {
		this.non_recurring_salary = non_recurring_salary;
	}
}
