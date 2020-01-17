import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewMagazineComponent } from './/new-magazine/new-magazine.component';
import { RegisterComponent } from './/register/register.component';
import { ApproveReviewerComponent } from './/approve-reviewer/approve-reviewer.component';
import { LoginComponent } from './/login/login.component';
import { HomepageComponent } from './/homepage/homepage.component';
import { ActivationComponent } from './/activation/activation.component';
import { FillMagazineComponent } from './/fill-magazine/fill-magazine.component';
import { CheckingMagazineComponent } from './/checking-magazine/checking-magazine.component';

const routes: Routes = [{
    path: '',
    component: LoginComponent
   },
   {
    path: 'signup',
    component: RegisterComponent
   },
   {
    path: 'newMagazine',
    component: NewMagazineComponent
   },
   {
    path: 'fillMagazine/:process_id',
    component: FillMagazineComponent
   },
   {
    path: 'checkingMagazine/:task_id',
    component: CheckingMagazineComponent
   },
   {
    path: 'approveReviewer/:process_id',
    component: ApproveReviewerComponent
   },
   {
    path: 'activate/:process_id/:username',
    component: ActivationComponent
   },
   {
    path: 'login',
    component: LoginComponent
   },
   {
    path: 'homepage',
    component: HomepageComponent
   }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
export class AppRoutingModule { }
