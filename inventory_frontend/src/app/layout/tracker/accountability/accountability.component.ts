import { Component, OnInit, ViewChild } from '@angular/core';
import { AccountabilityService } from '../../../services/accountability.service';
import { FormControl, FormBuilder, FormGroup } from '@angular/forms';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-accountability',
  templateUrl: './accountability.component.html',
  styleUrls: ['./accountability.component.css']
})
export class AccountabilityComponent implements OnInit {
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
  uploadAttachment: any;

  allAsset: any[];
  @ViewChild('upload_modal')
  upload_modal: ModalDirective;

  @ViewChild('acc_modal')
  acc_modal: ModalDirective;

  @ViewChild('remarks_modal')
  remarks_modal: ModalDirective;

  newAcc: any;
  newAccAsset:any;
  selectedAcc: any;
  submitError: string;
  
  constructor(private accoutability: AccountabilityService) { }

  ngOnInit() {
    this.newAcc = {
      employee: null,
      employeeId: null,
      assetNumber: null,
      assetType: null
    };
    this.newAccAsset ={accId: null, accountability: null};
    this.selectedAcc = {remarks:null};
    this.allAsset =[];
    this.loadAccountabilities();
  }

  generateDoc(item:any){
    this.selectedItem = item;
    
    window.open('Inventory/accountability/download/'+item.id,'_blank');
  }
  getAllAsset(){
    this.accoutability
        .getAllAssets()
        .subscribe(
          (res:any)=>{
            this.allAsset = res;
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }
  showUploadModal(item:any){
    this.submitError = null;
    this.submitError = null;
    this.selectedAcc = item;
    this.upload_modal.show();
  }

  showRemarksModal(item:any){
    this.submitError ='';
    this.selectedAcc = item;
    console.log(this.selectedAcc);
    this.remarks_modal.show();
  }
  showAddAccModal(item:any){
    this.submitError = null;
    this.selectedItem = item;
    this.newAccAsset = { accId: item.id, accountability: null};

    //load fresh list of assets
    this.getAllAsset();
    this.acc_modal.show();
  }
  searchAsset(term: string, item: any){
    return item.assetNumber.toLowerCase().includes(term.toLowerCase());
  }


  addAccountableAsset(){
    this.accoutability
        .addAccountableAsset(this.newAccAsset)
        .subscribe(
          (res:any)=>{
            this.acc_modal.hide();
            this.loadAccountabilities();
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }
  addAccountability(){
    this.accoutability
        .addAccountability(this.newAcc)
        .subscribe(
          (res:any)=>{
            console.log(res);
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }
 updateRemarks(){
   this.accoutability
       .updateRemarks(this.selectedAcc)
       .subscribe(
         (res:any)=>{
           this.remarks_modal.hide();
           this.loadAccountabilities();
         },
         (err:any)=>{
          this.submitError ='Failed updating remarks';
         }
       );
 }
  upateAccountability(){  
    this.accoutability
        .updateAccountability(this.selectedAcc)
        .subscribe(
          (res:any)=>{
            console.log(res);
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }

  downloadAttachment(filename:string){
    window.open('Inventory/accountability/downloadAttachment/'+filename,'_blank');
  }
  uploadAccAttachment(){

    console.log(this.uploadAttachment);

    if(this.uploadAttachment){
        console.log(this.selectedAcc);

        let input = new FormData();
        input.append('file',this.uploadAttachment);
        input.append('data', JSON.stringify(this.selectedAcc));
        this.accoutability
            .uploadAttachment(input)
            .subscribe(
              (res:any)=>{
                console.log(res);
                this.loadAccountabilities();
                this.upload_modal.hide();
              },
              (err:any)=>{
                console.log(err);
                this.submitError ='Failed uploading file';
              }
            );
    }
  
  }
  
  private loadAccountabilities(){
    this.accoutability
        .getAccountabilities()
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

  searchItem(){
    this.filteredList = this.search();
    this.currentPage = 1;

    const begin = ((this.currentPage - 1) * this.itemsPerPage);
    const end = begin + this.itemsPerPage;
    this.currentPageList = this.filteredList.slice(begin, end);
  }
  onFileChange(event: any){
    let file = event.target.files[0];

    if(file){
      this.uploadAttachment = file;
      // console.log(file);
      // let reader = new FileReader();
      // reader.readAsDataURL(file);
      // reader.onload =()=>{
      //   console.log(reader.result);
      // }
    }
  }

  private search(){
  
      let result = this.data.filter(
        (item)=>{

          let column = item.employeeName + ' '+item.employeeNumber;                  
         
          return column.toLowerCase().includes(this.search_keyword.toLowerCase());
        }
      );

      return result;
    

  }
}
