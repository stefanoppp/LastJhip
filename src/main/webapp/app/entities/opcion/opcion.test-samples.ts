import { IOpcion, NewOpcion } from './opcion.model';

export const sampleWithRequiredData: IOpcion = {
  id: 16704,
  codigo: 'grouchy aha',
  nombre: 'too gosh',
  descripcion: 'why',
  precioAdicional: 20033.01,
};

export const sampleWithPartialData: IOpcion = {
  id: 21090,
  codigo: 'in',
  nombre: 'grouper aw',
  descripcion: 'postbox curl',
  precioAdicional: 2113.03,
};

export const sampleWithFullData: IOpcion = {
  id: 3338,
  codigo: 'joint ah',
  nombre: 'unexpectedly dramatize uh-huh',
  descripcion: 'nun whose',
  precioAdicional: 3292.27,
};

export const sampleWithNewData: NewOpcion = {
  codigo: 'railway',
  nombre: 'boohoo safely coolly',
  descripcion: 'hmph apud',
  precioAdicional: 21667.38,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
