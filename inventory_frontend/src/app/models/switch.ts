import { Model } from "./model";

export class Switch {
    id: number;
    name: string;
    assetNumber: string;
    serialNumber: string;
    model: Model;
    macAddress: string;
    ipAddress: string;
    createdAt: Date;
    updateAt: Date;
}
