import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';

type EntityResponseType = HttpResponse<IAssetsLogMySuffix>;
type EntityArrayResponseType = HttpResponse<IAssetsLogMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class AssetsLogMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/assets-logs';

    constructor(private http: HttpClient) {}

    create(assetsLog: IAssetsLogMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(assetsLog);
        return this.http
            .post<IAssetsLogMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(assetsLog: IAssetsLogMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(assetsLog);
        return this.http
            .put<IAssetsLogMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAssetsLogMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAssetsLogMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(assetsLog: IAssetsLogMySuffix): IAssetsLogMySuffix {
        const copy: IAssetsLogMySuffix = Object.assign({}, assetsLog, {
            transDatetime: assetsLog.transDatetime != null && assetsLog.transDatetime.isValid() ? assetsLog.transDatetime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.transDatetime = res.body.transDatetime != null ? moment(res.body.transDatetime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((assetsLog: IAssetsLogMySuffix) => {
            assetsLog.transDatetime = assetsLog.transDatetime != null ? moment(assetsLog.transDatetime) : null;
        });
        return res;
    }
}
