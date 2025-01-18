import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyServiceService } from '../../services/company-service.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-update-ad',
  imports: [ReactiveFormsModule,CommonModule,],
  templateUrl: './update-ad.component.html',
  styleUrl: './update-ad.component.css'
})
export class UpdateAdComponent {
  // adId:any;

  // constructor(private companyServiceService:CompanyServiceService,
  //   private activatedroute:ActivatedRoute){}

    

  //   ngOnInit(){
  //     this.adId=this.activatedroute.snapshot.params['id']
  //     console.log('Route ID:', this.adId);
  //     this.getAdById();
  //   }


  //   getAdById(){
  //     this.companyServiceService.getAdById(this.adId).subscribe(res =>{
  //       console.log(res);
  //     })
  //   }
  adId:any;


    
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
   existingImage: string| null = null;

   imgChanged=false;
 
   constructor(private fb: FormBuilder,
     private router: Router,private companyServiceService:CompanyServiceService,  private activatedroute:ActivatedRoute){}
 
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
       description:[null, [Validators.required]],
       price:[null, [Validators.required]],
     })
     this.adId=this.activatedroute.snapshot.params['id']
     this.getAdById();
   }
 
   
     // onFileChange(event: any): void {
     //   this.img = event.target.files[0];
     // }
     onFileSelected(event:any) {
       this.selectedFile = event.target.files[0];
       this.previewImage();
       this.existingImage=null;
       this.imgChanged=true;
     }
 
     previewImage() {
       const reader = new FileReader();
       reader.onload = () => {
         this.imagePreview = reader.result;
       }
       reader.readAsDataURL(this.selectedFile);
     }
 
 
   
     updateAd() {
      
       const formData: FormData = new FormData();
       if(this.imgChanged && this.selectedFile){
         formData.append('img', this.selectedFile);
       }
       
       formData.append('serviceName', this.validateForm.get('serviceName')?.value);
       formData.append('description', this.validateForm.get('description')?.value);
       formData.append('price', this.validateForm.get('price')?.value);
 
 
       this.companyServiceService.updateAd(this.adId,formData).subscribe(
         res => {
           alert('SUCCESS: Ad updated Successfully!');
           this.router.navigateByUrl('/company/ads');
         },
         error => {
           alert(`ERROR: ${error.error}`);
         }
       );
     }
 



 getAdById(){
   this.companyServiceService.getAdById(this.adId).subscribe(res =>{
     console.log(res);
     this.validateForm.patchValue(res);
     this.existingImage='data:image/jpeg;base64,'+ res.returnedImg;
      })
    }
}


