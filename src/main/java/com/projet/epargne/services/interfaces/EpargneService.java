package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.dto.VersementDto;

public interface EpargneService {

    public VersementDto saveVersement(VersementDto versementDto);

    public VersementDto editVersement(VersementDto versementDto);

    public void deleteVersement(VersementDto versementDto);

    public RetraitDto saveRetrait(RetraitDto retraitDto);

    public RetraitDto editRetrait(RetraitDto retrait);

    public void deleteRetrait(RetraitDto retraitDto);


}
