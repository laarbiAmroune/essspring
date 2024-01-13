package creditdirect.clientmicrocervice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import creditdirect.clientmicrocervice.entities.DirectionRegionale;
import creditdirect.clientmicrocervice.services.DirectionRegionaleService;
import java.util.List;

@RestController
@RequestMapping("/directionregionales")
public class DirectionRegionaleController {

    @Autowired
    private DirectionRegionaleService directionRegionaleService;

    @GetMapping
    public ResponseEntity<List<DirectionRegionale>> getAllDirectionRegionales() {
        List<DirectionRegionale> directionRegionales = directionRegionaleService.getAllDirectionRegionales();
        return new ResponseEntity<>(directionRegionales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectionRegionale> getDirectionRegionaleById(@PathVariable("id") Long id) {
        DirectionRegionale directionRegionale = directionRegionaleService.getDirectionRegionaleById(id);
        if (directionRegionale != null) {
            return new ResponseEntity<>(directionRegionale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DirectionRegionale> createDirectionRegionale(@RequestBody DirectionRegionale directionRegionale) {
        System.out.println(directionRegionale); // Print received object

        DirectionRegionale createdDirectionRegionale = directionRegionaleService.createDirectionRegionale(directionRegionale);
        return new ResponseEntity<>(createdDirectionRegionale, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirectionRegionale(@PathVariable("id") Long id) {
        directionRegionaleService.deleteDirectionRegionale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
