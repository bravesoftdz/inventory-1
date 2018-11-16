import { Component, OnInit, ViewChild } from '@angular/core';
import { HeadsetService } from '../../../services/headset.service';
import { ModalDirective } from 'ngx-bootstrap';
import { Headset } from '../../../models/headset';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ModelService } from '../../../services/model.service';
import { Model } from '../../../models/model';

@Component({
  selector: 'app-headsets',
  templateUrl: './headsets.component.html',
  styleUrls: ['./headsets.component.css']
})
export class HeadsetsComponent implements OnInit {
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;

  selectedItem: Headset;
  newItem: Headset;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;


  models: Model[];
  error: string;
  constructor(private headsetService: HeadsetService,
              private modelService: ModelService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.initialize();
  }


  private initialize(){

    this.selectedItem = new Headset();
    this.newItem = new Headset();
    this.modelService
        .getModels()
        .subscribe(
          (res:any)=>{
            this.models = res;
          },
          (err:any)=>{
            console.log(err);
          }
        );

    this.headsetService
        .getHeadsets()
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
        serialNumber: [null, Validators.required],
        assetNumber: [null, Validators.required],
        model: [null, Validators.required]
      }
    );

    this.editItemForm =  this.formBuilder.group(
      {
        serialNumber: [null, Validators.required],
        assetNumber: [null, Validators.required],
        model: [null, Validators.required]
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

    
   // console.log(result);
  }
  showAddModal(){
    this.newItem = new Headset();
    this.add_modal.show();
  
  }

  addItem(){
    this.newItem.serialNumber = this.addItemForm.controls['serialNumber'].value;
    this.newItem.assetNumber = this.addItemForm.controls['assetNumber'].value;
    this.newItem.model = this.addItemForm.controls['model'].value;

    if(this.addItemForm.valid){
      this.headsetService
          .addHeadset(this.newItem)
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
    this.editItemForm.controls['serialNumber'].setValue(item.serialNumber);
    this.editItemForm.controls['assetNumber'].setValue(item.assetNumber);
    this.editItemForm.controls['model'].setValue(item.model);    
    this.edit_modal.show();
  }

  editItem(){
    this.selectedItem.serialNumber = this.editItemForm.controls['serialNumber'].value;
    this.selectedItem.assetNumber = this.editItemForm.controls['assetNumber'].value;
    this.selectedItem.model = this.editItemForm.controls['model'].value;
   
    if(this.editItemForm.valid){
      this.headsetService
          .updateHeadset(this.selectedItem)
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

 searchModel(term: string, item: Model){
    return item.name.toLowerCase().includes(term.toLowerCase());
  }
}
