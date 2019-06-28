import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }


  onUpload(selectedFile: File) {
    const uploadData = new FormData();
    uploadData.append('myFile', selectedFile, selectedFile.name);
    this.http.post('my-backend.com/file-upload', uploadData)
      .subscribe(file => console.log(file));
  }
}
