package br.com.vh.employeemanager.model;

public record NewEmployeeData(
        String name,
        String email,
        String phone,
        String jobTitle,
        String imageUrl){
}
