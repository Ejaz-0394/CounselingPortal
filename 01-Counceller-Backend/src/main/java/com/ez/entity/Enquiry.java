package com.ez.entity;



import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer eId;//  (PK)
	
	private String studentName;
	
	private String studentPhno;
	
	private String courseName;
	
	private String classMode;
	
	private String enqStatus;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@ManyToOne
	@JoinColumn(name = "c_id")
	private Counsellor counsellor;
	
	public Enquiry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enquiry(Integer eId, String studentName, String studentPhno, String courseName, String classMode,
			String enqStatus, LocalDateTime createdDate, LocalDateTime updatedDate, Counsellor counsellor) {
		super();
		this.eId = eId;
		this.studentName = studentName;
		this.studentPhno = studentPhno;
		this.courseName = courseName;
		this.classMode = classMode;
		this.enqStatus = enqStatus;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.counsellor = counsellor;
	}

	

	
	

}
