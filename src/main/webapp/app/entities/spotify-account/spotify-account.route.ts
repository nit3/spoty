import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISpotifyAccount, SpotifyAccount } from 'app/shared/model/spotify-account.model';
import { SpotifyAccountService } from './spotify-account.service';
import { SpotifyAccountComponent } from './spotify-account.component';
import { SpotifyAccountDetailComponent } from './spotify-account-detail.component';
import { SpotifyAccountUpdateComponent } from './spotify-account-update.component';

@Injectable({ providedIn: 'root' })
export class SpotifyAccountResolve implements Resolve<ISpotifyAccount> {
  constructor(private service: SpotifyAccountService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpotifyAccount> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((spotifyAccount: HttpResponse<SpotifyAccount>) => {
          if (spotifyAccount.body) {
            return of(spotifyAccount.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SpotifyAccount());
  }
}

export const spotifyAccountRoute: Routes = [
  {
    path: '',
    component: SpotifyAccountComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SpotifyAccounts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SpotifyAccountDetailComponent,
    resolve: {
      spotifyAccount: SpotifyAccountResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SpotifyAccounts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SpotifyAccountUpdateComponent,
    resolve: {
      spotifyAccount: SpotifyAccountResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SpotifyAccounts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SpotifyAccountUpdateComponent,
    resolve: {
      spotifyAccount: SpotifyAccountResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SpotifyAccounts',
    },
    canActivate: [UserRouteAccessService],
  },
];
