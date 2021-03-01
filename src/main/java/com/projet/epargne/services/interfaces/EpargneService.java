package com.projet.epargne.services.interfaces;

import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;

public interface EpargneService {

    public Versement saveVersement(Versement versement);

    public Versement editVersement(Versement versement);

    public void deleteVersement(Long idVersement);

    public Retrait saveRetrait(Retrait retrait);

    public Retrait editRetrait(Retrait retrait);

    public void deleteRetrait(Long idRetrait);

}
