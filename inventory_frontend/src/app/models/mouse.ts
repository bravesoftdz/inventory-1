import { Model } from "./model";

export class Mouse {
    id: number;
    assetNumber: string;
    serialNumber: string;
    model: Model;
    status: string;
    createdAt: Date;
    updateAt: Date;
}
