package apap.tugas.sipes.repository;


import apap.tugas.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipeDb extends JpaRepository<TipeModel,Long> {
    List<TipeModel> findAll();
}