import { Model } from "./model";
import { Switch } from "./switch";
import { SwitchSlot } from "./switch-slot";

export class SwitchConfig {
    id: number;
    source: Switch;
    portConfigs: SwitchSlot[];
}
