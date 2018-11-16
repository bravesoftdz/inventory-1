import { Component, OnInit } from '@angular/core';
import { SystemUnitsService } from '../../services/system-units.service';
import { SystemUnit } from '../../models/systemUnit';
import { TrackerService } from '../../services/tracker.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  pieChartLabels:string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales'];
  pieChartData:number[] = [300, 500, 100];
  pieChartType:string = 'pie';

  cpuLabels: string[] = [];
  cpuValues: number[] = [];

  cpuFilterCateg:string;

  chart: any;
  cpuByModel: any;
  cpuByDepartment: any;
  constructor(private cpuService: SystemUnitsService,
              private trackerService: TrackerService) { }

  ngOnInit() {
    this.cpuFilterCateg = 'department';
    // this.filterSystemUnits();
    // this.getProdAsset();
    this.cpuValues =[];
   

    this.loadCPUByModel();
    this.loadCPUByDepartment();
  }

  loadCPUByModel(){
    this.cpuService
        .getSystemUnitsByModel()
        .subscribe(
          (res:any)=>{
            this.cpuByModel =res;
          }
        );
  }
  

  loadCPUByDepartment(){
    this.cpuService
        .getSystemUnitsGroupByDepartment()
        .subscribe(
          (res:any)=>{
            this.cpuByDepartment = res;
          }
        );
  }
  getProdAsset(){
    this.trackerService
       .getProdAssets()
       .subscribe(
         (res:any)=>{
           console.log(res);
         }
       )
  }

  filterSystemUnits(){
    this.cpuLabels =[];
    this.cpuValues =[];
    if(this.cpuFilterCateg ==='department'){
      this.getSystemUnitsGroupByDepartment();
    }else{
      this.getSystemUnitsGroupByModel();
    }
    console.log('changed');
  }
  getSystemUnits(){
    this.cpuService
        .getSystemUnits()
        .subscribe(
          (res:any[])=>{
            console.log(res);
         
          },
          (err:any)=>{
            console.log(err);
          }
        )
  }
  getSystemUnitsGroupByDepartment(){
    this.cpuService
        .getSystemUnitsGroupByDepartment()
        .subscribe(
          (res:any)=>{
            console.log(res);
          },
          (err:any)=>{

          }
        )
  }

  getSystemUnitsGroupByModel(){
    this.cpuService
        .getSystemUnitsGroupByModel()
        .subscribe(
          (res:any)=>{
            let labels = [];
            let values = [];
            
            res.forEach(
              (item:any)=>{
                labels.push(item.label);
                values.push(item.value);
              }
            );
            
            this.cpuLabels = labels;
            this.cpuValues = values;
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }
  chartClicked(e:any): void{
    console.log(e);
  }

  chartHovered(e:any):void{
    console.log(e);
  }
}
