/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Sample0802TestModule } from '../../../test.module';
import { AssetsLogMySuffixDeleteDialogComponent } from 'app/entities/assets-log-my-suffix/assets-log-my-suffix-delete-dialog.component';
import { AssetsLogMySuffixService } from 'app/entities/assets-log-my-suffix/assets-log-my-suffix.service';

describe('Component Tests', () => {
    describe('AssetsLogMySuffix Management Delete Component', () => {
        let comp: AssetsLogMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<AssetsLogMySuffixDeleteDialogComponent>;
        let service: AssetsLogMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Sample0802TestModule],
                declarations: [AssetsLogMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(AssetsLogMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AssetsLogMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssetsLogMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
