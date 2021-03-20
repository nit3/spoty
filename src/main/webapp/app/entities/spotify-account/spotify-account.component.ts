import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISpotifyAccount } from 'app/shared/model/spotify-account.model';
import { SpotifyAccountService } from './spotify-account.service';
import { SpotifyAccountDeleteDialogComponent } from './spotify-account-delete-dialog.component';

@Component({
  selector: 'jhi-spotify-account',
  templateUrl: './spotify-account.component.html',
})
export class SpotifyAccountComponent implements OnInit, OnDestroy {
  spotifyAccounts?: ISpotifyAccount[];
  eventSubscriber?: Subscription;

  constructor(
    protected spotifyAccountService: SpotifyAccountService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.spotifyAccountService.query().subscribe((res: HttpResponse<ISpotifyAccount[]>) => (this.spotifyAccounts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSpotifyAccounts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISpotifyAccount): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSpotifyAccounts(): void {
    this.eventSubscriber = this.eventManager.subscribe('spotifyAccountListModification', () => this.loadAll());
  }

  delete(spotifyAccount: ISpotifyAccount): void {
    const modalRef = this.modalService.open(SpotifyAccountDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.spotifyAccount = spotifyAccount;
  }
}
