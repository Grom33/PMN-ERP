package ru.gromov.common.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
@ToString
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntity implements HasId, Serializable {

    private static final long serialVersionUID = 1L;

    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(name = "global_seq",
            sequenceName = "global_seq",
            allocationSize = 1,
            initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //  See https://hibernate.atlassian.net/browse/HHH-3718 and https://hibernate.atlassian.net/browse/HHH-12034
    //  Proxy initialization when accessing its identifier managed now by JPA_PROXY_COMPLIANCE setting
    protected Long id;

    @Version
    @Column(name = "version", nullable = false)
    protected long versionUID;

    @Column(name = "active", nullable = false)
    protected Boolean active;

    @Column(name = "trash", nullable = false)
    protected Boolean trash;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    protected Long createdAt;

    @Column(name = "modified_date")
    @LastModifiedDate
    protected Long lastModified;


    //toDo https://www.baeldung.com/database-auditing-jpa

    /*	@CreatedBy
        @Column(name = "created_by", nullable = false, length = 50, updatable = false)
        @JsonIgnore
        private String createdBy;

        @CreatedDate
        @Column(name = "created_date", updatable = false)
        @JsonIgnore
        private Instant createdDate = Instant.now();

        @LastModifiedBy
        @Column(name = "last_modified_by", length = 50)
        @JsonIgnore
        private String lastModifiedBy;

        @LastModifiedDate
        @Column(name = "last_modified_date")
        @JsonIgnore
        private Instant lastModifiedDate = Instant.now();*/
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : Long.hashCode(getId());
    }

}
