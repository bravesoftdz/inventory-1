import { Component, OnInit, ViewChild } from '@angular/core';
import { StationService } from '../../../services/station.service';
import { Station } from '../../../models/station';
import { ModalDirective } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.css']
})
export class StationComponent implements OnInit {
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  maxSize: number;
  numPageSelection = [5 ,10, 20 ,50, 100];
  search_keyword: string;
  
  selectedItem: Station;
  newItem: Station;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

  @ViewChild('add_modal')
  add_modal: ModalDirective;

  addItemForm: FormGroup;
  editItemForm: FormGroup;

  error: string;

  constructor(private stationService: StationService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.initialize();
  }


  private initialize(){
    this.stationService
        .getStations()
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
        stationName: [null, Validators.required],
        tower: null,
        department: null
      }
    );

    this.editItemForm =  this.formBuilder.group(
      {
        stationName: [null, Validators.required],
        tower: null,
        department: null
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
      (item:Station)=>{
        return item.stationName.toLowerCase().includes(this.search_keyword.toLowerCase());
      }
    );
    
    this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);

    
    // console.log(result);
  }

  showAddModal(){
    this.newItem = new Station();
    this.add_modal.show();
  
  }

  addItem(){
    this.newItem.stationName = this.addItemForm.controls['stationName'].value;
    this.newItem.tower = this.addItemForm.controls['tower'].value;
    this.newItem.department = this.addItemForm.controls['department'].value;
  
  
    if(this.addItemForm.valid){
      this.stationService
          .addStation(this.newItem)
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

  showEditModal(item: Station){
    this.selectedItem = item;
    this.editItemForm.controls['stationName'].setValue(item.stationName);
    this.editItemForm.controls['tower'].setValue(item.tower);
    this.editItemForm.controls['department'].setValue(item.department);
    this.edit_modal.show();
  }

  editItem(){
    this.selectedItem.stationName = this.editItemForm.controls['stationName'].value;
    this.selectedItem.tower = this.editItemForm.controls['tower'].value;
    this.selectedItem.department = this.editItemForm.controls['department'].value;
  
    if(this.editItemForm.valid){
      this.stationService
          .updateStation(this.selectedItem)
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
