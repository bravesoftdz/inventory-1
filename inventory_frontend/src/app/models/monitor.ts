import { Model } from "./model";

export class Monitor {
    serialNumber: string;
    assetNumber: string;
    model: Model;
    status: string;
    createdAt: Date;
    updatedAt: Date;
}
