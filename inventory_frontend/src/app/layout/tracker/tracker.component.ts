import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { TrackerService } from '../../services/tracker.service';
import { CompileSummaryKind } from '@angular/compiler';
import { SystemUnitsService } from '../../services/system-units.service';
import { MonitorService } from '../../services/monitor.service';
import { KeyboardService } from '../../services/keyboard.service';
import { MouseService } from '../../services/mouse.service';
import { UpsService } from '../../services/ups.service';
import { SystemUnit } from '../../models/systemUnit';
import { Monitor } from '../../models/monitor';
import { Keyboard } from '../../models/keyboard';
import { Mouse } from '../../models/mouse';
import { Ups } from '../../models/ups';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-tracker',
  templateUrl: './tracker.component.html',
  styleUrls: ['./tracker.component.css']
})
export class TrackerComponent implements OnInit {
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

  @ViewChild('edit')
  edit: ModalDirective;

 @ViewChild('add')
  add: ModalDirective;

  @ViewChild('batch_deploy')
  batch_deploy: ModalDirective

  @ViewChild('deployRef') 
  deployRef: ElementRef;

  
  @ViewChild('batch_transfer')
  batch_transfer: ModalDirective

  @ViewChild('transferRef') 
  transferRef: ElementRef;

  @ViewChild('batch_pullout')
  batch_pullout: ModalDirective

  @ViewChild('edit_asset_modal')
  edit_asset_modal: ModalDirective

  @ViewChild('pulloutRef') 
  pulloutRef: ElementRef;

  editAssetForm: FormGroup;

  models: any[];

  cpus: SystemUnit[];
  monitors: Monitor[];
  keyboards: Keyboard[];
  mice: Mouse[];
  ups: Ups[];

  error: string;

  importError: string;
  importResponse: any;

  transferImportResponse: any;
  pulloutImportResponse: any;


  selectedFile: any;
  submitError: string;

  constructor(private trackerService: TrackerService,
              private cpuService: SystemUnitsService,
              private monitorService: MonitorService,
              private keyboardService: KeyboardService,
              private mouseService: MouseService,
              private upsService: UpsService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loadProdAssets();
    this.editAssetForm = this.formBuilder.group(
      {
        cpu: null,
        monitorA: null,
        monitorB: null,
        keyboard: null,
        mouse: null,
        ups: null
      }
    );
  }

