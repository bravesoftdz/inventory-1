import { Component, OnInit, ViewChild } from '@angular/core';
import { NonProdLocation } from '../../../models/non-prod-location';
import { ModalDirective } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NonProdLocationService } from '../../../services/non-prod-location.service';

@Component({
  selector: 'app-non-prod-location',
  templateUrl: './non-prod-location.component.html',
  styleUrls: ['./non-prod-location.component.css']
})
export class NonProdLocationComponent implements OnInit {
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;
  
  selectedItem: NonProdLocation;
  newItem: NonProdLocation;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;

  error: string;
  constructor(private npService: NonProdLocationService,
              private formBuilder: FormBuilder) {  }

  ngOnInit() {
    this.initialize();
  }

  loadNonProdLocations(){
    this.npService
        .getNonProdLocations()
        .subscribe(
          (res:any)=>{
            this.initPagination(res);
          }
        );
  }
  initialize(){
    this.loadNonProdLocations();
    this.addItemForm = this.formBuilder.group(
      {
        locationName: [null, Validators.required]
      }
    );

    this.editItemForm = this.formBuilder.group(
      {
        locationName: [null, Validators.required]
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
      (item:NonProdLocation)=>{
        return item.locationName.toLowerCase().includes(this.search_keyword.toLowerCase());
      }
    );

      this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);

    
    // console.log(result);
  }

  
  showAddModal(){
    this.newItem = new NonProdLocation();
    this.add_modal.show();
  
  }

  addItem(){
    this.newItem.locationName = this.addItemForm.controls['locationName'].value;
  
    if(this.addItemForm.valid){
      this.npService
          .addNonProdLocation(this.newItem)
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

  showEditModal(item: NonProdLocation){
    this.selectedItem = item;
    this.editItemForm.controls['locationName'].setValue(item.locationName);
    this.edit_modal.show();
  }

  editItem(){
    this.selectedItem.locationName = this.editItemForm.controls['locationName'].value;
  
    if(this.editItemForm.valid){
      this.npService
          .updateNonProdLocation(this.selectedItem)
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
