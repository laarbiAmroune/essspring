package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.TypeFinancement;
import creditdirect.clientmicrocervice.repositories.TypeFinancementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeFinancementServiceImpl implements TypeFinancementService {

    private final TypeFinancementRepository typeFinancementRepository;

    @Autowired
    public TypeFinancementServiceImpl(TypeFinancementRepository typeFinancementRepository) {
        this.typeFinancementRepository = typeFinancementRepository;
    }

    @Override
    public List<TypeFinancement> getAllTypeFinancements() {
        return typeFinancementRepository.findAll();
    }

    @Override
    public TypeFinancement getTypeFinancementById(Long id) {
        return typeFinancementRepository.findById(id).orElse(null);
    }

    @Override
    public TypeFinancement createTypeFinancement(TypeFinancement typeFinancement) {
        return typeFinancementRepository.save(typeFinancement);
    }

    @Override
    public TypeFinancement updateTypeFinancement(Long id, TypeFinancement typeFinancement) {
        if (typeFinancementRepository.existsById(id)) {
            typeFinancement.setId(id);
            return typeFinancementRepository.save(typeFinancement);
        }
        return null; // Or handle as per requirement
    }

    @Override
    public void deleteTypeFinancement(Long id) {
        typeFinancementRepository.deleteById(id);
    }
}