  loadSystemUnits(){
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
          }
        );
  }


  loadKeyboards(){
    this.keyboardService
        .getKeyboards()
        .subscribe(
          (res:any)=>{
            this.keyboards = res;
          }
        )
  }
  loadMouse(){
    this.mouseService
        .getMice()
        .subscribe(
          (res:any)=>{
            this.mice = res;
          }
        );
  }
  loadUps(){
    this.upsService
        .getUps()
        .subscribe(
          (res:any)=>{
            this.ups = res;
          }
        );
  }
  loadProdAssets(){
    this.trackerService
        .getProdAssets()
        .subscribe(
          (res:any)=>{
            this.initPagination(res);
          }
        )
  }
  
  showEditAssetModal(item:any){
    this.submitError = null;
    
    let cpu = item.systemUnit;
    let monitorA = item.monitor1;
    let monitorB = item.monitor2;
    let keyboard = item.keyboard;
    let mouse = item.mouse;
    let ups = item.ups;
    
    this.selectedItem = item;
    
    this.editAssetForm.reset();
    this.editAssetForm.controls['cpu'].setValue(cpu);
    this.editAssetForm.controls['monitorA'].setValue(monitorA);
    this.editAssetForm.controls['monitorB'].setValue(monitorB);
    this.editAssetForm.controls['keyboard'].setValue(keyboard);
    this.editAssetForm.controls['mouse'].setValue(mouse);
    this.editAssetForm.controls['ups'].setValue(ups);

  
    this.loadSystemUnits();
    this.loadMonitors();
    this.loadKeyboards();
    this.loadMouse();
    this.loadUps();
    
    this.edit_asset_modal.show();

  }
  editAsset(){
    this.selectedItem.systemUnit = this.editAssetForm.controls['cpu'].value;
    this.selectedItem.monitor1 = this.editAssetForm.controls['monitorA'].value;
    this.selectedItem.monitor2 = this.editAssetForm.controls['monitorB'].value;
    this.selectedItem.keyboard = this.editAssetForm.controls['keyboard'].value;
    this.selectedItem.mouse = this.editAssetForm.controls['mouse'].value;
    this.selectedItem.ups = this.editAssetForm.controls['ups'].value;
  
    console.log(this.selectedItem);
    
    this.trackerService
        .updateProdAsset(this.selectedItem)
        .subscribe(
          (res:any)=>{
            this.loadProdAssets();
            this.edit_asset_modal.hide();
          },
          (err:any)=>{
            console.log(err);
            this.submitError = err.error.message;
          }
        );
  }
  showBatchDeployModal(){
    this.deployRef.nativeElement.value ='';
    this.submitError = '';
    this.importResponse = null;
    this.batch_deploy.show();
  }
  showBatchPulloutModal(){
    this.pulloutRef.nativeElement.value ='';
    this.submitError = '';
    this.pulloutImportResponse = null;
    this.batch_pullout.show();
  }
  showBatchTransferModal(){
    this.transferRef.nativeElement.value = '';
    this.submitError = '';
    this.transferImportResponse = null;
    this.batch_transfer.show();
  }
  onFileChange(event: any){
    this.importError = null;
    this.importResponse = null;
    let file = event.target.files[0];

    if(file){

      this.selectedFile = file;
      let input = new FormData();
      input.append('file',this.selectedFile);

      this.trackerService
          .batchDeployUpload(input)
          .subscribe(
            (res:any)=>{
              this.importResponse =res;
            },
            (err:any)=>{
              this.importError = err.error.message;
            }
          )
    }
  }

  onTransferFileChange(event:any){
    this.importError =null;
    this.submitError = null;
    this.transferImportResponse = null;
    this.batch_transfer.show();
    
    let file = event.target.files[0];
    let input = new FormData();

    input.append('file',file);

    if(file){
      this.trackerService
          .batchTransferUpload(input)
          .subscribe(
            (res:any)=>{
              this.transferImportResponse =res;
              console.log(res);
            },
            (err:any)=>{
              this.importError = err.error.message;
            }
      )
    }
  }

  onPulloutFileChange(event:any){
    this.importError =null;
    this.submitError = null;
    this.pulloutImportResponse = null;
    this.batch_pullout.show();
    
    let file = event.target.files[0];
    if(file){
      console.log(file);
    }
  }

  batchPullout(){

  }

  batchDeploy(){
    
    this.trackerService
        .batchDeployUpdate(this.importResponse)
        .subscribe(
          (res:any)=>{
            console.log(res);
            this.batch_deploy.hide();
          },
          (err:any)=>{
            console.log(err);
            this.submitError = 'Failed submitting request';
          }
        )
  }

  batchTransfer(){
    this.trackerService
        .batchTransferUpdate(this.transferImportResponse)
        .subscribe(
          (res:any)=>{
            console.log(res);
            this.batch_transfer.hide();
          },
          (err:any)=>{
            this.submitError = 'Failed submitting request';
            console.log(err);
          }
        )
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

    
   // console.log(result);
  }
  searchAsset(term: string, item: any){
    return item.assetNumber.toLowerCase().includes(term.toLowerCase());
  }
  searchItem(){
    this.filteredList = this.search();
    this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);
  }

  private search(){
  
      let result = this.data.filter(
        (item)=>{

          let mouse = item.mouse ? item.mouse.assetNumber : '';
          let keyboard =(item.keyboard) ? item.keyboard.assetNumber : '';
          let ups =(item.ups) ? item.ups.assetNumber : '';
          let systemUnit =(item.systemUnit) ? item.systemUnit.assetNumber: '';
          let column:string = item.location.name + ' '+mouse +' '+keyboard+' '+ups+' '+systemUnit;
                              
         
          return column.toLowerCase().includes(this.search_keyword.toLowerCase());
        }
      );

      return result;
    

  }

}
