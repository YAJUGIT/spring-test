package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "Employee", value="Employee CRUD Operations")
public interface EmployeeController extends BaseController{
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee records are created",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the delete User's Pref", response = String.class))})
    @PostMapping(value = "/employee", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO);


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success : Retrieved Employee Records for a given Id ",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the get Employee", response = Employee.class)),
            @ApiResponse(code = 404, message = "Record Not Found : Request record do not exists ",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the get Employee", response = Employee.class)),
            @ApiResponse(code = 400, message = "Bad Request : Invalid Request",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the get Employee", response = Employee.class)),
    })
    @GetMapping(value = "/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> getEmployee(@PathVariable int id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success : Retrieved Employee Records for a given Id ",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the get Employee", response = Employee.class)),
            @ApiResponse(code = 404, message = "Record Not Found : Request record do not exists ",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the get Employee", response = Employee.class)),
            @ApiResponse(code = 400, message = "Bad Request : Invalid Request",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the get Employee", response = Employee.class)),
    })
    @GetMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> getEmployees();


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee is Updated for the given Id ",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the delete User's Pref", response = String.class))})
    @PutMapping(value = "/employee", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO employeeDTO);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee is Deleted for the given Id ",
                    responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the delete User's Pref", response = String.class))})
    @DeleteMapping(value = "/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteEmployee(@PathVariable int id);
}
