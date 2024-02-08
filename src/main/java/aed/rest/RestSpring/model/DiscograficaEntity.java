package aed.rest.RestSpring.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "discografica", schema = "musica", catalog = "")
public class DiscograficaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @OneToMany(mappedBy = "discograficaByDiscograficaId")
    private Collection<ArtistaEntity> artistasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscograficaEntity that = (DiscograficaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public Collection<ArtistaEntity> getArtistasById() {
        return artistasById;
    }

    public void setArtistasById(Collection<ArtistaEntity> artistasById) {
        this.artistasById = artistasById;
    }
}
