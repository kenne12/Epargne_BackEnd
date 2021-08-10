package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.RetraitRequestDTO;
import com.projet.epargne.dto.VersementRequestDTO;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;

public interface EpargneService {

     Versement saveVersement(VersementRequestDTO versementRequestDTO);

     Versement editVersement(VersementRequestDTO versementRequestDTO);

     void deleteVersement(Long idVersement);

     Retrait saveRetrait(RetraitRequestDTO retraitRequestDTO);

     Retrait editRetrait(RetraitRequestDTO retraitRequestDTO);

     void deleteRetrait(Long idRetrait);

}
