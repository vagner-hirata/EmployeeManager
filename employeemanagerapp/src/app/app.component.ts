import { EmployeeService } from './employee.service';
import { HttpErrorResponse } from '@angular/common/http'
import { Employee } from './employee';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  public employees: Employee[] = [];
  public editEmployee: Employee | undefined;
  deleteEmployee: Employee | undefined;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getEmployees();
  }


  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      {
        next: (response: Employee[]) => {
          this.employees = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    );
  }

  public onAddEmployee(addForm: NgForm): void {
    document.getElementById('add-employee-form')?.click()
    this.employeeService.addEmployees(addForm.value).subscribe(
      {
        next: (response: Employee) => {
          console.log(response);
          this.getEmployees();
          addForm.reset();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
      }
    )
  }


  public onUpdateEmployee(employee: Employee): void {
    this.employeeService.updateEmployees(employee).subscribe(
      {
        next: (response: Employee) => {
          console.log(response);
          this.getEmployees();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    )
  }

  public onDeleteEmployee(employeeId: number): void {
    this.employeeService.deleteEmployees(employeeId).subscribe(
      {
        next: (response: void) => {
          this.getEmployees();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    )
  }

  public searchEmployees(key: string): void {
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }

  }

  public onOpenModal(employee: Employee | null, mode: string): void {

    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-bs-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editEmployee = employee!;
      button.setAttribute('data-bs-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee!;
      button.setAttribute('data-bs-target', '#deleteEmployeeModal');
    }
    container?.appendChild(button);
    button.click();
  }

}
