package br.com.vh.employeemanager.model;

public record EmployeeDetailData(Long id, String name, String email, String phone, String jobTitle, String imageUrl, String employeeCode) {
    public EmployeeDetailData(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhone(), employee.getJobTitle(), employee.getImageUrl(), employee.getEmployeeCode());
    }
}
