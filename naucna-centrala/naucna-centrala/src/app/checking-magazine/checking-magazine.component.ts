import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-service/user.service';
import { RepositoryService } from '../services/repository-service/repository.service';
import { ActivatedRoute } from '@angular/router';
import { MagazineService } from '../services/magazine-service/magazine.service';

@Component({
  selector: 'app-checking-magazine',
  templateUrl: './checking-magazine.component.html',
  styleUrls: ['./checking-magazine.component.css']
})
export class CheckingMagazineComponent implements OnInit {
  private formFieldsDto = null;
  private formFields = [];
  private taskId = '';

  constructor(private userService: UserService, private magazineService:MagazineService, private repositoryService: RepositoryService, private route: ActivatedRoute) {
    this.route.params.subscribe( params => {this.taskId = params.task_id; });
    repositoryService.loadTask(this.taskId).subscribe(
      res => {    
        this.formFieldsDto = res;
        this.formFields = res.formFields;     
      },
      err => {
       
      }
    );
 }

  ngOnInit() {
  }
  onSubmit(value, form) {  
    const o = new Array();
    
    for (const property in value) {
      
        o.push({fieldId : property, fieldValue : value[property]});
     }
   
    this.magazineService.approveMagazine(o, this.formFieldsDto.taskId).subscribe(
      res => {   
        alert('Ok!');
        window.location.href = '';
      },
      err => {
        
      }
    );
  }

}
