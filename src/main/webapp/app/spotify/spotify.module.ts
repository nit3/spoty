import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlaySongComponent } from './play-song/play-song.component';
import { RouterModule, Routes } from '@angular/router';
import { SpotySharedModule } from 'app/shared/shared.module';

const routes: Routes = [{ path: 'play', component: PlaySongComponent }];

@NgModule({
  declarations: [PlaySongComponent],
  imports: [CommonModule, SpotySharedModule, RouterModule.forRoot(routes)],
})
export class SpotifyModule {}
