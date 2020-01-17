import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { User } from '../model/User';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private userService: UserService) { }

  ngOnInit() {
  }
  clickLogIn(){
    this.userService.loginUser(this.user).subscribe(
      res => {
        sessionStorage.setItem('loggedUser', JSON.stringify(res));
        window.location.href = '/homepage';
      },
      err => {
        alert('Neispravni kredencijali!');
      }
    );
  }
  registruj(){
    window.location.href = '/signup';
  }
}
