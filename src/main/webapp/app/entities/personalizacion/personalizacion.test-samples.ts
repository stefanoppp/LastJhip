import { IPersonalizacion, NewPersonalizacion } from './personalizacion.model';

export const sampleWithRequiredData: IPersonalizacion = {
  id: 12555,
  nombre: 'ouch tattered',
  descripcion: 'braid silky finally',
};

export const sampleWithPartialData: IPersonalizacion = {
  id: 22034,
  nombre: 'or in neatly',
  descripcion: 'in phew reproachfully',
};

export const sampleWithFullData: IPersonalizacion = {
  id: 23928,
  nombre: 'brr',
  descripcion: 'aftermath pronoun gladly',
};

export const sampleWithNewData: NewPersonalizacion = {
  nombre: 'psst amongst',
  descripcion: 'although',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
