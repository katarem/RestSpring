package aed.rest.RestSpring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cancion", schema = "musica", catalog = "")
public class CancionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "duracion", nullable = false, precision = 0)
    private double duracion;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
    private AlbumEntity albumByAlbumId;

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

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancionEntity that = (CancionEntity) o;
        return Double.compare(duracion, that.duracion) == 0 && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, duracion);
    }

    public AlbumEntity getAlbumByAlbumId() {
        return albumByAlbumId;
    }

    public void setAlbumByAlbumId(AlbumEntity albumByAlbumId) {
        this.albumByAlbumId = albumByAlbumId;
    }
}
