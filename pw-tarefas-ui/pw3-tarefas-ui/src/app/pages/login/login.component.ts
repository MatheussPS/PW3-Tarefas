import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../service/login.service';
//import { Router } from 'express';
import { Router } from '@angular/router';

// Matheus Pinter e Paulo Sergio

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  private formBuilder = inject(FormBuilder);
  private loginService = inject(LoginService);

  private router = inject(Router);

  loginForm!: FormGroup;
  errorMessage: string = '';

  constructor() {
    this.loginForm = this.formBuilder.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
    });
  }

  login() {
    if (this.loginForm.valid) {
      const username = this.loginForm.value.username;
      const password = this.loginForm.value.password;
      this.loginService.autenticar(username, password).subscribe({
        next: (value) => {
          this.router.navigateByUrl('/')
          this.loginForm.reset();
        },
        error: (err) => {
          console.log('Problema na autenticação', err)
        },
      })
    }
  }

}
