import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Contact } from './contact';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
private backendurl="http://localhost:8080";
  constructor(private httpClient:HttpClient) { }

  createContact(contact:Contact):Observable<string>{
    return this.httpClient.post(`${this.backendurl}/contact`,contact,{responseType:"text"});
  }
  getContacts():Observable<Contact[]>{
    return this.httpClient.get<Contact[]>(`${this.backendurl}/contacts`);
  }

  getContactById(id:number):Observable<Contact>{
    return this.httpClient.get<Contact>(`${this.backendurl}/contact/${id}`);
  }
  deleteById(id:number):Observable<string>{
    return this.httpClient.delete(`${this.backendurl}/contact/${id}`,{responseType:"text"});
  }
}
