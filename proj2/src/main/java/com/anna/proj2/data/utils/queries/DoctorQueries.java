package com.anna.proj2.data.utils.queries;

public class DoctorQueries {

    public static final String GET_ALL = "select d.doctor_id, d.first_name, d.last_name, d.middle_name, d.phone_num, d.e_mail, s.doctor_speciality, m.med_name, dep.department_name, o.office_num from doctor d inner join speciality s using (speciality_id) inner join medical_center m using (med_id) inner join department dep using (department_id) inner join doctor_office o using (office_id)";

    public static final String GET_BY_NAME_AND_SURNAME = "select d.doctor_id, d.first_name, d.last_name, d.middle_name, d.phone_num, d.e_mail, s.doctor_speciality, m.med_name, dep.department_name, o.office_num from doctor d inner join speciality s using (speciality_id) inner join medical_center m using (med_id) inner join department dep using (department_id) inner join doctor_office o using (office_id) where d.first_name = ? and d.last_name = ?";

    public static final String GET_BY_SPECIALITY = "select d.doctor_id, d.first_name, d.last_name, d.middle_name, d.phone_num, d.e_mail, s.doctor_speciality, m.med_name, dep.department_name, o.office_num from doctor d inner join speciality s using (speciality_id) inner join medical_center m using (med_id) inner join department dep using (department_id) inner join doctor_office o using (office_id) where s.doctor_speciality = ?";

    public static final String GET_BY_PASSWORD_AND_EMAIL = "select d.doctor_id, d.first_name, d.last_name, d.middle_name, d.phone_num, d.e_mail, s.doctor_speciality, m.med_name, dep.department_name, o.office_num from doctor d inner join speciality s using (speciality_id) inner join medical_center m using (med_id) inner join department dep using (department_id) inner join doctor_office o using (office_id) where d.password = ? and d.e_mail = ?";

    public static final String CREATE = "insert into doctor (first_name, last_name, middle_name, phone_num, e_mail, password, speciality_id, med_id, department_id, office_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE = "update doctor set first_name = ?, last_name = ?, middle_name = ?, phone_num = ?, e_mail = ?, password = ?, speciality_id = ?, med_id = ?, department_id = ?, office_id = ? where doctor_id = ?";

    public static final String DELETE = "delete from doctor where doctor_id = ?";

    public static final String GET_DOCTOR_DATA = "select o.office_id, d.department_id, s.speciality_id, m.med_id from doctor_office o, department d, speciality s, medical_center m where o.office_num = ? and d.department_name = ? and s.doctor_speciality = ? and m.med_name = ?";
}

