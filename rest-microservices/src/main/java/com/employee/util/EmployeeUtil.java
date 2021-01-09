package com.employee.util;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.Optional;

public class EmployeeUtil {

  /*  public static void validateJobList(EmployeeDTO employeeDTO){
        if(StringUtils.isEmpty(employeeDTO.getPref().getJobName())){
            throw new EmployeeException(EmployeeError.CUSTOM_JOB_NAME_IS_NULL);
        }
        if(StringUtils.isEmpty(employeeDTO.getUserId())){
            throw new EmployeeException(EmployeeError.USER_ID_IS_NULL);
        }
    }*/

    public static Optional<EmployeeDTO> getJobDto(Employee employee) {
        return Optional.of(EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .build());
    }

    public static Employee getEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        return employee;
    }


    public static String getJsonData(Object teams) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //  String str = teams.toString().replaceAll("\\\\","");
            return new JSONTokener(mapper.writeValueAsString(teams)).nextValue().toString();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodeJson(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONObject newJSON = jsonObject.getJSONObject("teams");
        return newJSON.toString();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getJSonObject(String jsonString) {
        try {
            return (new JSONObject(jsonString));
        } catch (JSONException ioException) {
            System.err.println(ioException);
        }
        return null;
    }
}
