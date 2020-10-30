package apap.tugas.sipes.repository;


import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.TeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeknisiDb extends JpaRepository<TeknisiModel,Long> {
    List<TeknisiModel> findAll();
    Optional<TeknisiModel> findById(Long id_teknisi);
}