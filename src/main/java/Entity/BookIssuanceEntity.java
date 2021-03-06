package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@JsonAutoDetect
@Entity
@Table(name = "BookIssuance", schema = "dbo", catalog = "LibrarySystem")
public class BookIssuanceEntity {

    public BookIssuanceEntity() {
    }

    public BookIssuanceEntity(int bookIssuanceId, Date dateOfIssue, Date dateOfCompletion) {
        this.id = bookIssuanceId;
        this.dateOfIssue = dateOfIssue;
        this.dateOfCompletion = dateOfCompletion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "DateOfIssue", nullable = false)
    private Date dateOfIssue;

    @Basic
    @Column(name = "DateOfCompletion", nullable = true)
    private Date dateOfCompletion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookCopyID")
    private BookCopyEntity copyForIssuance;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LibraryCard")
    private ReadersEntity readerForIssuance;

    @JsonIgnore
    @OneToOne(mappedBy = "issuanceForFine")
    private FineEntity fineForIssuance;


    public int getId() { return id; }

    public void setId(int bookIssuanceId) { this.id = bookIssuanceId; }


    public Date getDateOfIssue() { return dateOfIssue; }

    public void setDateOfIssue(Date dateOfIssue) { this.dateOfIssue = dateOfIssue; }


    public Date getDateOfCompletion() { return dateOfCompletion; }

    public void setDateOfCompletion(Date dateOfCompletion) { this.dateOfCompletion = dateOfCompletion; }


    public BookCopyEntity getCopyForIssuance() { return copyForIssuance; }

    public void setCopyForIssuance(BookCopyEntity copyForIssuance) { this.copyForIssuance = copyForIssuance; }


    public ReadersEntity getReaderForIssuance() { return readerForIssuance; }

    public void setReaderForIssuance(ReadersEntity readerForIssuance) { this.readerForIssuance = readerForIssuance; }


    public FineEntity getFineForIssuance() { return fineForIssuance; }

    public void setFineForIssuance(FineEntity fineForIssuance) { this.fineForIssuance = fineForIssuance; }


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

        BookIssuanceEntity that = (BookIssuanceEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(dateOfIssue, that.dateOfIssue)) return false;
        if (!Objects.equals(dateOfCompletion, that.dateOfCompletion))
            return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (dateOfCompletion != null ? dateOfCompletion.hashCode() : 0);
        return result;
    }
}
