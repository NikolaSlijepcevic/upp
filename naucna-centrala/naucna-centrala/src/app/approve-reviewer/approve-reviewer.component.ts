import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-service/user.service';
import { RepositoryService } from '../services/repository-service/repository.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-approve-reviewer',
  templateUrl: './approve-reviewer.component.html',
  styleUrls: ['./approve-reviewer.component.css']
})
export class ApproveReviewerComponent implements OnInit {
  private formFieldsDto = null;
  private formFields = [];
  private taskId = '';
  constructor(private userService: UserService, private repositoryService: RepositoryService, private route: ActivatedRoute) { 
    this.route.params.subscribe( params => {this.taskId = params.process_id; });
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
   

    // tslint:disable-next-line:forin
    for (const property in value) {
        o.push({fieldId : property, fieldValue : value[property]});
     }

    this.userService.approveReviewer(o, this.formFieldsDto.taskId).subscribe(
      res => {
        alert('Uspjesno!');
        window.location.href = '';
      },
      err => {
      }
    );
  }
}
