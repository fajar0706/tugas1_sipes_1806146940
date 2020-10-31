package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @Qualifier("teknisiServiceImpl")
    @Autowired
    private TeknisiService teknisiService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @RequestMapping(path = "/pesawat", method = RequestMethod.GET)
    public String view(Model model){
        List<PesawatModel> allPesawat = pesawatService.findAllPesawat();
        model.addAttribute("listPesawat",allPesawat);

        return "daftar-pesawat";
    }
    @RequestMapping(value= "/pesawat/tambah",method = RequestMethod.GET)
    public String addPesawatForm(Model model){
        PesawatModel newPesawat = new PesawatModel();
        List<TeknisiModel> listTeknisi = teknisiService.findAllTeknisi();
        List<TipeModel> listTipe = tipeService.findAllTipeService();

        ArrayList<TeknisiModel> newTeknisiList = new ArrayList<TeknisiModel>();
        newTeknisiList.add(new TeknisiModel());
        newPesawat.setListTeknisi(newTeknisiList);

        model.addAttribute("listTipe",listTipe);
        model.addAttribute("teknisiList",listTeknisi);
        model.addAttribute("pesawat",newPesawat);
        return "form-tambah-pesawat";
    }
    @RequestMapping(value ="/pesawat/tambah", method = RequestMethod.POST)
    public String addPesawatSimpan(
            @ModelAttribute PesawatModel pesawat,
            Model model){
        pesawat.setNomor_seri(pesawatService.generateNomorSeri(pesawat));
        pesawatService.addPesawat(pesawat);
        PesawatModel targetPesawat = pesawatService.getPesawatById(pesawat.getId()).get();
        for (int i = 0; i < targetPesawat.getListTeknisi().size(); i++) {
            TeknisiModel teknisi = targetPesawat.getListTeknisi().get(i);
            if(teknisi.getListPesawat()==null){
                teknisi.setListPesawat(new ArrayList<PesawatModel>());
            }
            teknisi.getListPesawat().add(targetPesawat);
            teknisiService.addTeknisi(teknisi);
        }
        model.addAttribute("pesawat",pesawat);
        return "tambah-pesawat";
    }
    @RequestMapping(value = "/pesawat/tambah", method = RequestMethod.POST, params = {"addRow"})
    public String addRowPesawat(@ModelAttribute PesawatModel pesawat, BindingResult bindingResult, Model model){
        if(pesawat.getListTeknisi() == null){
            pesawat.setListTeknisi(new ArrayList<TeknisiModel>());
        }
        pesawat.getListTeknisi().add(new TeknisiModel());
        List<TipeModel> listTipe = tipeService.findAllTipeService();
        List<TeknisiModel> listTeknisi = teknisiService.findAllTeknisi();
        model.addAttribute("pesawat",pesawat);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("teknisiList", listTeknisi);
        return "form-tambah-pesawat";
    }
    @RequestMapping(path = "/pesawat/view", method = RequestMethod.GET)
    public String viewPesawat(@RequestParam(value = "id") Long id, Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(id).get();
        model.addAttribute("pesawat", pesawat);
        return "lihat-pesawat";
    }

    @RequestMapping(value = "/pesawat/ubah/{id}", method = RequestMethod.GET)
    public String changePesawatFormPage(@PathVariable Long id, Model model){
        PesawatModel adaPesawat = pesawatService.getPesawatById(id).get();
        model.addAttribute("pesawat", adaPesawat);
        return "form-ubah-pesawat";
    }
    @RequestMapping(value = "pesawat/ubah/{id}", method = RequestMethod.POST)
    public String changePesawatFormSubmit(
            @PathVariable Long id,
            @ModelAttribute PesawatModel pesawat,
            Model model){
        PesawatModel newPesawatData = pesawatService.changePesawat(pesawat);
        model.addAttribute("pesawat", newPesawatData);
        return "ubah-data-pesawat";
    }
    @RequestMapping(path = "/cari/pesawat/pesawat-tua",method = RequestMethod.GET)
    public String pesawatTua(Model model){
        List<PesawatModel> listPesawat = pesawatService.findAllPesawat();
        List<PesawatModel> listPesawatTua = pesawatService.findAllPesawatTua(listPesawat);
        model.addAttribute("listPesawatTua", listPesawatTua);
        return "daftar-pesawat-tua";
    }
    @RequestMapping(path = "/pesawat/{id}/tambah-penerbangan", method = RequestMethod.GET)
    public String tambahPenerbangan(
            @PathVariable Long id,Model model){
        PenerbanganModel newPenerbangan = new PenerbanganModel();
        PesawatModel pesawatId = pesawatService.getPesawatById(id).get();
        List<PenerbanganModel> listPenerbangan = penerbanganService.findAllPenerbangan();
        model.addAttribute("penerbanganList", listPenerbangan);
        model.addAttribute("pesawat",pesawatId);
        model.addAttribute("penerbangan",newPenerbangan);
        return  "lihat-pesawat-penerbangan";
    }
    @RequestMapping(value = "/pesawat/{id}/tambah-penerbangan",method = RequestMethod.POST)
    public String viewPenerbanganPesawat(
            @PathVariable Long id,
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){
        PenerbanganModel penerbanganId = penerbanganService.getPenerbanganById(penerbangan.getId_penerbangan()).get();
        System.out.println(penerbangan.getId_penerbangan());
        penerbanganId.setPesawatModel(pesawatService.getPesawatById(id).get());
        List<PenerbanganModel> listPenerbangan = penerbanganService.findAllPenerbangan();
        PesawatModel pesawatId = pesawatService.getPesawatById(id).get();
        model.addAttribute("penerbanganList", listPenerbangan);
        model.addAttribute("pesawat",pesawatId);
        penerbangan = new PenerbanganModel();
        model.addAttribute("penerbangan",penerbangan);

        return "lihat-pesawat-penerbangan";
    }
}