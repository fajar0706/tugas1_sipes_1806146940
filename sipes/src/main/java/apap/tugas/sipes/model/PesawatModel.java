package apap.tugas.sipes.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_seri", nullable = false, unique = true)
    private String nomor_seri;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_dibuat", nullable = false)
    private String tempat_dibuat;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_dibuat", nullable = false)
    private Date tanggal_dibuat;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_pesawat", nullable = false)
    private String jenis_pesawat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipeId", referencedColumnName = "id_tipe", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawatModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @ManyToMany
    @JoinTable(
            name = "daftarTeknisi",
            joinColumns = @JoinColumn(name = "id_pesawat"),
            inverseJoinColumns = @JoinColumn(name = "id_teknisi"))
    private List<TeknisiModel> listTeknisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomor_seri() {
        return nomor_seri;
    }

    public void setNomor_seri(String nomor_seri) {
        this.nomor_seri = nomor_seri;
    }

    public String getTempat_dibuat() {
        return tempat_dibuat;
    }

    public void setTempat_dibuat(String tempat_dibuat) {
        this.tempat_dibuat = tempat_dibuat;
    }

    public Date getTanggal_dibuat() {
        return tanggal_dibuat;
    }

    public void setTanggal_dibuat(Date tanggal_dibuat) {
        this.tanggal_dibuat = tanggal_dibuat;
    }

    public String getJenis_pesawat() {
        return jenis_pesawat;
    }

    public void setJenis_pesawat(String jenis_pesawat) {
        this.jenis_pesawat = jenis_pesawat;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public List<TeknisiModel> getListTeknisi() {
        return listTeknisi;
    }

    public void setListTeknisi(List<TeknisiModel> listTeknisi) {
        this.listTeknisi = listTeknisi;
    }
}
