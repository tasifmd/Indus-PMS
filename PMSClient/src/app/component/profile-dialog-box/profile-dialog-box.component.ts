import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-profile-dialog-box',
  templateUrl: './profile-dialog-box.component.html',
  styleUrls: ['./profile-dialog-box.component.scss']
})
export class ProfileDialogBoxComponent implements OnInit {

  imageChangedEvent: any = '';
  croppedImage: any = '';
  constructor(public dialogRef: MatDialogRef<ProfileDialogBoxComponent>) { }

  ngOnInit() {
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
  }

  imageCropped(event: any) {
    console.log(event);
    this.croppedImage = event;
  }

  setProfile() {
    if (this.croppedImage != null) {
      this.dialogRef.close(this.croppedImage);
    }
  }

  close(){
    this.dialogRef.close();
  }

}
