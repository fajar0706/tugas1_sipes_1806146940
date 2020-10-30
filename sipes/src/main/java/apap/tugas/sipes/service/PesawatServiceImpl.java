package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import apap.tugas.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public List<PesawatModel> findAllPesawat() {
        return pesawatDb.findAll();
    }

    @Override
    public void addPesawat(PesawatModel pesawat) {
        pesawatDb.save(pesawat);
    }

    @Override
    public String generateNomorSeri(PesawatModel pesawat) {
        String nomor_seri = "";
        if(pesawat.getJenis_pesawat().equals("Komersial")){
            nomor_seri += "1";
        }
        else if(pesawat.getJenis_pesawat().equals("Militer")){
            nomor_seri += "2";
        }
        if (pesawat.getTipe().getNama_tipe().equals("Boeing")){
            nomor_seri += "BO";
        }
        else if (pesawat.getTipe().getNama_tipe().equals("ATR")){
            nomor_seri += "AT";
        }
        else if (pesawat.getTipe().getNama_tipe().equals("Airbus")){
            nomor_seri += "AB";
        }
        else if (pesawat.getTipe().getNama_tipe().equals("Bombardier")){
            nomor_seri += "BB";
        }

//        String [] tanggalDibuat = pesawat.getTanggal_dibuat().toString().split("-");
//        String year = tanggalDibuat[0];
//        System.out.println(year);
//        String reverse = new StringBuffer(year).reverse().toString();
//        System.out.println(reverse);
//        nomor_seri += reverse;

        Date date = pesawat.getTanggal_dibuat();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(date);
        String reverse = new StringBuffer(year).reverse().toString();
        nomor_seri += reverse;

        int tanggalDibuatExtra = Integer.parseInt(year) + 8;
        nomor_seri += String.valueOf(tanggalDibuatExtra);

        Random r = new Random();
        char a = (char)(r.nextInt(26) + 'A');
        char b = (char)(r.nextInt(26) + 'A');
        nomor_seri = nomor_seri + a + b;

        return nomor_seri;
    }

    @Override
    public Optional<PesawatModel> getPesawatById(Long id) {
        return pesawatDb.findById(id);
    }

    @Override
    public PesawatModel changePesawat(PesawatModel pesawat) {
        PesawatModel targetPesawat = pesawatDb.findById(pesawat.getId()).get();
        try{
            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTanggal_dibuat(pesawat.getTanggal_dibuat());
            targetPesawat.setTempat_dibuat(pesawat.getTempat_dibuat());
            targetPesawat.setJenis_pesawat(pesawat.getJenis_pesawat());
            targetPesawat.setNomor_seri(generateNomorSeri(targetPesawat));
            pesawatDb.save(targetPesawat);
            return targetPesawat;
        } catch (NullPointerException nullException){
            return null;
        }
    }

}
