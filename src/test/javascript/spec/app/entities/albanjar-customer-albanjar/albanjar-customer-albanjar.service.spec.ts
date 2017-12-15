/* tslint:disable max-line-length */
import { TestBed, async } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { ConnectionBackend, RequestOptions, BaseRequestOptions, Http, Response, ResponseOptions } from '@angular/http';
import { JhiDateUtils } from 'ng-jhipster';

import { AlbanjarCustomerAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.service';
import { AlbanjarCustomerAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.model';

describe('Service Tests', () => {

    describe('AlbanjarCustomerAlbanjar Service', () => {
        let service: AlbanjarCustomerAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                providers: [
                    {
                        provide: ConnectionBackend,
                        useClass: MockBackend
                    },
                    {
                        provide: RequestOptions,
                        useClass: BaseRequestOptions
                    },
                    Http,
                    JhiDateUtils,
                    AlbanjarCustomerAlbanjarService
                ]
            });

            service = TestBed.get(AlbanjarCustomerAlbanjarService);

            this.backend = TestBed.get(ConnectionBackend) as MockBackend;
            this.backend.connections.subscribe((connection: any) => {
                this.lastConnection = connection;
            });
        }));

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(123).subscribe(() => {});

                expect(this.lastConnection).toBeDefined();
                expect(this.lastConnection.request.url).toEqual('api/albanjar-customers/' + 123);
            });
            it('should return AlbanjarCustomerAlbanjar', () => {

                let entity: AlbanjarCustomerAlbanjar;
                service.find(123).subscribe((_entity: AlbanjarCustomerAlbanjar) => {
                    entity = _entity;
                });

                this.lastConnection.mockRespond(new Response(new ResponseOptions({
                    body: JSON.stringify({id: 123}),
                })));

                expect(entity).toBeDefined();
                expect(entity.id).toEqual(123);
            });

            it('should propagate not found response', () => {

                let error: any;
                service.find(123).subscribe(null, (_error: any) => {
                    error = _error;
                });

                this.lastConnection.mockError(new Response(new ResponseOptions({
                    status: 404,
                })));

                expect(error).toBeDefined();
                expect(error.status).toEqual(404);
            });
        });
    });

});
