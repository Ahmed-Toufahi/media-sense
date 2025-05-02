import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './signup.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
})
export class SignupComponent {
  form: FormGroup;
  isSubmitting = false;
  errorMessage: string | null = null;
  signupSuccess = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    }, { validators: this.passwordsMatch });
  }

  passwordsMatch(group: FormGroup) {
    const password = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;
    return password === confirm ? null : { mismatch: true };
  }

  get email() { return this.form.get('email'); }
  get password() { return this.form.get('password'); }
  get confirmPassword() { return this.form.get('confirmPassword'); }
  
  onSubmit() {
    this.form.markAllAsTouched();
    this.errorMessage = null;

    if (this.form.valid) {
      this.isSubmitting = true;
      
      const email = this.email?.value;
      const password = this.password?.value;
      
      this.authService.register(email, password)
        .pipe(
          finalize(() => this.isSubmitting = false)
        )
        .subscribe({
          next: () => {
            this.signupSuccess = true;
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