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
@Table(name="teknisi")
public class TeknisiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_teknisi;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_teknisi", nullable = false)
    private String nama_teknisi;

    @NotNull
    @Column(name = "nomor_telepon", nullable = false)
    private Long nomor_telepon;

    @ManyToMany(mappedBy = "listTeknisi")
    private List<PesawatModel> listPesawat;

    public Long getId_teknisi() {
        return id_teknisi;
    }

    public void setId_teknisi(Long id_teknisi) {
        this.id_teknisi = id_teknisi;
    }

    public String getNama_teknisi() {
        return nama_teknisi;
    }

    public void setNama_teknisi(String nama_teknisi) {
        this.nama_teknisi = nama_teknisi;
    }

    public Long getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(Long nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public List<PesawatModel> getListPesawat() {
        return listPesawat;
    }

    public void setListPesawat(List<PesawatModel> listPesawat) {
        this.listPesawat = listPesawat;
    }
}
