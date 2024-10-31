import { Component,OnInit } from '@angular/core';
import { ContactService } from '../contact.service';
import { response } from 'express';
import { Contact } from '../contact';
import { Router } from '@angular/router';


@Component({
  selector: 'app-contactlist',
  templateUrl: './contactlist.component.html',
  styleUrl: './contactlist.component.css'
})
export class ContactlistComponent implements OnInit {

  constructor(private service:ContactService, private router: Router ){}

  contacts:Contact[]=[ ];
  deleteMsg:string="";
  ngOnInit(): void {
    this.getContacts();
  }

  getContacts(){
    this.service.getContacts().subscribe(response => {
      this.contacts=response;
    });
  }

  editContact(id:number){
    this.router.navigate(['edit/',id]);
  }

  deleteContact(id:number){
    this.service.deleteById(id).subscribe(response =>
      {
        this.deleteMsg=response;
        this.getContacts();
      });
      
  }
}



