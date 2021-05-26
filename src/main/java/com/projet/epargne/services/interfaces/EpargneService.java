package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.RetraitRequest;
import com.projet.epargne.dto.VersementRequest;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;

public interface EpargneService {

    public Versement saveVersement(VersementRequest versementRequest);

    public Versement editVersement(VersementRequest versementRequest);

    public void deleteVersement(Long idVersement);

    public Retrait saveRetrait(RetraitRequest retraitRequest);

    public Retrait editRetrait(RetraitRequest retraitRequest);

    public void deleteRetrait(Long idRetrait);

}
