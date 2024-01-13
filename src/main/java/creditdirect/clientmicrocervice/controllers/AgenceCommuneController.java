package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.entities.Commune;
import creditdirect.clientmicrocervice.services.AgenceCommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/agence-commune")
public class AgenceCommuneController {
    private final AgenceCommuneService agenceCommuneService;

    @Autowired
    public AgenceCommuneController(AgenceCommuneService agenceCommuneService) {
        this.agenceCommuneService = agenceCommuneService;
    }

    @PostMapping("/addcommune")
    public ResponseEntity<Commune> addCommune(@RequestBody Commune commune) {
        Commune createdCommune = agenceCommuneService.createCommune(commune);
        return new ResponseEntity<>(createdCommune, HttpStatus.CREATED);
    }
    @PostMapping("/addagence")
    public ResponseEntity<Agence> addAgence(@RequestBody Agence agence) {
        Agence createdAgence = agenceCommuneService.createAgence(agence);
        return new ResponseEntity<>(createdAgence, HttpStatus.CREATED);
    }
    @GetMapping("/getallagences")
    public List<Agence> getAllAgences() {
        return agenceCommuneService.getAllAgences();
    }

    @PutMapping("/{agenceId}/add-commune/{communeId}")
    public ResponseEntity<Void> addCommuneToAgence(@PathVariable Long agenceId, @PathVariable Long communeId) {
        agenceCommuneService.addCommuneToAgence(agenceId, communeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCommunes() {
        try {
            List<Commune> communes = agenceCommuneService.getAllCommunes();
            return ResponseEntity.ok(communes);
        } catch (Exception e) {
            // Log the exception or perform necessary actions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching communes: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Commune> getCommuneById(@PathVariable Long id) {
        Commune commune = agenceCommuneService.getCommuneById(id);
        if (commune != null) {
            return ResponseEntity.ok(commune);
        }
        return ResponseEntity.notFound().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Commune> updateCommune(@PathVariable Long id, @RequestBody Commune commune) {
        Commune updatedCommune = agenceCommuneService.updateCommune(id, commune);
        if (updatedCommune != null) {
            return ResponseEntity.ok(updatedCommune);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommune(@PathVariable Long id) {
        agenceCommuneService.deleteCommune(id);
        return ResponseEntity.noContent().build();
    }
}
