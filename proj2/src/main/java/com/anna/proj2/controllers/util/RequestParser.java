package com.anna.proj2.controllers.util;

import com.anna.proj2.pojo.Request;

public class RequestParser {

    public static RequestUi toRequestUi(Request request) {

        return new RequestUi(
                request.getId(),
                request.getPatientId(),
                request.getTimetableId(),
                request.getDoctorId(),
                request.getVisit(),
                DateTimeParser.timeToString(request.getVisitTime())
        );
    }

    public static Request toRequest(RequestUi requestUi) {

        return new Request(
                requestUi.getId(),
                requestUi.getVisit(),
                DateTimeParser.stringToTimeIfFailureReturnNull(requestUi.getVisitTime()),
                requestUi.getPatientId(),
                requestUi.getTimetableId(),
                requestUi.getDoctorId()
        );
    }
}
