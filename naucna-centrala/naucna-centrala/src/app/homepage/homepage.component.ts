import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-service/user.service';
import { RepositoryService } from '../services/repository-service/repository.service';
import { User } from '../model/User';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private tasks = [];
  private user: User;
  private type = '';
  constructor(private userService: UserService, private repositoryService: RepositoryService) {
    this.user  = JSON.parse(sessionStorage.getItem('loggedUser'));
    
    this.type = this.user.type;
    this.repositoryService.getTasksOfUser(this.user.username).subscribe(
      res => {
        
        this.tasks = res;
      },
      err => {
      }
    );
   }

  ngOnInit() {

  }
  magForm() {
    window.location.href = 'newMagazine';
  }
  complete(taskId){
    this.repositoryService.completeTask(taskId);
   }
  logout() {   
    this.userService.logoutUser().subscribe(
      res => {
        //alert('Izlogovani ste!');
        sessionStorage.setItem('loggedUser', null);
        window.location.href = 'login';
      },
      err => {
      }
    );
}
}
