import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpotyTestModule } from '../../../test.module';
import { SpotifyAccountDetailComponent } from 'app/entities/spotify-account/spotify-account-detail.component';
import { SpotifyAccount } from 'app/shared/model/spotify-account.model';

describe('Component Tests', () => {
  describe('SpotifyAccount Management Detail Component', () => {
    let comp: SpotifyAccountDetailComponent;
    let fixture: ComponentFixture<SpotifyAccountDetailComponent>;
    const route = ({ data: of({ spotifyAccount: new SpotifyAccount(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpotyTestModule],
        declarations: [SpotifyAccountDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SpotifyAccountDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpotifyAccountDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load spotifyAccount on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.spotifyAccount).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
