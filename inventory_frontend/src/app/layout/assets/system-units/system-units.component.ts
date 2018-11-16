import { Component, OnInit, ViewChild } from '@angular/core';
import { SystemUnitsService } from '../../../services/system-units.service';
import { ModalDirective } from 'ngx-bootstrap';
import { ModelService } from '../../../services/model.service';
import { AccessService } from '../../../services/access.service';
import { SystemUnit } from '../../../models/systemUnit';
import { Model } from '../../../models/model';
import { SystemUnitPipe } from '../../../pipes/system-unit.pipe';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-system-units',
  templateUrl: './system-units.component.html',
  styleUrls: ['./system-units.component.css'],
  providers: [SystemUnitsService, ModelService, SystemUnitPipe]
})
export class SystemUnitsComponent implements OnInit {
  data: any[];
  filteredList: any[];
  currentPageList: any[];
  totalItems: number;
  currentPage: number;
  maxSize: number;
  itemsPerPage: number;
  numPageSelection = [5, 10, 20, 50, 100];
  search_keyword: string;
  closeResult: string;
  selectedItem: SystemUnit;

  models: any;

  @ViewChild('edit_modal')
  edit_modal: ModalDirective;

 @ViewChild('add_modal')
  add_modal: ModalDirective;

  editItemForm: FormGroup;
  addItemForm: FormGroup;

  currentUser: any;

  newSystemUnit: SystemUnit;

  error: string;
  constructor(private cpuService: SystemUnitsService,
              private modelService: ModelService,
              private accessService: AccessService,
              private formBuilder: FormBuilder,
              private cpuPipe: SystemUnitPipe) { }

  ngOnInit() {
    this.init();
    this.editItemForm = this.formBuilder.group(
      {
        name: [null, Validators.required],
        assetNumber: [null, Validators.required],
        serialNumber: [null, Validators.required],
        macAddress: [null, Validators.required],
        model: [null, Validators.required]
      }

      
    );

    this.addItemForm = this.formBuilder.group(
      {
        name: [null, Validators.required],
        assetNumber: [null, Validators.required],
        serialNumber: [null, Validators.required],
        macAddress: [null, Validators.required],
        model: [null, Validators.required]
      });

    this.currentUser = this.accessService.getUser();
  }


  canEdit(){
    return this.currentUser.role ==='USER';
  }


  getSystemUnits(){
    this.cpuService
        .getSystemUnits()
        .subscribe(
          (res:SystemUnit[])=>{
            this.initPagination(res);
          },
          (err:any)=>{
            console.log(err);
          }
        )
  }
  getModels(){

    this.modelService
        .getModels()
        .subscribe(
          (res:any)=>{
            this.models = res;
          },
          (err:any)=>{
            console.log(err);
          }
        )
  }
  pageChange(event: any): void {
    const begin = ((event.page - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.data.slice(begin, end);
  }
  filterList(){

    this.filteredList = this.cpuPipe.transform(this.data, this.search_keyword);
   
    //let result = this.cpuPipe.transform(this.data, this.search_keyword);

     this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);

    
   // console.log(result);
  }
  showAddModal(){
    this.newSystemUnit = new SystemUnit();
    this.addItemForm.reset();
    this.add_modal.show();
  }
  showEditModal(cpu){
    this.selectedItem = cpu;
    this.editItemForm = this.formBuilder.group(
      {
        name: [cpu.name, Validators.required],
        assetNumber: [cpu.assetNumber, Validators.required],
        serialNumber: [cpu.serialNumber, Validators.required],
        macAddress: [cpu.macAddress, Validators.required],
        model: [cpu.model, Validators.required]
      }
    );
    this.edit_modal.show();
  }
  private init(){

    this.selectedItem = new SystemUnit();
    this.newSystemUnit = new SystemUnit();


    this.getSystemUnits();
    this.getModels();

  }

  searchModel(term: string, item: Model){
    return item.name.toLowerCase().includes(term.toLowerCase());
  }

  private initPagination(res){
    this.maxSize = 3;
    this.itemsPerPage = 10;
    this.currentPage = 1;
    this.data = res;
    this.filteredList = res;
    let begin = ((this.currentPage - 1) * this.itemsPerPage);
    let end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);
  }
  addSystemUnit(){
    
    this.newSystemUnit.name = this.addItemForm.controls['name'].value;
    this.newSystemUnit.assetNumber = this.addItemForm.controls['assetNumber'].value;
    this.newSystemUnit.serialNumber = this.addItemForm.controls['serialNumber'].value;
    this.newSystemUnit.macAddress = this.addItemForm.controls['macAddress'].value;
    this.newSystemUnit.model = this.addItemForm.controls['model'].value;
  
    this.cpuService
        .addSystemUnit(this.newSystemUnit)
        .subscribe(
          (res:any)=>{
            this.init();
            this.add_modal.hide();
            console.log(res);
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }
  editSystemUnit(){
    this.selectedItem.name = this.editItemForm.controls['name'].value;
    this.selectedItem.assetNumber = this.editItemForm.controls['assetNumber'].value;
    this.selectedItem.serialNumber = this.editItemForm.controls['serialNumber'].value;
    this.selectedItem.macAddress = this.editItemForm.controls['macAddress'].value;
    this.selectedItem.model = this.editItemForm.controls['model'].value;
    this.cpuService
        .updateSystemUnit(this.selectedItem)
        .subscribe(
          (res:any)=>{
            this.init();
            this.selectedItem = new SystemUnit();
            this.edit_modal.hide();
          
          },
          (err:any)=>{
            console.log(err);
          }
        )
  }



}
