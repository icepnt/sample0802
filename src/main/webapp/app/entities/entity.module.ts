import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Sample0802RegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { Sample0802CountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { Sample0802LocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { Sample0802DepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { Sample0802TaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { Sample0802EmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { Sample0802JobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { Sample0802JobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
import { Sample0802AssetsLogMySuffixModule } from './assets-log-my-suffix/assets-log-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Sample0802RegionMySuffixModule,
        Sample0802CountryMySuffixModule,
        Sample0802LocationMySuffixModule,
        Sample0802DepartmentMySuffixModule,
        Sample0802TaskMySuffixModule,
        Sample0802EmployeeMySuffixModule,
        Sample0802JobMySuffixModule,
        Sample0802JobHistoryMySuffixModule,
        Sample0802AssetsLogMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Sample0802EntityModule {}
