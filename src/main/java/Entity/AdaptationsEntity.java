package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;


@JsonAutoDetect
@Entity
@Table(name = "Adaptations", schema = "dbo", catalog = "LibrarySystem")
public class AdaptationsEntity {

    public AdaptationsEntity() {
    }

    public AdaptationsEntity(int adaptationId, String typeAdaptation, int year, String country) {
        this.id = adaptationId;
        this.typeAdaptation = typeAdaptation;
        this.year = year;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "TypeAdaptation", nullable = false, length = 800)
    private String typeAdaptation;

    @Basic
    @Column(name = "Year_", nullable = true)
    private Integer year;

    @Basic
    @Column(name = "Country", nullable = true, length = 800)
    private String country;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookID")
    private BookEntity bookForAdaptation;


    public int getId() {
        return id;
    }


    public void setId(int adaptationId) {
        this.id = adaptationId;
    }


    public String getTypeAdaptation() {
        return typeAdaptation;
    }


    public void setTypeAdaptation(String typeAdaptation) {
        this.typeAdaptation = typeAdaptation;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public BookEntity getBookForAdaptation() {
        return bookForAdaptation;
    }

    public void setBookForAdaptation(BookEntity bookForAdaptation) {
        this.bookForAdaptation = bookForAdaptation;
    }


    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }


    @Override
    public String toString() {
        try {
            return toJSON();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Не удалось преобразовать к JSON";
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdaptationsEntity that = (AdaptationsEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(typeAdaptation, that.typeAdaptation)) return false;
        if (!Objects.equals(year, that.year)) return false;
        if (!Objects.equals(country, that.country)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeAdaptation != null ? typeAdaptation.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
