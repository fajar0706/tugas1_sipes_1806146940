package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;


import java.util.List;
import java.util.Optional;

public interface PenerbanganService {
    List<PenerbanganModel> findAllPenerbangan();
    void addPenerbangan(PenerbanganModel penerbangan);
    Optional<PenerbanganModel> getPenerbanganById(Long id_penerbangan);
    PenerbanganModel changePenerbangan(PenerbanganModel penerbangan);
    void deletePenerbangan(PenerbanganModel penerbangan);
}
