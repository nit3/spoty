import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { DeviceService } from 'app/entities/device/device.service';
import { IPlay, Play } from 'app/shared/model/play.model';
import { Device, IDevice } from 'app/shared/model/device.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-play-song',
  templateUrl: './play-song.component.html',
  styleUrls: ['./play-song.component.scss'],
})
export class PlaySongComponent implements OnInit {
  playForm = this.fb.group({
    numberOfDevices: ['', [Validators.required]],
    songName: ['', [Validators.required]],
    maxConcurrentDevices: ['', [Validators.required]],
  });
  constructor(private fb: FormBuilder, private deviceService: DeviceService) {}

  ngOnInit(): void {}

  playSong(): void {
    const play = this.createFromForm();
    this.deviceService.play(play);
    this.subscribeToSaveResponse(this.deviceService.play(play));
    /* eslint-disable no-console */
    console.log('test');
    /* eslint-disable no-console */
  }

  private createFromForm(): IPlay {
    return {
      ...new Play(),
      count: this.playForm.get(['numberOfDevices'])!.value,
      max: this.playForm.get(['maxConcurrentDevices'])!.value,
      song: this.playForm.get(['songName'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlay>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {}

  protected onSaveError(): void {}
}
