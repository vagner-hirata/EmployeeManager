package br.com.vh.employeemanager.repo;


import br.com.vh.employeemanager.exception.UserNotFoundException;
import br.com.vh.employeemanager.model.*;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity createEmployee(NewEmployeeData data, UriComponentsBuilder uriBuilder) {
        var employee = new Employee(data);
        employeeRepository.save(employee);
        var uri = uriBuilder.path("/employeemanager/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmployeeDetailData(employee));
    }

    public ResponseEntity<List<EmployeeDataList>> findAllEmployees() {
        var list = employeeRepository.findAll().stream().map(EmployeeDataList::new).toList();
        return ResponseEntity.ok(list);

    }

    public ResponseEntity updateEmployee(UpdateEmployeeData data) {
        if(!employeeRepository.existsById(data.id()))throw new UserNotFoundException("User by id " + data.id() + " was not found.");
        var employee = employeeRepository.getReferenceById(data.id());
        employee.updateData(data);
        return ResponseEntity.ok(new EmployeeDetailData(employee));
    }

    public ResponseEntity deleteEmployee(Long id) {
        if(!employeeRepository.existsById(id))throw new UserNotFoundException("User by id " + id + " was not found.");
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity findEmployeeById(Long id) {
        if(!employeeRepository.existsById(id))throw new UserNotFoundException("User by id " + id + " was not found.");
        var employee = employeeRepository.getReferenceById(id);
        return ResponseEntity.ok(new EmployeeDetailData(employee));

    }
}
