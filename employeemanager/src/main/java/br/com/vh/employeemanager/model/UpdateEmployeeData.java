package br.com.vh.employeemanager.model;

public record UpdateEmployeeData(

        Long id,
        String name,
        String email,
        String jobTitle,
        String phone,
        String imageUrl) {
}
