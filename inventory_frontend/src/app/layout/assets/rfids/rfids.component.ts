import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { RfidService } from '../../../services/rfid.service';
import { RFID } from '../../../models/rfid';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Model } from '../../../models/model';

@Component({
  selector: 'app-rfids',
  templateUrl: './rfids.component.html',
  styleUrls: ['./rfids.component.css']
})
export class RfidsComponent implements OnInit {

  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;
  
  selectedItem: RFID;
  newItem: RFID;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;

  models: Model[];
  
  error: string;
  constructor(private rfidService: RfidService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.initialize();
  }


  private initialize(){

    this.rfidService
        .getRFIDs()
        .subscribe(
          (res:any)=>{
            this.initPagination(res);
          },
          (err:any)=>{
            console.log(err);
          }
        );

      this.addItemForm = this.formBuilder.group(
        {
          badgeNumber: [null, Validators.required],
          assetNumber: [null, Validators.required]
        }
      );
  
      this.editItemForm =  this.formBuilder.group(
        {
          badgeNumber: [null, Validators.required],
          assetNumber: [null, Validators.required]
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

     this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);

    
  }

  showAddModal(){
    this.newItem = new RFID();
    this.add_modal.show();
  
  }

  addItem(){
    this.newItem.badgeNumber = this.addItemForm.controls['badgeNumber'].value;
    this.newItem.assetNumber = this.addItemForm.controls['assetNumber'].value;
   
    if(this.addItemForm.valid){
      this.rfidService
          .addRFID(this.newItem)
          .subscribe(
            (res:any)=>{
              this.addItemForm.reset();
              this.initialize();
              this.add_modal.hide();
            },
            (err:any)=>{
              console.log(err);
            }
          );
    }
   
  }

  showEditModal(item){
    this.selectedItem = item;
    this.editItemForm.controls['badgeNumber'].setValue(item.badgeNumber);
    this.editItemForm.controls['assetNumber'].setValue(item.assetNumber);
    this.edit_modal.show();
  }

  editItem(){
    this.selectedItem.badgeNumber = this.editItemForm.controls['badgeNumber'].value;
    this.selectedItem.assetNumber = this.editItemForm.controls['assetNumber'].value;
  
    if(this.editItemForm.valid){
      this.rfidService
          .updateRFID(this.selectedItem)
          .subscribe(
            (res:any)=>{
              this.editItemForm.reset();
              this.initialize();
              this.edit_modal.hide();
            },
            (err: any)=>{
              console.log(err);
            }
          );
    }  
     
  }
 
}
