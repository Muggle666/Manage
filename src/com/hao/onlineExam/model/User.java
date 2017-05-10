package com.hao.onlineExam.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exam_user")
//@Table(name = "manage")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
//	@GeneratedValue
	@Column(name = "user_id", length = 11, nullable = false)
	private String userId;
	
	
	@Column(name = "user_name", length = 11, nullable = false)
//	@NotEmpty(message = "姓名不可以为空！")
	private String userName;
	
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@Column(name = "tel", length = 11, nullable = false)
//	@NotEmpty(message = "电话不可以为空！")
	private String tel;
	
	@Column(name = "address", length = 11, nullable = false)
//	@NotEmpty(message = "地址不可以为空！")
	private String address;
	
	@Column(name = "birthday", length = 11, nullable = false)
//	@NotEmpty(message = "出生年月不可以为空！")
	private String birthday;
	
	@Column(name = "email", length = 11, nullable = false)
//	@NotEmpty(message = "郵箱不可以为空！")
	private String email;
	
	@Column(name = "gender", length = 11, nullable = false)
	private Integer gender;
	
	@Column(name = "last_lock_time", nullable = false)
	private String last_lock_time;
	
	@Column(name = "login_fail_count", nullable = false)
	private int login_fail_count;
	
	@OneToMany(mappedBy = "user")
	private Set<ExamUserSubject> examUserSubject;
	
	@OneToOne(mappedBy = "user")
	private ExamUserRole examUserRole;
	
//	@OneToMany(mappedBy = "user")
//	private Set<ExamScore> examScore;

	public User(String userId, String userName, String password, String tel, String address, String birthday,
			String email, Integer gender, String last_lock_time, int login_fail_count,
			Set<ExamUserSubject> examUserSubject, ExamUserRole examUserRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.tel = tel;
		this.address = address;
		this.birthday = birthday;
		this.email = email;
		this.gender = gender;
		this.last_lock_time = last_lock_time;
		this.login_fail_count = login_fail_count;
		this.examUserSubject = examUserSubject;
		this.examUserRole = examUserRole;
	}

	public User(){
		
	}
	
	public ExamUserRole getExamUserRole() {
		return examUserRole;
	}

	public void setExamUserRole(ExamUserRole examUserRole) {
		this.examUserRole = examUserRole;
	}

	public Set<ExamUserSubject> getExamUserSubject() {
		return examUserSubject;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setExamUserSubject(Set<ExamUserSubject> examUserSubject) {
		this.examUserSubject = examUserSubject;
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	
}
