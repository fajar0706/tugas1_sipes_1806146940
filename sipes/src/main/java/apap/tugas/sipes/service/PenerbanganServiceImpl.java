package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> findAllPenerbangan() {
        return penerbanganDb.findAll();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public Optional<PenerbanganModel> getPenerbanganById(Long id_penerbangan) {
        return penerbanganDb.findById(id_penerbangan);
    }

    @Override
    public PenerbanganModel changePenerbangan(PenerbanganModel penerbangan) {
        PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getId_penerbangan()).get();
        try{
            targetPenerbangan.setNomor_penerbangan(penerbangan.getNomor_penerbangan());
            targetPenerbangan.setKode_bandara_asal(penerbangan.getKode_bandara_asal());
            targetPenerbangan.setKode_bandara_tujuan(penerbangan.getKode_bandara_tujuan());
            targetPenerbangan.setWaktu_berangkat(penerbangan.getWaktu_berangkat());
            penerbanganDb.save(targetPenerbangan);
            return targetPenerbangan;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);
    }


}
