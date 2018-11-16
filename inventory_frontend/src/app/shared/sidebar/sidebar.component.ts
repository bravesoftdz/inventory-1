import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isActive: boolean = false;
  showMenu: string = '';
  pushRightClass: string = 'push-right';

  trackerToggled:boolean;
  assetsToggled:boolean;
  settingsToggled: boolean;
  constructor() { }

  ngOnInit() {
  }
  eventCalled() {
    this.isActive = !this.isActive;
  }

  addExpandClass(element: any) {
      this.toggleSubmenu(element);
      
      if (element === this.showMenu) {
          this.showMenu = '0';
      } else {
          this.showMenu = element;
      }
   
  }

  private toggleSubmenu(submenu:string){
    switch(submenu){
        case 'assets': this.assetsToggled = !this.assetsToggled; break;
        case 'tracker': this.trackerToggled = !this.trackerToggled; break;
        case 'settings': this.settingsToggled = !this.settingsToggled; break;
        default: break;
    }
  }

  isToggled(): boolean {
      const dom: Element = document.querySelector('body');
      return dom.classList.contains(this.pushRightClass);
  }

  toggleSidebar() {
      const dom: any = document.querySelector('body');
      dom.classList.toggle(this.pushRightClass);
  }

}
