package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Particulier;

import java.util.List;

public interface ParticulierService {
    List<Particulier> getAllParticuliers();
    Particulier getParticulierById(Long id);
    Particulier createParticulier(Particulier particulier);
    Particulier updateParticulier(Long id, Particulier particulier);
    void deleteParticulier(Long id);
}
