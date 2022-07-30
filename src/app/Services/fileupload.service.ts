import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Employee {
  empId: number;
  namePrefix: string;
  firstName: string;
  lastName: string;
  gender: string;
  email: string;
  fatherName: string;

}


@Injectable({
  providedIn: 'root'
})
export class FileuploadService {

  baseurl='http://localhost:7373'

  constructor(private http: HttpClient) { }

  SendPostRequest(file:any){
    let formData=new FormData()
    formData.append("file",file)

    return this.http.post(this.baseurl+'/excel/upload',formData)
  }

  GetAllEmployees():Observable<Employee[]>{
    return this.http.get<Employee[]>(this.baseurl+'/excel/employees')
  }
}
