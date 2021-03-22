import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlaySongComponent } from './play-song/play-song.component';
import { RouterModule, Routes } from '@angular/router';
import { SpotySharedModule } from 'app/shared/shared.module';
import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

const routes: Routes = [
  {
    path: 'play',
    component: PlaySongComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Devices',
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [PlaySongComponent],
  imports: [CommonModule, SpotySharedModule, RouterModule.forRoot(routes)],
})
export class SpotifyModule {}
