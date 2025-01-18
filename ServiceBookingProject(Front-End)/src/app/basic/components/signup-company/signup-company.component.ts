import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router, RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-signup-company',
  imports: [ReactiveFormsModule,CommonModule,RouterOutlet],
  templateUrl: './signup-company.component.html',
  styleUrl: './signup-company.component.css'
})
export class SignupCompanyComponent {


  validateForm!: FormGroup;
      
  constructor(private fb:FormBuilder,
    private authService:AuthService,
    private router:Router){}

    ngOnInit(){
      this.validateForm=this.fb.group({
        email:[null,[Validators.email,Validators.required,Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$')]],
        name:[null,[Validators.required]],
        address:[null,[Validators.required]],
        phone:[null,[Validators.pattern(/^\d{10}$/)]],
        password:[null,[Validators.required,Validators.minLength(6)]],
        confirmPassword:[null,[Validators.required]],
  },
  {
    validator: this.matchPasswords('password', 'confirmPassword'), // Custom validator
  }
);
}

  submitForm(){
    if (this.validateForm.invalid) {
      alert('Please fill in all required fields correctly.');
      return;
    }

    this.authService.registerCompany(this.validateForm.value).subscribe(res => {
      alert('Signup successful');
    
      this.router.navigateByUrl('/login');
    }, error => {
      alert('Error: ' + error.error);
    });

  }
  



matchPasswords(password: string, confirmPassword: string) {
  return (formGroup: FormGroup) => {
    const pass = formGroup.controls[password];
    const confirmPass = formGroup.controls[confirmPassword];
    if (confirmPass.errors && !confirmPass.errors['passwordMismatch']) {
      return;
    }
    if (pass.value !== confirmPass.value) {
      confirmPass.setErrors({ passwordMismatch: true });
    } else {
      confirmPass.setErrors(null);
    }
  };
}




}
