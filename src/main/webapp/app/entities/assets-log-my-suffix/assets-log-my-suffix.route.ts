import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';
import { AssetsLogMySuffixService } from './assets-log-my-suffix.service';
import { AssetsLogMySuffixComponent } from './assets-log-my-suffix.component';
import { AssetsLogMySuffixDetailComponent } from './assets-log-my-suffix-detail.component';
import { AssetsLogMySuffixUpdateComponent } from './assets-log-my-suffix-update.component';
import { AssetsLogMySuffixDeletePopupComponent } from './assets-log-my-suffix-delete-dialog.component';
import { IAssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class AssetsLogMySuffixResolve implements Resolve<IAssetsLogMySuffix> {
    constructor(private service: AssetsLogMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((assetsLog: HttpResponse<AssetsLogMySuffix>) => assetsLog.body));
        }
        return of(new AssetsLogMySuffix());
    }
}

export const assetsLogRoute: Routes = [
    {
        path: 'assets-log-my-suffix',
        component: AssetsLogMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sample0802App.assetsLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'assets-log-my-suffix/:id/view',
        component: AssetsLogMySuffixDetailComponent,
        resolve: {
            assetsLog: AssetsLogMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sample0802App.assetsLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'assets-log-my-suffix/new',
        component: AssetsLogMySuffixUpdateComponent,
        resolve: {
            assetsLog: AssetsLogMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sample0802App.assetsLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'assets-log-my-suffix/:id/edit',
        component: AssetsLogMySuffixUpdateComponent,
        resolve: {
            assetsLog: AssetsLogMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sample0802App.assetsLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assetsLogPopupRoute: Routes = [
    {
        path: 'assets-log-my-suffix/:id/delete',
        component: AssetsLogMySuffixDeletePopupComponent,
        resolve: {
            assetsLog: AssetsLogMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sample0802App.assetsLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
