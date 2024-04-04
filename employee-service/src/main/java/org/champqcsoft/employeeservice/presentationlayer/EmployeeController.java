package org.champqcsoft.employeeservice.presentationlayer;


import org.champqcsoft.employeeservice.buisnesslayer.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping()
    public ResponseEntity<List<EmployeeResponseModel>> getEmployees(){
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> getEmployeeByEmployeeId(@PathVariable String employeeId){
        return ResponseEntity.ok().body(employeeService.getEmployeeByEmployeeIdentifier_employeeId(employeeId));
    }
    @PostMapping
    public ResponseEntity<EmployeeResponseModel> createEmployee(@RequestBody EmployeeRequestModel employeeRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRequestModel));
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> updateEmployee(@RequestBody EmployeeRequestModel employeeRequestModel, @PathVariable String employeeId){
        return ResponseEntity.ok().body(employeeService.updateEmployee(employeeRequestModel, employeeId));
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeId){
        employeeService.removeEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
