package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Wilaya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WilayaService {
    List<Wilaya> getAllWilayas();
    Wilaya getWilayaById(Long id);
    Wilaya createWilaya(Wilaya wilaya);
    Wilaya updateWilaya(Long id, Wilaya updatedWilaya);
    void deleteWilaya(Long id);
}
