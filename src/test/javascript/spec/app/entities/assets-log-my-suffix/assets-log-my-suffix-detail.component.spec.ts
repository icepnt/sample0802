/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Sample0802TestModule } from '../../../test.module';
import { AssetsLogMySuffixDetailComponent } from 'app/entities/assets-log-my-suffix/assets-log-my-suffix-detail.component';
import { AssetsLogMySuffix } from 'app/shared/model/assets-log-my-suffix.model';

describe('Component Tests', () => {
    describe('AssetsLogMySuffix Management Detail Component', () => {
        let comp: AssetsLogMySuffixDetailComponent;
        let fixture: ComponentFixture<AssetsLogMySuffixDetailComponent>;
        const route = ({ data: of({ assetsLog: new AssetsLogMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Sample0802TestModule],
                declarations: [AssetsLogMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AssetsLogMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AssetsLogMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.assetsLog).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
