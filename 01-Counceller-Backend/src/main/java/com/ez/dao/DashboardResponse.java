package com.ez.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardResponse {

	private int totalEnquiries;
    private int openEnquiries;
    private int lostEnquiries;
    private int enrolledEnquiries;
    
    public DashboardResponse(int totalEnquiries, int openEnquiries, int lostEnquiries, int enrolledEnquiries) {
        this.totalEnquiries = totalEnquiries;
        this.openEnquiries = openEnquiries;
        this.lostEnquiries = lostEnquiries;
        this.enrolledEnquiries = enrolledEnquiries;
    }
}
