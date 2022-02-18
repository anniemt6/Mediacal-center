package com.anna.proj2.data.utils.queries;

public class TasksQueries {

    public static final String TASK_1 = "select d.doctor_id, d.first_name, d.last_name, d.middle_name, p.first_name, p.last_name, p.middle_name from doctor d, patient p, request r, speciality s where d.doctor_id = r.doctor_id and p.patient_id = r.patient_id and d.speciality_id = (select speciality_id from speciality where doctor_speciality = 'Кардиолог') group by d.doctor_id";

    public static final String TASK_2 = "";

    public static final String TASK_3 = "";
}
