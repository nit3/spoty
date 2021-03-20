import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SpotyTestModule } from '../../../test.module';
import { SpotifyAccountUpdateComponent } from 'app/entities/spotify-account/spotify-account-update.component';
import { SpotifyAccountService } from 'app/entities/spotify-account/spotify-account.service';
import { SpotifyAccount } from 'app/shared/model/spotify-account.model';

describe('Component Tests', () => {
  describe('SpotifyAccount Management Update Component', () => {
    let comp: SpotifyAccountUpdateComponent;
    let fixture: ComponentFixture<SpotifyAccountUpdateComponent>;
    let service: SpotifyAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpotyTestModule],
        declarations: [SpotifyAccountUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SpotifyAccountUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpotifyAccountUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpotifyAccountService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpotifyAccount(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpotifyAccount();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
