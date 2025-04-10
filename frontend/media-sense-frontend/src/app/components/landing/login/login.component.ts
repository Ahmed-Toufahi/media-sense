import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
})
export class LoginComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    })
  }

  

  get email() { return this.form.get('email'); }
  get password() { return this.form.get('password'); }
  
  onSubmit() {
    this.form.markAllAsTouched();

    if (this.form.valid) {
      console.log("Form submitted:", this.form.value);
    }
  }
}
