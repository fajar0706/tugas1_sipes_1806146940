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

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PenerbanganController {
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

    @RequestMapping(path = "/penerbangan", method = RequestMethod.GET)
    public String view(Model model){
        List<PenerbanganModel> allPenerbangan = penerbanganService.findAllPenerbangan();
        model.addAttribute("listPenerbangan",allPenerbangan);
        return "daftar-penerbangan";
    }
    @RequestMapping(value= "/penerbangan/tambah",method = RequestMethod.GET)
    public String addPenerbanganForm(Model model){
        PenerbanganModel newPenerbangan = new PenerbanganModel();
        model.addAttribute("penerbangan",newPenerbangan);
        return "form-tambah-penerbangan";
    }
    @RequestMapping(value ="/penerbangan/tambah", method = RequestMethod.POST)
    public String addPenerbanganSimpan(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){

        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("penerbangan",penerbangan);
        return "tambah-penerbangan";
    }
    @RequestMapping(path = "/penerbangan/view", method = RequestMethod.GET)
    public String viewPenerbangan(@RequestParam(value = "id_penerbangan") Long id_penerbangan, Model model){
        PenerbanganModel penerbanganAda = penerbanganService.getPenerbanganById(id_penerbangan).get();
        model.addAttribute("penerbangan", penerbanganAda);
        return "lihat-penerbangan";
    }
    @RequestMapping(value = "/penerbangan/ubah/{id_penerbangan}", method = RequestMethod.GET)
    public String changePenerbanganFormPage(@PathVariable Long id_penerbangan, Model model){
        PenerbanganModel penerbanganAda = penerbanganService.getPenerbanganById(id_penerbangan).get();
        model.addAttribute("penerbangan", penerbanganAda);
        return "form-ubah-penerbangan";
    }
    @RequestMapping(value = "/penerbangan/ubah/{id_penerbangan}", method = RequestMethod.POST)
    public String changePenerbanganFormSubmit(
            @PathVariable Long id_penerbangan,
            @ModelAttribute PenerbanganModel penerbangan
            , Model model){
        PenerbanganModel newPenerbangan = penerbanganService.changePenerbangan(penerbangan);
        model.addAttribute("penerbangan", newPenerbangan);
        return "ubah-data-penerbangan";
    }
    @RequestMapping("/penerbangan/hapus/{id_penerbangan}")
    public String deletePenerbangan(@PathVariable Long id_penerbangan, Model model) {
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id_penerbangan).get();
        penerbanganService.deletePenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "delete-data-penerbangan";
    }

}
