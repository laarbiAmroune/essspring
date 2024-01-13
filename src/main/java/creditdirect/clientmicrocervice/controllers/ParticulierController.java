package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Particulier;
import creditdirect.clientmicrocervice.services.ParticulierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/particuliers")
public class ParticulierController {

    @Autowired
    private ParticulierService particulierService;

    @PutMapping("/{id}")
    public ResponseEntity<Particulier> updateParticulier(@PathVariable Long id, @RequestBody Particulier updatedParticulier) {
        try {
            Particulier updated = particulierService.updateParticulier(id, updatedParticulier);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Gérer l'exception, par exemple, renvoyer une réponse HTTP 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
