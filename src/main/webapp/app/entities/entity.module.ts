import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'device',
        loadChildren: () => import('./device/device.module').then(m => m.SpotyDeviceModule),
      },
      {
        path: 'spotify-account',
        loadChildren: () => import('./spotify-account/spotify-account.module').then(m => m.SpotySpotifyAccountModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SpotyEntityModule {}
