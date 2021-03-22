export interface IPlay {
  count?: number;
  max?: number;
  song?: string;
}

export class Play implements IPlay {
  constructor(public count?: number, public max?: number, public song?: string) {}
}
