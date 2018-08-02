import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Sample0802SharedModule } from 'app/shared';
import {
    RegionMySuffixComponent,
    RegionMySuffixDetailComponent,
    RegionMySuffixUpdateComponent,
    RegionMySuffixDeletePopupComponent,
    RegionMySuffixDeleteDialogComponent,
    regionRoute,
    regionPopupRoute
} from './';

const ENTITY_STATES = [...regionRoute, ...regionPopupRoute];

@NgModule({
    imports: [Sample0802SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RegionMySuffixComponent,
        RegionMySuffixDetailComponent,
        RegionMySuffixUpdateComponent,
        RegionMySuffixDeleteDialogComponent,
        RegionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        RegionMySuffixComponent,
        RegionMySuffixUpdateComponent,
        RegionMySuffixDeleteDialogComponent,
        RegionMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Sample0802RegionMySuffixModule {}
