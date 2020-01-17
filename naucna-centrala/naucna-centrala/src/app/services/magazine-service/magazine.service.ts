import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  constructor(private httpClient: HttpClient, private http: Http) { }

  updateMagazine(magazine, taskId) {
    console.log('update magazine');
    console.log(magazine);
    return this.httpClient.post('http://localhost:8080/magazine/update/'.concat(taskId), magazine) as Observable<any>;
  }
  approveMagazine(form, taskId){
    return this.httpClient.post('http://localhost:8080/magazine/approveMagazine/'.concat(taskId), form) as Observable<any>;

  }

}
