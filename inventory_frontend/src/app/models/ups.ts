import { Model } from "./model";

export class Ups {
    id: number;
    serialNumber: string;
    assetNumber: string;
    createdAt: Date;
    updatedAt: Date;
    model: Model;
}
