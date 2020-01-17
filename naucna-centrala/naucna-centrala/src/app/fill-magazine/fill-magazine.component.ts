import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository-service/repository.service';
import { AreaService } from '../services/areas-service/area.service';
import { MagazineService } from '../services/magazine-service/magazine.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-fill-magazine',
  templateUrl: './fill-magazine.component.html',
  styleUrls: ['./fill-magazine.component.css']
})
export class FillMagazineComponent implements OnInit {
  private formFieldsDto = null;
  private formFields = [];
  private processId = '';
  private username = '';
  private reviewers = [];
  private editors = [];
  // tslint:disable-next-line:max-line-length
  constructor(private route: ActivatedRoute, private userService: UserService, private magazineService: MagazineService, private areaService: AreaService, private repositoryService: RepositoryService) { 
    this.route.params.subscribe( params => {this.processId = params.process_id; });
    
    repositoryService.getTaskMag(this.processId).subscribe(
      res => {
        
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        
        this.userService.loadReviewers().subscribe(
          pom => {
            
            this.reviewers = pom;
           }
        );
        this.userService.loadEditors().subscribe(
          red => {
            
            this.editors = red;
           }
        );
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
     
     if (property != 'reviewersl' && property != 'editorsl' ) {
       o.push({fieldId : property, fieldValue : value[property]});
      } else {
       o.push({fieldId : property, categories : value[property]});

     }
  
    }

    this.magazineService.updateMagazine(o, this.formFieldsDto.taskId).subscribe(
      res => {
        
        alert('Radi!');
        window.location.href = 'login';
      },
      err => {

      }
    );
  }
}
