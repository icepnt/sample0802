import { Moment } from 'moment';
import { IJobMySuffix } from 'app/shared/model//job-my-suffix.model';
import { IAssetsLogMySuffix } from 'app/shared/model//assets-log-my-suffix.model';

export interface IEmployeeMySuffix {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    hireDate?: Moment;
    salary?: number;
    commissionPct?: number;
    departmentId?: number;
    jobs?: IJobMySuffix[];
    logs?: IAssetsLogMySuffix[];
    managerId?: number;
}

export class EmployeeMySuffix implements IEmployeeMySuffix {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public phoneNumber?: string,
        public hireDate?: Moment,
        public salary?: number,
        public commissionPct?: number,
        public departmentId?: number,
        public jobs?: IJobMySuffix[],
        public logs?: IAssetsLogMySuffix[],
        public managerId?: number
    ) {}
}
