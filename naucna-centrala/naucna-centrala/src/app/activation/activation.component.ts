import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-service/user.service';
import { RepositoryService } from '../services/repository-service/repository.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit { 
  private formFieldsDto = null;
  private formFields = [];
  private processId = '';
  private username = '';
  constructor(private userService: UserService, private repositoryService: RepositoryService, private route: ActivatedRoute) { 
    this.route.params.subscribe( params => {this.processId = params.process_id; this.username = params.username; });
    repositoryService.getTask(this.processId).subscribe(
      res => {       
        this.formFieldsDto = res;
        this.formFields = res.formFields;
      },
      err => {
        console.log('Error occured');
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
  
   this.userService.activateUser(o, this.formFieldsDto.taskId, this.username).subscribe(
      res => {
        
        alert('Uspjesna aktivacija!');
        window.location.href = 'login';
      },
      err => {
        console.log('greska');
      }
    );
  }
}
