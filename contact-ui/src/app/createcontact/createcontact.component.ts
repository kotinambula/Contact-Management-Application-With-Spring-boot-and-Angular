import { Component,OnInit} from '@angular/core';
import { ContactService } from '../contact.service';
import { Contact } from '../contact';
import { response } from 'express';
import { Router } from '@angular/router';

@Component({
  selector: 'app-createcontact',
  templateUrl: './createcontact.component.html',
  styleUrl: './createcontact.component.css'
})
export class CreatecontactComponent  implements OnInit {

  ngOnInit(): void { }

  constructor(private service:ContactService,private router: Router){}
  onSubmit(){
    console.log(this.contact);
    this.saveContact();
  }

     contact:Contact= new Contact();
    msg:string="";

    saveContact(){
      this.service.createContact(this.contact).subscribe(response => {
            this.msg=response;
            this.redirectToContactList();
      });
    }
    redirectToContactList(){
      this.router.navigate(['/contacts']);
    }
  }

