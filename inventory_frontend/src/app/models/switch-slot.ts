import { Model } from "./model";
import { Switch } from "./switch";

export class SwitchSlot {
 
    switchPort: number;
    portPanel: string;
    vLan: string;
    trunkSwitch: Switch;
    trunkPort: string;
    department: string;
    remarks:string;

}
