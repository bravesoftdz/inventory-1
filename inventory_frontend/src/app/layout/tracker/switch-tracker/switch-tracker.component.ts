import { Component, OnInit, ViewChild } from '@angular/core';
import { Switch } from '../../../models/switch';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ModalDirective } from 'ngx-bootstrap';
import { SwitchConfig } from '../../../models/switch-config';
import { SwitchSlot } from '../../../models/switch-slot';
import { SwitchService } from '../../../services/switch.service';
import { SwitchConfService } from '../../../services/switch-conf.service';

@Component({
  selector: 'app-switch-tracker',
  templateUrl: './switch-tracker.component.html',
  styleUrls: ['./switch-tracker.component.css']
})
export class SwitchTrackerComponent implements OnInit {

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
  newItem: SwitchConfig;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;
  switchNumPorts:number[];

  newSwitchConfig: SwitchConfig;
  newSwitchSlots: SwitchSlot[];

  selectedPorts: number;
  switches: Switch;
  error: string;

  constructor(private switchService: SwitchService,
              private switchConfService: SwitchConfService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.initialize();
    this.switchNumPorts = [24,36,48,50];
    this.selectedPorts = 24;
    this.newItem =new SwitchConfig();
    this.newItem.portConfigs = this.createPortConfig(24);
    this.selectedItem ={
      source: null,
      portConfig: {
        switchPort: null,
        portPanel: null,
        vlan: null,
        trunkSwitch: null,
        trunkPort: null,
        department: null,
        remarks: null
      }
    };
  }

  initialize(){

    
    this.addItemForm = this.formBuilder.group(
      {
        switch: [null, Validators.required],
        switchConfig: [null, Validators.required]
      }
    );

    this.editItemForm = this.formBuilder.group(
      {
        switch: [null, Validators.required],
        switchConfig: [null, Validators.required]
      }
    );

    this.switchService
        .getSwitches()
        .subscribe(
          (res:any)=>{
            this.switches = res;
          },
          (err:any)=>{
            console.log(err);
          }
        );
    this.switchConfService
        .getSwitchConfigs()
        .subscribe(
          (res:any)=>{
            this.initPagination(res);
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }

  private initPagination(res:any){
    this.maxSize = 3;
    this.itemsPerPage = 10;
    this.currentPage = 1;
    this.data = res;
    this.filteredList = res;
    let begin = ((this.currentPage - 1) * this.itemsPerPage);
    let end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);
  }

  pageChange(event: any): void {
    const begin = ((event.page - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.data.slice(begin, end);
  }
  filterList(){

   // this.filteredList = this.cpuPipe.transform(this.data, this.search_keyword);
   
    //let result = this.cpuPipe.transform(this.data, this.search_keyword);
    this.filteredList = this.data.filter(
      (item:SwitchConfig)=>{
        return item.source.name.toLowerCase().includes(this.search_keyword.toLowerCase());
      }
    );
    
     this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);

    
   // console.log(result);
  }

  onNumPortChange(numPort:number){
    this.newItem.portConfigs = null;
    this.newItem.portConfigs = this.createPortConfig(numPort);
  }

  //helper function for creating list of port configuration of switch
  private createPortConfig(numPort: number) :SwitchSlot[]{
    let slots:SwitchSlot[] = [];

    for(let i =0; i<numPort; i++){
      let slot = new SwitchSlot();
      slot.switchPort = i+1;

      slots.push(slot);
    }
    return slots;
  }

  showAddModal(){
    this.newItem = new SwitchConfig();
    this.newItem.portConfigs = this.createPortConfig(24);
    console.log(this.newItem);
    this.add_modal.show();
    
  }

  addItem(){
    
  
    console.log(this.newItem);
    this.switchConfService
        .addSwitchConfig(this.newItem)
        .subscribe(
          (res:any)=>{
            console.log(res);
          },
          (err:any)=>{
            console.log(err);
          }
        );
    // if(this.addItemForm.valid){
    //   this.trService
    //       .addRoom(this.newItem)
    //       .subscribe(
    //         (res:any)=>{
    //           this.addItemForm.reset();
    //           this.initialize();
    //           this.add_modal.hide();
    //         },
    //         (err:any)=>{
    //           console.log(err);
    //         }
    //       );
    // }
    
  }

  showEditModal(sw: any,conf:any ){
     this.selectedItem = {source: sw, portConfig: conf};
    // this.editItemForm.controls['switch'].setValue(item.source);

    console.log(this.selectedItem);
     this.edit_modal.show();
  }

  editItem(){
  
    this.switchConfService
        .updateSwitchConfig(this.selectedItem)
        .subscribe(
          (res:any)=>{
            console.log(res);
            // this.editItemForm.reset();
            this.initialize();
            this.edit_modal.hide();
          },
          (err: any)=>{
            console.log(err);
            this.error ='Unable to process request';
          }
        );
    
  }
  searchSwitch(term: string, item: Switch){
    return item.name.toLowerCase().includes(term.toLowerCase());
  }
}
