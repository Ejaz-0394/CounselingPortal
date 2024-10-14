package com.ez.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounsellorDao {

	 	private String counselorName;  
	    private String counselorEmail; //(unique)
	    private String counselorPwd;
	    private String counselorPhno;

	    // Constructors
	    public CounsellorDao() {}
}
