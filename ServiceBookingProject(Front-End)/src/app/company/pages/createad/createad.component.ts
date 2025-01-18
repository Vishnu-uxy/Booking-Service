import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { CompanyServiceService } from '../../services/company-service.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-createad',
  imports: [ReactiveFormsModule,RouterOutlet,CommonModule,FormsModule,RouterLink],
  templateUrl: './createad.component.html',
  styleUrl: './createad.component.css'
})
export class CreateadComponent {
  serviceName: string = '';
  description: string = '';
  price: number | null = null;
  img: File | null = null;
  userId!: number ; // Replace with dynamic user ID if required
  isLoading = false;
  message: string = '';
  
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  validateForm!: FormGroup;


  constructor(private fb: FormBuilder,
    private router: Router,private companyServiceService:CompanyServiceService){}

  //   ngOnInit(): void {
  //     // Get userId from route parameters
  //     this.userId = this.userService.getUserId(); // Fetch dynamic userId
  //   console.log('Dynamic User ID:', this.userId);
  //   if (!this.userId) {
  //     this.router.navigateByUrl('/login'); // Redirect if no userId found
  //   }
  // }
  ngOnInit() {
    this.validateForm = this.fb.group ({
      serviceName:[null, [Validators.required]],
      category:[null,[Validators.required]],
      description:[null, [Validators.required]],
      price:[null, [Validators.required]],
    })
  }

  
    // onFileChange(event: any): void {
    //   this.img = event.target.files[0];
    // }
    onFileSelected(event:any) {
      this.selectedFile = event.target.files[0];
      this.previewImage();
    }

    previewImage() {
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      }
      reader.readAsDataURL(this.selectedFile);
    }


  
    postAd() {
     
      const formData: FormData = new FormData();

      formData.append('img', this.selectedFile);
      formData.append('serviceName', this.validateForm.get('serviceName')?.value);
      formData.append('category', this.validateForm.get('category')?.value)
      formData.append('description', this.validateForm.get('description')?.value);
      formData.append('price', this.validateForm.get('price')?.value);


      this.companyServiceService.postAd(formData).subscribe(
        res => {
          alert('SUCCESS: Ad Posted Successfully!');
          this.router.navigateByUrl('/company/ads');
        },
        error => {
          alert(`ERROR: ${error.error}`);
        }
      );
    }

  }
    
