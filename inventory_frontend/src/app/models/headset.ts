import { Model } from "./model";

export class Headset {
    id: number;
    assetNumber: string;
    serialNumber: string;
    model: Model;
    status: string;
    createdAt: Date;
    updateAt: Date;
}
