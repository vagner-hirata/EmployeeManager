import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiServerUrl = 'http://localhost:8080/employeemanager';

  constructor(private http: HttpClient) { }


  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/all`);
  }

  public addEmployees(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiServerUrl}/add`, employee)
  }

  public updateEmployees(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiServerUrl}/update`, employee)
  }

  public deleteEmployees(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/delete/${employeeId}`)
  }


}
