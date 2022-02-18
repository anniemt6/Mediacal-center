package com.anna.proj2.data.utils.queries;

public class PatientQueries {

    public static final String GET_BY_ID = "select * from patient where patient_id = ?";

    public static final String GET_BY_EMAIL_AND_PASSWORD = "select * from patient where e_mail = ? and password = ?";

    public static final String CREATE = "insert into patient (first_name, last_name, middle_name, phone_num, e_mail, password, date_of_birth, adresse, sex, medical_history_num) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE = "update patient set first_name = ?, last_name = ?, middle_name = ?, phone_num = ?, e_mail = ?, password = ?, date_of_birth = ?, adresse = ?, sex = ?, medical_history_num = ? where patient_id = ?";

    public static final String DELETE = "delete from patient where patient_id = ?";
}


