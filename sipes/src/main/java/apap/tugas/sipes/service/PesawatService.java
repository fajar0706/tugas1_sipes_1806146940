package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import java.util.List;
import java.util.Optional;

public interface PesawatService {
    List<PesawatModel> findAllPesawat();
    void addPesawat(PesawatModel pesawat);
    String generateNomorSeri(PesawatModel pesawat);
    Optional<PesawatModel> getPesawatById(Long id);
    PesawatModel changePesawat(PesawatModel pesawat);
    List<PesawatModel> findAllPesawatTua(List<PesawatModel> listPesawat);
}
