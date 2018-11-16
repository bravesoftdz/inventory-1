import { Component, OnInit } from '@angular/core';
import { AccessService } from '../../services/access.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  providers: [AccessService]
})
export class HeaderComponent implements OnInit {

  user: any;
  constructor(private accessService: AccessService) { }

  ngOnInit() {
    this.user = this.accessService.getUser();
  }
  onLoggedOut(){
    this.accessService.logout();
  }
  toggleSidebar(){
    
  }
}
