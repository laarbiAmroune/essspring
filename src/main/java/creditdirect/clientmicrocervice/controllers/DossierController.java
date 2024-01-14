package creditdirect.clientmicrocervice.controllers;
import creditdirect.clientmicrocervice.config.FileStorageProperties;
import creditdirect.clientmicrocervice.entities.Dossier;
import creditdirect.clientmicrocervice.kafka.KafkaProducer;
import creditdirect.clientmicrocervice.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;



@RestController
@RequestMapping("/dossiers")
public class DossierController {

    private final KafkaProducer kafkaProducer;
    private final DossierService dossierService;

    @Autowired
    public DossierController(DossierService dossierService,KafkaProducer kafkaProducer) {
        this.dossierService = dossierService;
        this.kafkaProducer = kafkaProducer;
    }
///////////////get all dossiers////////////////////
    @GetMapping("/all")

    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }
    ////////////////get dosssier by id dossiers /////////////////////////
    @GetMapping("/{id}")
    public Dossier getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id);
    }


    //////////////////get dossiiers by id client/////////////////

    @GetMapping("/client/{clientId}")
    public List<Dossier> getDossiersByClientId(@PathVariable Long clientId) {
        return dossierService.getDossiersByClientId(clientId);
    }

    ///////////////////add dossier /////////////////
    @PostMapping("/adddossier")
    public ResponseEntity<Dossier> addDossier(@RequestBody Dossier dossier) {
        Dossier addedDossier = dossierService.addDossier(dossier);
        return ResponseEntity.ok(addedDossier);
    }
/////////////////////update dossier add files/////////////////////////
    @PostMapping("/{dossierId}/files")
    public ResponseEntity<Dossier> updateFilesForDossier(
            @PathVariable Long dossierId,
            @RequestParam("files") MultipartFile[] files
    ) {
        Dossier updatedDossier = dossierService.updateFilesForDossier(dossierId, files);
        return ResponseEntity.ok(updatedDossier);
    }



//////////////////////////////// asign dossiers to courtier///////////////////
    @PostMapping("/assign-dossier/{dossierId}/to-courtier/{courtierId}")
    public ResponseEntity<Dossier> assignDossierToCourtier(@PathVariable Long dossierId, @PathVariable Long courtierId) {
        Dossier assignedDossier = dossierService.assignDossierToCourtier(dossierId, courtierId);
        return ResponseEntity.ok(assignedDossier);
    }

/////////////dossier agence non asignd
    @GetMapping("/{courtierAgenceId}/dossiersnotassigned")
    public List<Dossier> getDossiersForCourtier(@PathVariable Long courtierAgenceId) {
        return dossierService.getDossiersForCourtier(courtierAgenceId);
    }


///////////// dossier en cours for courtiers
    @GetMapping("/courtier/{courtierId}/Encours")
    public List<Dossier> getDossiersencoursForCourtier(@PathVariable Long courtierId) {
        return dossierService.getDossiersencoursForCourtier(courtierId);
    }

/// dosiieers traitte par le courtier
    @GetMapping("/courtier/{courtierId}/traitee")
    public List<Dossier> getTraiteeDossiersByCourtierId(@PathVariable Long courtierId) {
        return dossierService.getTraiteeDossiersByCourtier(courtierId);
    }



