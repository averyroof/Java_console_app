package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

@JsonAutoDetect
@Entity
@Table(name = "Fine", schema = "dbo", catalog = "LibrarySystem")
public class FineEntity {

    public FineEntity() {
    }

    public FineEntity(int fineId, Integer amount) {
        this.id = fineId;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "Amount", nullable = false)
    private Integer amount;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookIssuanceID")
    private BookIssuanceEntity issuanceForFine;


    public int getId() { return id; }

    public void setId(int fineId) { this.id = fineId; }


    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }


    public BookIssuanceEntity getIssuanceForFine() { return issuanceForFine; }

    public void setIssuanceForFine(BookIssuanceEntity issuanceForFine) { this.issuanceForFine = issuanceForFine; }


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
        if (!(o instanceof FineEntity)) return false;

        FineEntity that = (FineEntity) o;

        if (id != that.id) return false;
        return Objects.equals(amount, that.amount);
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
