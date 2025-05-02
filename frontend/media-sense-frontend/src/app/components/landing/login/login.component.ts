import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
})
export class LoginComponent {
  form: FormGroup;
  errorMessage: string | null = null;
  isSubmitting = false;
  loginSuccess = false;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    })
  }

  

  get email() { return this.form.get('email'); }
  get password() { return this.form.get('password'); }
  
  onSubmit() {
      this.form.markAllAsTouched();
      this.errorMessage = null;
  
      if (this.form.valid) {
        this.isSubmitting = true;
        
        const email = this.email?.value;
        const password = this.password?.value;
        
        this.authService.login(email, password)
          .pipe(
            finalize(() => this.isSubmitting = false)
          )
          .subscribe({
            next: () => {
              this.loginSuccess = true;
              this.router.navigate(['/']);
            },
            error: (error) => {
              if (typeof error === 'string') {
                this.errorMessage = error;
              } else if (error instanceof Error) {
                this.errorMessage = error.message;
              } else {
                this.errorMessage = 'Registration failed. Please try again.';
              }
            }
          });
      }
    }
}
