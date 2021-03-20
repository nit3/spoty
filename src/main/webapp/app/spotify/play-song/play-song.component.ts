import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-play-song',
  templateUrl: './play-song.component.html',
  styleUrls: ['./play-song.component.scss'],
})
export class PlaySongComponent implements OnInit {
  passwordForm = this.fb.group({
    numberOfDevices: ['', [Validators.required]],
    songName: ['', [Validators.required]],
    maxConcurrentDevices: ['', [Validators.required]],
  });
  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {}

  playSong() {}
}
