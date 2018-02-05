import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-crroot',
  templateUrl: './crroot.component.html',
  styleUrls: ['./crroot.component.scss']
})
export class CrrootComponent implements OnInit {
  
  title = 'Please choose a link.';
    
  constructor() { }

  ngOnInit() {
  }

}
