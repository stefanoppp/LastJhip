import { Moneda } from 'app/entities/enumerations/moneda.model';

export interface IDispositivo {
  id: number;
  codigo?: string | null;
  nombre?: string | null;
  descripcion?: string | null;
  precioBase?: number | null;
  moneda?: keyof typeof Moneda | null;
}

export type NewDispositivo = Omit<IDispositivo, 'id'> & { id: null };
