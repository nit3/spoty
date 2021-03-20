import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpotifyAccount } from 'app/shared/model/spotify-account.model';
import { SpotifyAccountService } from './spotify-account.service';

@Component({
  templateUrl: './spotify-account-delete-dialog.component.html',
})
export class SpotifyAccountDeleteDialogComponent {
  spotifyAccount?: ISpotifyAccount;

  constructor(
    protected spotifyAccountService: SpotifyAccountService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.spotifyAccountService.delete(id).subscribe(() => {
      this.eventManager.broadcast('spotifyAccountListModification');
      this.activeModal.close();
    });
  }
}
