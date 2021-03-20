export interface ISpotifyAccount {
  id?: number;
  username?: string;
  password?: string;
}

export class SpotifyAccount implements ISpotifyAccount {
  constructor(public id?: number, public username?: string, public password?: string) {}
}
