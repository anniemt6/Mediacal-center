package com.anna.proj2.data.utils.queries;

public class StoredProceduresAndFunctions {

    public static final String CANCEL_REQUEST = "{call cancel_request(?)}";

    public static final String VISIT_TO_DOCTOR_AMOUNT = "{?= call visit_to_doctor_amount(?)}";
}


