package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.DirectionRegionale;

import java.util.List;

public interface DirectionRegionaleService {
    List<DirectionRegionale> getAllDirectionRegionales();
    DirectionRegionale getDirectionRegionaleById(Long id);
    DirectionRegionale createDirectionRegionale(DirectionRegionale directionRegionale);
    void deleteDirectionRegionale(Long id);
    // Other methods if needed
}
