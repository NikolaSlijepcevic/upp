import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';
import { RepositoryService } from '../services/repository-service/repository.service';
import { AreaService } from '../services/areas-service/area.service';
import { MagazineService } from '../services/magazine-service/magazine.service';
@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.css']
})
export class NewMagazineComponent implements OnInit {
  
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private enumValues = [];
  private categories = [];

  constructor(private magazineService: MagazineService, private areaService: AreaService, private repositoryService: RepositoryService) { 
    repositoryService.startMagazineProcess().subscribe(
      res => {     
        this.formFieldsDto = res;
        this.formFields = res.formFields;

        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) => {
          if ( field.type.name === 'enum') {
            this.enumValues = Object.keys(field.type.values);
          }
        });
        this.areaService.loadAreas().subscribe(
          res => {
            this.categories = res;
           }
        );
      },
      err => {      
      }
    );
  }
  ngOnInit() {
  }

  onSubmit(value, form) {
    const o = new Array();

    // for (const property in value) {
    //  if (property != 'areas' ) {
    //   o.push({fieldId : property, fieldValue : value[property]});
    //   } else {
    //    o.push({fieldId : property, categories : value[property]});
    //  }
    // }

    for (const property in value) {
      o.push({fieldId : property, fieldValue : value[property]});
    }
    let cat= new Array(); 
       for(let a of this.categories){
          if (a.isChecked)
            cat.push(a.id);
       }
    o.push({fieldId : "areas", categories : cat});


    this.repositoryService.createMagazine(o, this.formFieldsDto.taskId).subscribe(
      res => {
        alert('Uspjesno!');
        window.location.href = 'fillMagazine/' + this.processInstance;
      },
      err => {
      }
    );
  }
}
