package br.com.vh.employeemanager.model;

public record EmployeeDataList(Long id, String name, String email, String phone, String jobTitle, String imageUrl) {

    public EmployeeDataList(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhone(), employee.getJobTitle(), employee.getImageUrl());
    }

}
