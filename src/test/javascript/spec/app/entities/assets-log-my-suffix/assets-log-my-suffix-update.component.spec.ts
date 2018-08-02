/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Sample0802TestModule } from '../../../test.module';
import { AssetsLogMySuffixUpdateComponent } from 'app/entities/assets-log-my-suffix/assets-log-my-suffix-update.component';
import { AssetsLogMySuffixService } from 'app/entities/assets-log-my-suffix/assets-log-my-suffix.service';
import { AssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';

describe('Component Tests', () => {
    describe('AssetsLogMySuffix Management Update Component', () => {
        let comp: AssetsLogMySuffixUpdateComponent;
        let fixture: ComponentFixture<AssetsLogMySuffixUpdateComponent>;
        let service: AssetsLogMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Sample0802TestModule],
                declarations: [AssetsLogMySuffixUpdateComponent]
            })
                .overrideTemplate(AssetsLogMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AssetsLogMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssetsLogMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AssetsLogMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.assetsLog = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AssetsLogMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.assetsLog = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
