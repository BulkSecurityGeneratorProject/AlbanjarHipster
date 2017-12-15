import { BaseEntity } from './../../shared';

export class AlbanjarDeliveryAlbanjar implements BaseEntity {
    constructor(
        public id?: number,
        public czone?: string,
        public cda?: string,
        public quantity?: number,
        public albanjarCustomerId?: number,
        public albanjarProductId?: number,
    ) {
    }
}
