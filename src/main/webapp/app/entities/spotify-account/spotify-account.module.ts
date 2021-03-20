import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpotySharedModule } from 'app/shared/shared.module';
import { SpotifyAccountComponent } from './spotify-account.component';
import { SpotifyAccountDetailComponent } from './spotify-account-detail.component';
import { SpotifyAccountUpdateComponent } from './spotify-account-update.component';
import { SpotifyAccountDeleteDialogComponent } from './spotify-account-delete-dialog.component';
import { spotifyAccountRoute } from './spotify-account.route';

@NgModule({
  imports: [SpotySharedModule, RouterModule.forChild(spotifyAccountRoute)],
  declarations: [
    SpotifyAccountComponent,
    SpotifyAccountDetailComponent,
    SpotifyAccountUpdateComponent,
    SpotifyAccountDeleteDialogComponent,
  ],
  entryComponents: [SpotifyAccountDeleteDialogComponent],
})
export class SpotySpotifyAccountModule {}
