package aed.rest.RestSpring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "genero", schema = "musica", catalog = "")
public class GeneroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @JsonManagedReference
    @OneToMany(mappedBy = "generoByGenreId")
    private Collection<AlbumEntity> albumsById;

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
        GeneroEntity that = (GeneroEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public Collection<AlbumEntity> getAlbumsById() {
        return albumsById;
    }

    public void setAlbumsById(Collection<AlbumEntity> albumsById) {
        this.albumsById = albumsById;
    }
}
