import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SpotyTestModule } from '../../../test.module';
import { SpotifyAccountComponent } from 'app/entities/spotify-account/spotify-account.component';
import { SpotifyAccountService } from 'app/entities/spotify-account/spotify-account.service';
import { SpotifyAccount } from 'app/shared/model/spotify-account.model';

describe('Component Tests', () => {
  describe('SpotifyAccount Management Component', () => {
    let comp: SpotifyAccountComponent;
    let fixture: ComponentFixture<SpotifyAccountComponent>;
    let service: SpotifyAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpotyTestModule],
        declarations: [SpotifyAccountComponent],
      })
        .overrideTemplate(SpotifyAccountComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpotifyAccountComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpotifyAccountService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SpotifyAccount(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.spotifyAccounts && comp.spotifyAccounts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
