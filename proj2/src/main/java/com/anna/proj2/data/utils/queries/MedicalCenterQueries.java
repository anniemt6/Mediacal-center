package com.anna.proj2.data.utils.queries;

public class MedicalCenterQueries {

    public static final String GET_MEDICAL_CENTER_DATA = "select * from medical_center";

    public static final String UPDATE_MEDICAL_CENTER_DATA = "update medical_center set med_name = ?, e_mail = ?, phone_num = ?, adresse = ? where med_id = ?";
}
