package creditdirect.clientmicrocervice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import creditdirect.clientmicrocervice.entities.DirectionRegionale;
import creditdirect.clientmicrocervice.repositories.DirectionRegionaleRepository;
import java.util.List;

@Service
public class DirectionRegionaleServiceImpl implements DirectionRegionaleService {

    private final DirectionRegionaleRepository directionRegionaleRepository;

    @Autowired
    public DirectionRegionaleServiceImpl(DirectionRegionaleRepository directionRegionaleRepository) {
        this.directionRegionaleRepository = directionRegionaleRepository;
    }

    @Override
    public List<DirectionRegionale> getAllDirectionRegionales() {
        return directionRegionaleRepository.findAll();
    }

    @Override
    public DirectionRegionale getDirectionRegionaleById(Long id) {
        return directionRegionaleRepository.findById(id)
                .orElse(null); // handle if not found
    }

    @Override
    public DirectionRegionale createDirectionRegionale(DirectionRegionale directionRegionale) {
        System.out.println(directionRegionale);
        return directionRegionaleRepository.save(directionRegionale);
    }

    @Override
    public void deleteDirectionRegionale(Long id) {
        directionRegionaleRepository.deleteById(id);
    }

    // Other methods if needed
}
