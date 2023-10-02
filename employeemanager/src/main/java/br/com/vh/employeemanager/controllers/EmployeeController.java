package br.com.vh.employeemanager.controllers;

import br.com.vh.employeemanager.model.NewEmployeeData;
import br.com.vh.employeemanager.model.UpdateEmployeeData;
import br.com.vh.employeemanager.model.EmployeeDataList;
import br.com.vh.employeemanager.repo.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/employeemanager")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @GetMapping("/all")
        public ResponseEntity<List<EmployeeDataList>> getAllEmployees() {
                return employeeService.findAllEmployees();

        }
        @PostMapping("/add")
        public ResponseEntity createEmployee(@RequestBody NewEmployeeData data, UriComponentsBuilder uri) {
                return employeeService.createEmployee(data, uri);
        }

        @PutMapping("/update")
        @Transactional
        public ResponseEntity updateEmployee(@RequestBody UpdateEmployeeData data) {
                return employeeService.updateEmployee(data);
        }
        @DeleteMapping("/delete/{id}")
        @Transactional
        public ResponseEntity deleteEmployee(@PathVariable Long id) {
                return employeeService.deleteEmployee(id);
        }

        @GetMapping("/find/{id}")
        public ResponseEntity findEmployeeById(@PathVariable Long id) {
                return employeeService.findEmployeeById(id);
        }



}
