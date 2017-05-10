package com.hao.onlineExam.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "role_id", nullable = false)
	private int roleId;
	
	@Column(name = "name", length = 1000, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "role")
	private Set<ExamUserRole> examUserRole;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ExamUserRole> getExamUserRole() {
		return examUserRole;
	}

	public void setExamUserRole(Set<ExamUserRole> examUserRole) {
		this.examUserRole = examUserRole;
	}
	
}
