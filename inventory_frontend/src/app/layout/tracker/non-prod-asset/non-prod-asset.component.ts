import { Component, OnInit, ViewChild } from '@angular/core';
import { NonProdService } from '../../../services/non-prod.service';
import { ModalDirective } from 'ngx-bootstrap';
import { SystemUnitsService } from '../../../services/system-units.service';
import { MonitorService } from '../../../services/monitor.service';
import { KeyboardService } from '../../../services/keyboard.service';
import { MouseService } from '../../../services/mouse.service';
import { UpsService } from '../../../services/ups.service';
import { NonProdCPU } from '../../../models/non-prod-cpu';
import { NonProdMonitor } from '../../../models/non-prod-monitor';
import { NonProdKeyboard } from '../../../models/non-prod-keyboard';
import { NonProdMouse } from '../../../models/non-prod-mouse';
import { NonProdUPS } from '../../../models/non-prod-ups';

@Component({
  selector: 'app-non-prod-asset',
  templateUrl: './non-prod-asset.component.html',
  styleUrls: ['./non-prod-asset.component.css']
})
export class NonProdAssetComponent implements OnInit {
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;
  selectedItem: any;

  newCPU: NonProdCPU;
  newMonitor: NonProdMonitor;
  newKeyboard: NonProdKeyboard;
  newMouse: NonProdMouse;
  newUPS: NonProdUPS;

  cpus: any;
  monitors: any;
  keyboards: any;
  mice: any;
  ups: any;

  error: string;
  
  @ViewChild('add_cpu_modal')
  add_cpu_modal: ModalDirective;

  @ViewChild('add_monitor_modal')
  add_monitor_modal: ModalDirective;

  @ViewChild('add_keyboard_modal')
  add_keyboard_modal: ModalDirective;
  
  @ViewChild('add_mouse_modal')
  add_mouse_modal: ModalDirective;

  
  @ViewChild('add_ups_modal')
  add_ups_modal: ModalDirective;

  constructor(private npService: NonProdService,
              private cpuService: SystemUnitsService,
              private monitorService: MonitorService,
              private keyboardService: KeyboardService,
              private mouseService: MouseService,
              private upsService: UpsService) { }

  ngOnInit() {
    
    this.loadNonProdAssets();
    this.newCPU = new NonProdCPU();
    this.newMonitor = new NonProdMonitor();
    this.newKeyboard = new NonProdKeyboard();
    this.newMouse = new NonProdMouse();
    this.newUPS = new NonProdUPS();

  }

  showAddCPUModal(item: any){
    
    this.newCPU = new NonProdCPU();
    this.newCPU.location = item;
    this.loadCPUS();
    this.add_cpu_modal.show();
  }
  showAddMonitorModal(item: any){
    this.newMonitor = new NonProdMonitor();
    this.newMonitor.location = item;
    this.loadMonitors();
    this.add_monitor_modal.show();
  }
  showAddKeyboardModal(item: any){
    this.newKeyboard = new NonProdKeyboard();
    this.newKeyboard.location = item;
    this.loadKeyboards();
    this.add_keyboard_modal.show();
  }
  showAddMouseModal(item: any){
    this.newMouse = new NonProdMouse();
    this.newMouse.location = item;
    this.loadMice();
    this.add_mouse_modal.show();
  }
  showAddUPSModal(item: any){
    this.newUPS = new NonProdUPS();
    this.newUPS.location = item;
    this.loadUPS();
    this.add_ups_modal.show();
  }
  addCPU(){
    this.npService
        .addNonProdCPU(this.newCPU)
        .subscribe(
          (res:any)=>{
            console.log(res);
            this.loadNonProdAssets();
            this.add_cpu_modal.hide();
          }
        );
  }
  addMonitor(){
    this.npService
        .addNonProdMonitor(this.newMonitor)
        .subscribe(
          (res:any)=>{
            console.log(res);
            this.loadNonProdAssets();
            this.add_monitor_modal.hide();
          }
        );
  }

  addKeyboard(){
    this.npService
        .addNonProdKeyboard(this.newKeyboard)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
            this.add_keyboard_modal.hide();
          }
        );
  }

  addMouse(){
    this.npService
        .addNonProdMouse(this.newMouse)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
            this.add_mouse_modal.hide();
          }
        )
  }

  addUPS(){
    this.npService
        .addNonProdUPS(this.newUPS)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
            this.add_ups_modal.hide();
          }
        );
  }

  deleteSystemUnit(location:any, cpu:any){
    let nonProdCPU = new NonProdCPU();
    nonProdCPU.location = location;
    nonProdCPU.cpu = cpu;

    this.npService
        .deleteNonProdCPU(nonProdCPU)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
          }
        )
  }

  deleteMonitor(location: any, monitor: any){
    let nonProdMonitor = new NonProdMonitor();
    nonProdMonitor.location = location;
    nonProdMonitor.monitor = monitor;
    this.npService
        .deleteNonProdMonitor(nonProdMonitor)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
          }
        );
  }
  deleteKeyboard(location:any, keyboard: any){
    let nonProdKeyboard = new NonProdKeyboard();
    nonProdKeyboard.location = location;
    nonProdKeyboard.keyboard = keyboard;

    this.npService
        .deleteNonProdKeyboard(nonProdKeyboard)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
          }
        );
  }
  deleteMouse(location: any, mouse: any){
    let nonProdMouse = new NonProdMouse();
    nonProdMouse.location = location;
    nonProdMouse.mouse = mouse;
    this.npService
        .deleteNonProdMouse(nonProdMouse)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
          }
        );
  }
  deleteUps(location:any, ups: any){
    let nonProdUps = new NonProdUPS();
    nonProdUps.location = location;
    nonProdUps.ups = ups;
    this.npService
        .deleteNonProdUPS(nonProdUps)
        .subscribe(
          (res:any)=>{
            this.loadNonProdAssets();
          }
        );
  }

  loadNonProdAssets(){
    this.npService
        .getNonProdAssets()
        .subscribe(
          (res:any)=>{
            this.initPagination(res);
          },
          (err:any)=>{
            console.log(err);
          }
        )
  }
  loadCPUS(){
    this.cpuService
        .getSystemUnits()
        .subscribe(
          (res:any)=>{
            this.cpus = res;
          }
        );
  }
  loadMonitors(){
    this.monitorService
        .getMonitors()
        .subscribe(
          (res:any)=>{
            this.monitors = res;
          },
          (err:any)=>{
            console.log(err);
          }
        )
  }
  loadKeyboards(){
    this.keyboardService
        .getKeyboards()
        .subscribe(
          (res:any)=>{
            this.keyboards = res;
          }
        );
  }
  loadMice(){
    this.mouseService
        .getMice()
        .subscribe(
          (res:any)=>{
            this.mice = res;
          }
        )
  }
  loadUPS(){
    this.upsService
        .getUps()
        .subscribe(
          (res:any)=>{
            this.ups = res;
          }
        );
  }
  searchItem(term: string, item: any){
    return item.assetNumber.toLowerCase().includes(term.toLowerCase());
  }

  private initPagination(res:any){
    this.maxSize = 3;
    this.itemsPerPage = 20;
    this.currentPage = 1;
    this.data = res;
    this.filteredList = res;
    let begin = ((this.currentPage - 1) * this.itemsPerPage);
    let end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);
    console.log(this.currentPageList);
  }

  pageChange(event: any): void {
    const begin = ((event.page - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.data.slice(begin, end);
  }

  filterList(){
    this.currentPage = 1;
    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);
  }



}
