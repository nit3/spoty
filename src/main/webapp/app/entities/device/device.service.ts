import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDevice } from 'app/shared/model/device.model';
import { IPlay } from 'app/shared/model/play.model';

type EntityResponseType = HttpResponse<IDevice>;
type EntityArrayResponseType = HttpResponse<IDevice[]>;

@Injectable({ providedIn: 'root' })
export class DeviceService {
  public resourceUrl = SERVER_API_URL + 'api/devices';

  constructor(protected http: HttpClient) {}

  create(device: IDevice): Observable<EntityResponseType> {
    return this.http.post<IDevice>(this.resourceUrl, device, { observe: 'response' });
  }

  update(device: IDevice): Observable<EntityResponseType> {
    return this.http.put<IDevice>(this.resourceUrl, device, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDevice>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDevice[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  play(play: IPlay): Observable<HttpResponse<IPlay>> {
    return this.http.post<IPlay>(`${this.resourceUrl}/play`, play, { observe: 'response' });
  }
}
