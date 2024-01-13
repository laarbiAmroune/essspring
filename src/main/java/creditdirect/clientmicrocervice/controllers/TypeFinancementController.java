package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.TypeFinancement;
import creditdirect.clientmicrocervice.services.TypeFinancementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-financements")
public class TypeFinancementController {

    private final TypeFinancementService typeFinancementService;

    @Autowired
    public TypeFinancementController(TypeFinancementService typeFinancementService) {
        this.typeFinancementService = typeFinancementService;
    }

    @GetMapping
    public ResponseEntity<List<TypeFinancement>> getAllTypeFinancements() {
        List<TypeFinancement> typeFinancements = typeFinancementService.getAllTypeFinancements();
        return new ResponseEntity<>(typeFinancements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeFinancement> getTypeFinancementById(@PathVariable("id") Long id) {
        TypeFinancement typeFinancement = typeFinancementService.getTypeFinancementById(id);
        return typeFinancement != null ? new ResponseEntity<>(typeFinancement, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/ad")
    public ResponseEntity<TypeFinancement> createTypeFinancement(@RequestBody TypeFinancement typeFinancement) {
        TypeFinancement createdTypeFinancement = typeFinancementService.createTypeFinancement(typeFinancement);
        return new ResponseEntity<>(createdTypeFinancement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeFinancement> updateTypeFinancement(@PathVariable("id") Long id,
                                                                 @RequestBody TypeFinancement typeFinancement) {
        TypeFinancement updatedTypeFinancement = typeFinancementService.updateTypeFinancement(id, typeFinancement);
        return updatedTypeFinancement != null ? new ResponseEntity<>(updatedTypeFinancement, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeFinancement(@PathVariable("id") Long id) {
        typeFinancementService.deleteTypeFinancement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
