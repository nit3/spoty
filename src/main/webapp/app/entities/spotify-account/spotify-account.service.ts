import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISpotifyAccount } from 'app/shared/model/spotify-account.model';

type EntityResponseType = HttpResponse<ISpotifyAccount>;
type EntityArrayResponseType = HttpResponse<ISpotifyAccount[]>;

@Injectable({ providedIn: 'root' })
export class SpotifyAccountService {
  public resourceUrl = SERVER_API_URL + 'api/spotify-accounts';

  constructor(protected http: HttpClient) {}

  create(spotifyAccount: ISpotifyAccount): Observable<EntityResponseType> {
    return this.http.post<ISpotifyAccount>(this.resourceUrl, spotifyAccount, { observe: 'response' });
  }

  update(spotifyAccount: ISpotifyAccount): Observable<EntityResponseType> {
    return this.http.put<ISpotifyAccount>(this.resourceUrl, spotifyAccount, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpotifyAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpotifyAccount[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
