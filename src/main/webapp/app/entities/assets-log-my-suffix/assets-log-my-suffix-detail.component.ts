import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';

@Component({
    selector: 'jhi-assets-log-my-suffix-detail',
    templateUrl: './assets-log-my-suffix-detail.component.html'
})
export class AssetsLogMySuffixDetailComponent implements OnInit {
    assetsLog: IAssetsLogMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assetsLog }) => {
            this.assetsLog = assetsLog;
        });
    }

    previousState() {
        window.history.back();
    }
}
