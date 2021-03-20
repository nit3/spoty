import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISpotifyAccount, SpotifyAccount } from 'app/shared/model/spotify-account.model';
import { SpotifyAccountService } from './spotify-account.service';

@Component({
  selector: 'jhi-spotify-account-update',
  templateUrl: './spotify-account-update.component.html',
})
export class SpotifyAccountUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    username: [],
    password: [],
  });

  constructor(protected spotifyAccountService: SpotifyAccountService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ spotifyAccount }) => {
      this.updateForm(spotifyAccount);
    });
  }

  updateForm(spotifyAccount: ISpotifyAccount): void {
    this.editForm.patchValue({
      id: spotifyAccount.id,
      username: spotifyAccount.username,
      password: spotifyAccount.password,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const spotifyAccount = this.createFromForm();
    if (spotifyAccount.id !== undefined) {
      this.subscribeToSaveResponse(this.spotifyAccountService.update(spotifyAccount));
    } else {
      this.subscribeToSaveResponse(this.spotifyAccountService.create(spotifyAccount));
    }
  }

  private createFromForm(): ISpotifyAccount {
    return {
      ...new SpotifyAccount(),
      id: this.editForm.get(['id'])!.value,
      username: this.editForm.get(['username'])!.value,
      password: this.editForm.get(['password'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpotifyAccount>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
