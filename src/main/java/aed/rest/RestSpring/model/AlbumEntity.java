package aed.rest.RestSpring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "album", schema = "musica", catalog = "")
public class AlbumEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "anyo", nullable = false)
    private Integer anyo;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "artista_id", referencedColumnName = "id")
    private ArtistaEntity artistaByArtistaId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private GeneroEntity generoByGenreId;
    @JsonManagedReference
    @OneToMany(mappedBy = "albumByAlbumId")
    private Collection<CancionEntity> cancionsById;

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

    public Integer getAnyo() {
        return anyo;
    }

    public void setAnyo(Integer anyo) {
        this.anyo = anyo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumEntity that = (AlbumEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(anyo, that.anyo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, anyo);
    }

    public ArtistaEntity getArtistaByArtistaId() {
        return artistaByArtistaId;
    }

    public void setArtistaByArtistaId(ArtistaEntity artistaByArtistaId) {
        this.artistaByArtistaId = artistaByArtistaId;
    }

    public GeneroEntity getGeneroByGenreId() {
        return generoByGenreId;
    }

    public void setGeneroByGenreId(GeneroEntity generoByGenreId) {
        this.generoByGenreId = generoByGenreId;
    }

    public Collection<CancionEntity> getCancionsById() {
        return cancionsById;
    }

    public void setCancionsById(Collection<CancionEntity> cancionsById) {
        this.cancionsById = cancionsById;
    }
}
