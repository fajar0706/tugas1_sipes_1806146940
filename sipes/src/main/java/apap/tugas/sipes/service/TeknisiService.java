package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;

import java.util.List;
import java.util.Optional;

public interface TeknisiService {
    List<TeknisiModel> findAllTeknisi();
    void addTeknisi(TeknisiModel teknisi);
    Optional<TeknisiModel> getTeknisiByID(Long id_teknisi);
}
