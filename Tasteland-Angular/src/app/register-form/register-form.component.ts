import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { User } from '../model/User';
import { AuthService } from '../authentication/auth.service';
import { FileService } from '../services/file.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: { showError: true }
  }]
})
export class RegisterFormComponent implements OnInit {

  selectedFile: File;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private fileService: FileService) { }

  authDetailsFormGroup: FormGroup;
  basicUserInfoFormGroup: FormGroup;
  profilePictureFormGroup: FormGroup;

  ngOnInit() {
    this.authDetailsFormGroup = this.formBuilder.group({
      usernameControl: ['', Validators.required],
      passwordControl: ['', Validators.required],
      matchingPasswordControl: ['', Validators.required]
    });
    this.basicUserInfoFormGroup = this.formBuilder.group({
      firstnameControl: [''],
      lastnameControl: [''],
      dateOfBirthControl: [''],
      emailControl: ['', Validators.email],
      genderControl: [''],
      countryControl: ['']
    });
    this.profilePictureFormGroup = this.formBuilder.group({
      pictureControl: ['', Validators.required]
    });
  }

  register() {
    const user: User = {
      username: this.authDetailsFormGroup.get('usernameControl').value,
      password: this.authDetailsFormGroup.get('passwordControl').value,
      matchingPassword: this.authDetailsFormGroup.get('matchingPasswordControl').value,
      firstName: this.basicUserInfoFormGroup.get('firstnameControl').value,
      lastName: this.basicUserInfoFormGroup.get('lastnameControl').value,
      dateOfBirth: this.basicUserInfoFormGroup.get('dateOfBirthControl').value,
      email: this.basicUserInfoFormGroup.get('emailControl').value,
      gender: this.basicUserInfoFormGroup.get('genderControl').value,
      country: this.basicUserInfoFormGroup.get('countryControl').value,
      pictureUrl: this.profilePictureFormGroup.get('pictureControl').value
    };
    this.authService.register(user);
  }

  onFileChanged(event) {
    const file = event.target.files[0]
  }

  onUpload() {
    this.fileService.onUpload(this.selectedFile);
  }

  public findInvalidControls() {
    const invalid = [];
    const controls = this.basicUserInfoFormGroup.controls;
    for (const name in controls) {
        if (controls[name].invalid) {
           if(name === 'emailControl') {
             invalid.push('Incorrect email address!');
           } else if (name === 'dateOfBirthControl') {
            invalid.push('Incorrect date format!');
           }
        }
    }
    return invalid;
}

}
