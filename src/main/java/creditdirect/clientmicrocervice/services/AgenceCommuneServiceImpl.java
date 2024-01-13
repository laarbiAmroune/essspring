package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.entities.Commune;
import creditdirect.clientmicrocervice.entities.Wilaya;
import creditdirect.clientmicrocervice.repositories.AgenceRepository;
import creditdirect.clientmicrocervice.repositories.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AgenceCommuneServiceImpl implements AgenceCommuneService {
    private final AgenceRepository agenceRepository;
    private final CommuneRepository communeRepository;

    @Autowired
    public AgenceCommuneServiceImpl(AgenceRepository agenceRepository, CommuneRepository communeRepository) {
        this.agenceRepository = agenceRepository;
        this.communeRepository = communeRepository;
    }

    @Override
    public Commune createCommune(Commune commune) {
        return communeRepository.save(commune);
    }


    @Override
    public List<Commune> getAllCommunes() throws Exception {
        try {
            return communeRepository.findAll();
        } catch (Exception e) {
            // Log the exception or perform necessary actions
            throw new Exception("Failed to fetch communes", e);
        }
    }
    @Override
    public Commune getCommuneById(Long id) {
        return communeRepository.findById(id).orElse(null);
    }

    @Override
    public Commune updateCommune(Long id, Commune updatedCommune) {
        if (communeRepository.existsById(id)) {
            updatedCommune.setId(id);
            return communeRepository.save(updatedCommune);
        }
        return null; // Or handle as needed for non-existent commune
    }

    @Override
    public void deleteCommune(Long id) {
        communeRepository.deleteById(id);
    }

    @Override
    public Agence createAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public void addCommuneToAgence(Long agenceId, Long communeId) {
        System.out.println("agenceId" + agenceId);
        System.out.println("communeId" + communeId);
        Agence agence = agenceRepository.findById(agenceId).orElse(null);
        Commune commune = communeRepository.findById(communeId).orElse(null);

        if (agence != null && commune != null) {
           agence.getCommunes().add(commune);

           // commune.getAgences().add(agence);
           agenceRepository.save(agence);
          //  communeRepository.save(commune);
        }

    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence getAgenceById(Long assignedAgenceId) {
        return agenceRepository.findById(assignedAgenceId).orElse(null);
    }


}