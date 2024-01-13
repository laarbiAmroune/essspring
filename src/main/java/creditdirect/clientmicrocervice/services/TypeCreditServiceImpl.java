package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.TypeCredit;
import creditdirect.clientmicrocervice.repositories.TypeCreditRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class TypeCreditServiceImpl implements TypeCreditService {

    private final TypeCreditRepository typeCreditRepository;

    @Autowired
    public TypeCreditServiceImpl(TypeCreditRepository typeCreditRepository) {
        this.typeCreditRepository = typeCreditRepository;
    }

    @Override
    public List<TypeCredit> getAllTypeCredits() {
        return typeCreditRepository.findAll();
    }

    @Override
    public TypeCredit getTypeCreditById(Long id) {
        return typeCreditRepository.findById(id).orElse(null);
    }

    @Override
    public TypeCredit createTypeCredit(TypeCredit typeCredit) {
        return typeCreditRepository.save(typeCredit);
    }

    @Override
    public TypeCredit updateTypeCredit(Long id, TypeCredit typeCredit) {
        if (typeCreditRepository.existsById(id)) {
            typeCredit.setId(id);
            return typeCreditRepository.save(typeCredit);
        }
        return null;
    }

    @Override
    public void deleteTypeCredit(Long id) {
        typeCreditRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TypeCredit saveTypeCredit(TypeCredit typeCredit, MultipartFile imageFile) {
        try {
            TypeCredit savedTypeCredit = typeCreditRepository.save(typeCredit);

            if (imageFile != null && !imageFile.isEmpty()) {
                String folderPath = "src/main/resources/TypeCreditImages/";
                String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path imagePath = Paths.get(folderPath + uniqueFileName);
                Files.createDirectories(imagePath.getParent());
                Files.copy(imageFile.getInputStream(), imagePath);

                String imagePathString = imagePath.toString();
                savedTypeCredit.setImagePath(imagePathString);
            } else {
                savedTypeCredit.setImagePath(null); // Set image as null if no file is provided
            }

            if (savedTypeCredit.getPrix() == null) {
                savedTypeCredit.setPrix(0.0); // Setting a default value of 0.0 for prix if it's null
            }

            return typeCreditRepository.save(savedTypeCredit);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

@Override
    public List<TypeCredit> getTypeCreditsByTypeFinancementId(Long typeFinancementId) {
        return typeCreditRepository.findByTypeFinancementId(typeFinancementId);
    }
}
