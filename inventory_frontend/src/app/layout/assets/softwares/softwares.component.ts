import { Component, OnInit, ViewChild } from '@angular/core';
import { SoftwareService } from '../../../services/software.service';
import { ModalDirective } from 'ngx-bootstrap';
import { Software } from '../../../models/software';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-softwares',
  templateUrl: './softwares.component.html',
  styleUrls: ['./softwares.component.css']
})
export class SoftwaresComponent implements OnInit {

  
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;
  
  selectedItem: Software;
  newItem: Software;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;

  error: string;
  constructor(private softwareService: SoftwareService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.initialize();
  }


  private initialize(){
    this.softwareService
        .getSoftwares()
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
        softwareName: [null, Validators.required],
        softwareUser: [null, Validators.required]
      }
    );

    this.editItemForm =  this.formBuilder.group(
      {
        softwareName: [null, Validators.required],
        softwareUser: [null, Validators.required]
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
    this.newItem = new Software();
    this.add_modal.show();
  
  }

  addItem(){
    this.newItem.softwareName = this.addItemForm.controls['softwareName'].value;
    this.newItem.softwareUser = this.addItemForm.controls['softwareUser'].value;
   
    if(this.addItemForm.valid){
      this.softwareService
          .addSoftware(this.newItem)
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

  showEditModal(item: Software){
    this.selectedItem = item;
    this.editItemForm.controls['softwareName'].setValue(item.softwareName);
    this.editItemForm.controls['softwareUser'].setValue(item.softwareUser);
    this.edit_modal.show();
  }

  editItem(){
    this.selectedItem.softwareName = this.editItemForm.controls['softwareName'].value;
    this.selectedItem.softwareUser = this.editItemForm.controls['softwareUser'].value;
  
    if(this.editItemForm.valid){
      this.softwareService
          .updateSoftware(this.selectedItem)
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
