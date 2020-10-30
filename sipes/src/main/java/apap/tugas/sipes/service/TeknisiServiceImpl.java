package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeknisiServiceImpl implements  TeknisiService {
    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public List<TeknisiModel> findAllTeknisi() {
        return teknisiDb.findAll();
    }

    @Override
    public void addTeknisi(TeknisiModel teknisi) {
        teknisiDb.save(teknisi);
    }

    @Override
    public Optional<TeknisiModel> getTeknisiByID(Long id_teknisi) {
        return teknisiDb.findById(id_teknisi);
    }
}
