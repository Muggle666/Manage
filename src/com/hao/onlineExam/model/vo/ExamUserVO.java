package com.hao.onlineExam.model.vo;


public class ExamUserVO {
	
	private String userId;
	private String userName;
	private String tel;
	private String address;
	private String email;
	private String birthday;
	private String gender;
	private String password;
	private String last_lock_time;
	private int login_fail_count;
	private ExamScoreVO examScoreVO;
	
	

	public ExamUserVO(String userId, String userName, String tel, String address, String email, String birthday,
			String gender, String password, String last_lock_time, int login_fail_count, ExamScoreVO examScoreVO) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.tel = tel;
		this.address = address;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.password = password;
		this.last_lock_time = last_lock_time;
		this.login_fail_count = login_fail_count;
		this.examScoreVO = examScoreVO;
	}

	public ExamUserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLast_lock_time() {
		return last_lock_time;
	}

	public void setLast_lock_time(String last_lock_time) {
		this.last_lock_time = last_lock_time;
	}

	public int getLogin_fail_count() {
		return login_fail_count;
	}

	public void setLogin_fail_count(int login_fail_count) {
		this.login_fail_count = login_fail_count;
	}

	public ExamScoreVO getExamScoreVO() {
		return examScoreVO;
	}

	public void setExamScoreVO(ExamScoreVO examScoreVO) {
		this.examScoreVO = examScoreVO;
	}
	
	
}
