import { Moment } from 'moment';

export interface IAssetsLogMySuffix {
    id?: number;
    digitalCode?: string;
    custId?: number;
    securityAccountCode?: string;
    assetsType?: number;
    assetsCode?: string;
    assetsSubtype?: number;
    transDatetime?: Moment;
    tradeAmt?: number;
    feeAmt?: number;
    startBal?: number;
    endBal?: number;
    tradeObjType?: number;
    tradeObjId?: number;
    description?: string;
    remark?: string;
    employeeId?: number;
}

export class AssetsLogMySuffix implements IAssetsLogMySuffix {
    constructor(
        public id?: number,
        public digitalCode?: string,
        public custId?: number,
        public securityAccountCode?: string,
        public assetsType?: number,
        public assetsCode?: string,
        public assetsSubtype?: number,
        public transDatetime?: Moment,
        public tradeAmt?: number,
        public feeAmt?: number,
        public startBal?: number,
        public endBal?: number,
        public tradeObjType?: number,
        public tradeObjId?: number,
        public description?: string,
        public remark?: string,
        public employeeId?: number
    ) {}
}
