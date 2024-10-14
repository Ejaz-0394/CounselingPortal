package com.ez.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Counsellor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cId;
	
	private String counsellorName;  
	
	@Column(unique=true)
	private String counsellorEmail; //(unique)
	
	private String counsellorPwd;
	
	private String counsellorPhno;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;                                                   

	public Counsellor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Counsellor(int cId, String counsellorName, String counsellorEmail, String counsellorPwd,
			String counsellorPhno, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.cId = cId;
		this.counsellorName = counsellorName;
		this.counsellorEmail = counsellorEmail;
		this.counsellorPwd = counsellorPwd;
		this.counsellorPhno = counsellorPhno;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Counsellor [cId=" + cId + ", counsellorName=" + counsellorName + "]";
	}
	
	

}
