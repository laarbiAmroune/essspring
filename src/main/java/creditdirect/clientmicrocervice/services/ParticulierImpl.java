package creditdirect.clientmicrocervice.services;


import creditdirect.clientmicrocervice.entities.Particulier;
import creditdirect.clientmicrocervice.repositories.ParticulierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticulierImpl implements ParticulierService {


    @Autowired
    private ParticulierRepository particulierRepository;


    @Override
    public Particulier updateParticulier(Long id, Particulier updatedParticulier) {
        Optional<Particulier> optionalParticulier = particulierRepository.findById(id);

        if (optionalParticulier.isPresent()) {
            Particulier existingParticulier = optionalParticulier.get();

            // Mettez à jour les propriétés nécessaires de existingParticulier avec les valeurs de updatedParticulier
            existingParticulier.setNom(updatedParticulier.getNom());
            existingParticulier.setPrenom(updatedParticulier.getPrenom());
            existingParticulier.setTelephone(updatedParticulier.getTelephone());
            existingParticulier.setNationalite(updatedParticulier.getNationalite());
            existingParticulier.setAdresse(updatedParticulier.getAdresse());
            existingParticulier.setVille(updatedParticulier.getVille());
            existingParticulier.setCodePostal(updatedParticulier.getCodePostal());
            existingParticulier.setResidesInAlgeria(updatedParticulier.isResidesInAlgeria());
            existingParticulier.setCommune(updatedParticulier.getCommune());

            // Ajoutez d'autres propriétés à mettre à jour...

            return particulierRepository.save(existingParticulier);
        } else {
            // Gérer le cas où le particulier n'est pas trouvé
            // Vous pouvez lever une exception, renvoyer null ou prendre d'autres mesures
            throw new RuntimeException("Particulier non trouvé avec l'ID : " + id);
        }
    }



}
