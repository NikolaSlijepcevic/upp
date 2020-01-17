import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.modules';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
//import { RegistrationComponent } from './registration/registration.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NewMagazineComponent } from './new-magazine/new-magazine.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { ApproveReviewerComponent } from './approve-reviewer/approve-reviewer.component';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ActivationComponent } from './activation/activation.component';
import { FillMagazineComponent } from './fill-magazine/fill-magazine.component';
import { CheckingMagazineComponent } from './checking-magazine/checking-magazine.component';

@NgModule({
  declarations: [
    AppComponent,
    //RegistrationComponent,
    NewMagazineComponent,
    RegisterComponent,
    ApproveReviewerComponent,
    LoginComponent,
    HomepageComponent,
    ActivationComponent,
    FillMagazineComponent,
    CheckingMagazineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    HttpClientModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
