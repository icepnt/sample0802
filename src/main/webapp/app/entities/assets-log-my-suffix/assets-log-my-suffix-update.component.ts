import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';
import { AssetsLogMySuffixService } from './assets-log-my-suffix.service';
import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';
import { EmployeeMySuffixService } from 'app/entities/employee-my-suffix';

@Component({
    selector: 'jhi-assets-log-my-suffix-update',
    templateUrl: './assets-log-my-suffix-update.component.html'
})
export class AssetsLogMySuffixUpdateComponent implements OnInit {
    private _assetsLog: IAssetsLogMySuffix;
    isSaving: boolean;

    employees: IEmployeeMySuffix[];
    transDatetime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private assetsLogService: AssetsLogMySuffixService,
        private employeeService: EmployeeMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ assetsLog }) => {
            this.assetsLog = assetsLog;
        });
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployeeMySuffix[]>) => {
                this.employees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.assetsLog.transDatetime = moment(this.transDatetime, DATE_TIME_FORMAT);
        if (this.assetsLog.id !== undefined) {
            this.subscribeToSaveResponse(this.assetsLogService.update(this.assetsLog));
        } else {
            this.subscribeToSaveResponse(this.assetsLogService.create(this.assetsLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAssetsLogMySuffix>>) {
        result.subscribe((res: HttpResponse<IAssetsLogMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEmployeeById(index: number, item: IEmployeeMySuffix) {
        return item.id;
    }
    get assetsLog() {
        return this._assetsLog;
    }

    set assetsLog(assetsLog: IAssetsLogMySuffix) {
        this._assetsLog = assetsLog;
        this.transDatetime = moment(assetsLog.transDatetime).format(DATE_TIME_FORMAT);
    }
}
