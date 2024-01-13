package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.TypeFinancement;

import java.util.List;

public interface TypeFinancementService {
    List<TypeFinancement> getAllTypeFinancements();
    TypeFinancement getTypeFinancementById(Long id);
    TypeFinancement createTypeFinancement(TypeFinancement typeFinancement);
    TypeFinancement updateTypeFinancement(Long id, TypeFinancement typeFinancement);
    void deleteTypeFinancement(Long id);


}
