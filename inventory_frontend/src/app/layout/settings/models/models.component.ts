import { Component, OnInit, ViewChild } from '@angular/core';
import { Model } from '../../../models/model';
import { ModalDirective } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ModelService } from '../../../services/model.service';

@Component({
  selector: 'app-models',
  templateUrl: './models.component.html',
  styleUrls: ['./models.component.css']
})
export class ModelsComponent implements OnInit {
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;
  
  selectedItem: Model;
  newItem: Model;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;

  error: string;

  constructor(private modelService: ModelService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.initialize();
  }


  private initialize(){
    this.modelService
        .getModels()
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
        name: [null, Validators.required]
      }
    );

    this.editItemForm =  this.formBuilder.group(
      {
        name: [null, Validators.required]
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
    this.newItem = new Model();
    this.add_modal.show();
  
  }

  addItem(){
    this.newItem.name = this.addItemForm.controls['name'].value;
  
    if(this.addItemForm.valid){
      this.modelService
          .addModel(this.newItem)
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

  showEditModal(item: Model){
    this.selectedItem = item;
    this.editItemForm.controls['name'].setValue(item.name);
    this.edit_modal.show();
  }

  editItem(){
    this.selectedItem.name = this.editItemForm.controls['name'].value;
  
    if(this.editItemForm.valid){
      this.modelService
          .updateModel(this.selectedItem)
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
