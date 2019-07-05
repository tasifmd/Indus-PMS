import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PmsService {

  baseurl = environment.baseurl;
  constructor(private http: HttpClient) { }

  public postRequest(url: any, data: any): any {
    return this.http.post(this.baseurl + url, data, {
      headers: new HttpHeaders().set('token', localStorage.getItem('token'))
    });
  }

  public getRequest(url: any): any {
    return this.http.get(this.baseurl + url, {
      headers: new HttpHeaders().set('token', localStorage.getItem('token'))
    });
  }

  public putRequest(url: any, data: any): any {
    return this.http.put(this.baseurl + url, data, {
      headers: new HttpHeaders().set('token', localStorage.getItem('token'))
    });
  }

  public deleteRequest(url: any): any {
    return this.http.delete(this.baseurl + url, {
      headers: new HttpHeaders().set('token', localStorage.getItem('token'))
    });
  }

}
