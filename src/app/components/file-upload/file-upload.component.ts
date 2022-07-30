import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Employee, FileuploadService } from 'src/app/Services/fileupload.service';




@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  displayedColumns: string[] =['empId','prefixName','firstName','lastName','gender','email','fatherName']
  dataSource:Employee[]=[]

  file: File | undefined
  loading = false
  flag:boolean=false


  constructor(private fileupload: FileuploadService) {

  }
  
  



  ngOnInit(): void {
    
  }

  selectfile(event: any) {
    // console.log(event);
    this.file = event.target.files[0]
    // console.log(this.file);


  }
  onUpload() {

    this.loading = true
    this.fileupload.SendPostRequest(this.file).subscribe(
      (response: any) => {
        console.log(response);
        this.loading = false
        alert(response.Message)

      },
      (error: any) => {
        console.log(error);
        this.loading = false
        alert(error.error)

      }
    )

  }
  employees: any
  id = 0

  GEtAll() {
    this.fileupload.GetAllEmployees().subscribe(
      (response: any) => {
        console.log(response);
        this.dataSource = response;
        this.flag=true
        
        // while (this.id < this.employees.length) {
        //   // console.log(this.employees[this.id]);
        //   // console.log("Name is : ",this.employees[this.id].firstName);
        //   // console.log("column names :  ",Object.keys(this.employees[this.id]));
          

        //   this.id++;
        // }


      },
      (error: any) => {
        console.log(error);

      }
    )
  }




}