/////////once the client validate dossiers asign it to agence or diretion re

    @PostMapping("/assign-agency/{dossierId}")
    public ResponseEntity<Dossier> assignAgencyToDossier(@PathVariable Long dossierId) {
        Dossier updatedDossier = dossierService.affectiondossieragence(dossierId);
        return ResponseEntity.ok(updatedDossier);
    }
    //////////////////////delete file

    @DeleteMapping("/{dossierId}/files/{fileName}")
    public ResponseEntity<String> deleteFileFromDossier(
            @PathVariable Long dossierId,
            @PathVariable String fileName) {
System.out.println(fileName);
        boolean isDeleted = dossierService.deleteFileByDossierIdAndFileName(dossierId, fileName);

        if (isDeleted) {
            return ResponseEntity.ok("File deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found or dossier not found.");
        }
    }
    //////////////sendmultipletoDirectreur
    @PostMapping("/sendmultipletoDirectreur")
    public ResponseEntity<String> updateDossiersStatusToTraitee(@RequestBody List<Long> dossierIds) {
        dossierService.updateDossiersStatusToTraitee(dossierIds);
        return new ResponseEntity<>("Dossier statuses updated to TRAITEE", HttpStatus.OK);
    }

    ///////////////getDossiersByAgenceid

    @GetMapping("/agence/{agenceId}")
    public ResponseEntity<List<Dossier>> getDossiersByAgence(@PathVariable Long agenceId) {
        List<Dossier> dossiers = dossierService.getAllDossiersByAgence(agenceId);

        if (dossiers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dossiers);

    }



    @PutMapping("/{dossierId}/mark-as-ACCEPTER")
    public void markDossierAsACCEPTER(@PathVariable Long dossierId) {
        dossierService.updateDossierStatusDirector_ACCEPTED(dossierId);
    }

    @PutMapping("/{dossierId}/mark-as-REFUSE")
    public void markDossierAsREFUSE(@PathVariable Long dossierId) {
        dossierService.updateDossierStatusDirector_ACCEPTED(dossierId);
    }




    @Autowired
    private FileStorageProperties fileStorageProperties;

    @GetMapping("/downloadFile/{dossierId}/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long dossierId, @PathVariable String fileName) {
        try {
            String uploadDir = fileStorageProperties.getUploadDir();
            System.out.println("Base Upload Directory: " + uploadDir);

            Path filePath = Paths.get(uploadDir, String.valueOf(dossierId), fileName);
            System.out.println("File Path: " + filePath);

            if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
                System.out.println("File does not exist or is not readable");
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = Files.readAllBytes(filePath);
            ByteArrayResource resource = new ByteArrayResource(fileContent);

            System.out.println("File downloaded successfully: " + fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(fileContent.length)
                    .body(resource);
        } catch (IOException e) {
            // Handle file download failure
            System.out.println("File download failed");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /////////////////////////////////////////
    ////////////////////update status //////////////
    //////////////////////////////////    put mehodes /////////
    @PostMapping("/{idDossier}/AccepterDossier/{idCompte}")
    public ResponseEntity<String> setStatusToAccepter(
            @PathVariable Long idDossier,
            @PathVariable Long idCompte,
            @RequestBody(required = false) String comment) {

        try {
            // Call the service method to update Dossier and Commentaire
            dossierService.setStatusToAccepter(idDossier, idCompte, comment);

            // If no exception is thrown, everything is successful
            return ResponseEntity.ok("Dossier and Commentaire updated successfully");
        } catch (Exception e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @PutMapping("/{dossierId}/refuse")
    public ResponseEntity<String> setDossierStatusToRefuser(@PathVariable Long dossierId) {
        dossierService.setStatusToRefuser(dossierId);
        return ResponseEntity.ok("Dossier status set to REFUSER successfully.");
    }


    @PutMapping("/{idDossier}/RenvoyerDossier/{idCompte}")
    public ResponseEntity<String> updateStatusToRenvoyer(
            @PathVariable Long idDossier,
            @PathVariable Long idCompte,
            @RequestBody(required = false) String comment) {

        try {
            // Call the service method to update Dossier and Commentaire
            dossierService.updateStatusToRenvoyer(idDossier, idCompte, comment);

            // If no exception is thrown, everything is successful
            return ResponseEntity.ok("Dossier and Commentaire updated successfully");
        } catch (Exception e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{dossierId}/mark-as-traitee")
    public void markDossierAsTraitee(@PathVariable Long dossierId) {
        System.out.println((dossierId));
        dossierService.updateDossierStatusToTraitee(dossierId);
    }



    /////////////////////
    @GetMapping("/courtier/{courtierId}/alldossiers")
    public List<Dossier> getAcceptedAndRejectedDossiersByCourtier(
            @PathVariable Long courtierId
    ) {
        return dossierService.getAcceptedAndRejectedDossiersByCourtier(courtierId);
    }
}
