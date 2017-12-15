import { BaseEntity } from './../../shared';

export const enum Gender {
    'MR',
    'MRS'
}

export class AlbanjarCustomerAlbanjar implements BaseEntity {
    constructor(
        public id?: number,
        public gender?: Gender,
        public firstname?: string,
        public lastname?: string,
        public cin?: string,
        public adresse?: string,
        public deliveries?: BaseEntity[],
    ) {
    }
}
