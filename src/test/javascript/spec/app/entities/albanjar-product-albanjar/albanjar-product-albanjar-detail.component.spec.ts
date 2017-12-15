/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarProductAlbanjarDetailComponent } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar-detail.component';
import { AlbanjarProductAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.service';
import { AlbanjarProductAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarProductAlbanjar Management Detail Component', () => {
        let comp: AlbanjarProductAlbanjarDetailComponent;
        let fixture: ComponentFixture<AlbanjarProductAlbanjarDetailComponent>;
        let service: AlbanjarProductAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarProductAlbanjarDetailComponent],
                providers: [
                    AlbanjarProductAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarProductAlbanjarDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarProductAlbanjarDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarProductAlbanjarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new AlbanjarProductAlbanjar(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.albanjarProduct).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
