package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Wilaya;
import creditdirect.clientmicrocervice.repositories.WilayaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WilayaServiceImpl implements WilayaService {

    private final WilayaRepository wilayaRepository;

    @Autowired
    public WilayaServiceImpl(WilayaRepository wilayaRepository) {
        this.wilayaRepository = wilayaRepository;
    }

    @Override
    public List<Wilaya> getAllWilayas() {
        return wilayaRepository.findAll();
    }

    @Override
    public Wilaya getWilayaById(Long id) {
        Optional<Wilaya> optionalWilaya = wilayaRepository.findById(id);
        return optionalWilaya.orElse(null);
    }



    @Override
    public Wilaya createWilaya(Wilaya wilaya) {
        return wilayaRepository.save(wilaya);
    }

    @Override
    public Wilaya updateWilaya(Long id, Wilaya updatedWilaya) {
        if (wilayaRepository.existsById(id)) {
            updatedWilaya.setId(id); // Ensure the ID is set for update
            return wilayaRepository.save(updatedWilaya);
        }
        return null;
    }

    @Override
    public void deleteWilaya(Long id) {
        wilayaRepository.deleteById(id);
    }
}
