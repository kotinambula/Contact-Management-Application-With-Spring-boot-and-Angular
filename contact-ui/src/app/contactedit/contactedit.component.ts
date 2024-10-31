import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Contact } from '../contact';
import { ContactService } from '../contact.service';
import { response } from 'express';

@Component({
  selector: 'app-contactedit',
  templateUrl: './contactedit.component.html',
  styleUrls: ['./contactedit.component.css']
})
export class ContacteditComponent implements OnInit {

  contact:Contact=new Contact();
  id:number=0;
  constructor(private service: ContactService,
    private router:Router,private activeRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.getContact();
  }
  getContact(){
    this.id=this.activeRouter.snapshot.params['id'];
    console.log("UPDATED ID ::"+this.id);
    this.service.getContactById(this.id).subscribe(
      response=>{
        console.log("GETTING A CONTACT..");
        console.log(response);
        this.contact=response;
      },
      error=>{
        console.log("SOMETHING WENT WRONG DURING GETTING A CONTACT..");
        console.log(error);  
      }
    );
  }
  updateContact(){
    console.log("UPDATED ..");
    this.service.createContact(this.contact).subscribe(
      response=>{
        console.log("UPDATING A CONTACT..");
        console.log(response);
        this.router.navigate(['/contacts'])
      },
      error=>{
        console.log("SOMETHING WENT WRONG DURING UPDATING A CONTACT..");
        console.log(error);
      });
    }
}
