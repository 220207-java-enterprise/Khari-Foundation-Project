package com.revature.foundations.services;

import com.revature.foundations.daos.ReimbursementsDAO;

public class ReimbursementsService {

    private ReimbursementsDAO reimbursementsDAO;

    public ReimbursementsService(ReimbursementsDAO reimbursementsDAO) {
        this.reimbursementsDAO = reimbursementsDAO;

    }

    public ReimbursementsDAO getReimbursementsDAO() {
        return reimbursementsDAO;
    }

    public void setReimbursementsDAO(ReimbursementsDAO reimbursementsDAO) {
        this.reimbursementsDAO = reimbursementsDAO;
    }

    // methods to include:
    // createNewReimbursement(NewReimbursementRequest) returns ResourceCreationResponse

    // approveReimbursement(reimbursementId, resolverId) return void
    // denyReimbursement(reimbursementId, resolverId) return void




}
