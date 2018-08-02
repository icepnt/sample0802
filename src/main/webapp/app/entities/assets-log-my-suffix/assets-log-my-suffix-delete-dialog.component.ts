import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';
import { AssetsLogMySuffixService } from './assets-log-my-suffix.service';

@Component({
    selector: 'jhi-assets-log-my-suffix-delete-dialog',
    templateUrl: './assets-log-my-suffix-delete-dialog.component.html'
})
export class AssetsLogMySuffixDeleteDialogComponent {
    assetsLog: IAssetsLogMySuffix;

    constructor(
        private assetsLogService: AssetsLogMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assetsLogService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'assetsLogListModification',
                content: 'Deleted an assetsLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-assets-log-my-suffix-delete-popup',
    template: ''
})
export class AssetsLogMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assetsLog }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AssetsLogMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.assetsLog = assetsLog;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
