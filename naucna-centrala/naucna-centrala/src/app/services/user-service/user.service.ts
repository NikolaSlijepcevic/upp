import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }
  fetchUsers() {
    return this.httpClient.get<any>('http://localhost:8080/user/fetch');
  }

  registerUser(user, taskId) {
    return this.httpClient.post<any>('http://localhost:8080/register/post/'.concat(taskId), user);
  }

  loginUser(user) {
    return this.httpClient.post<any>('http://localhost:8080/users/login', user);
  }
  logoutUser() {
    return this.httpClient.get<any>('http://localhost:8080/users/logout');
  }
  approveReviewer(form, taskId) {
    return this.httpClient.post<any>('http://localhost:8080/register/approveReviewer/'.concat(taskId), form);
  }
  activateUser(form, taskId, username) {
    return this.httpClient.post<any>('http://localhost:8080/register/activateAccount/'.concat(taskId) + '/username/' + username, form);
  }
  loadReviewers() {
    return this.httpClient.get<any>('http://localhost:8080/users/getAllReviewers');
  }
  loadEditors() {
    return this.httpClient.get<any>('http://localhost:8080/users/getAllEditors');
  }
}
