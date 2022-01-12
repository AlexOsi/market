package com.osintsev.market.database.beat;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@RequiredArgsConstructor
@Table(name = "BEAT")
public class BeatEntity {

    @Setter(AccessLevel.NONE)
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;

    private String genre;

    private String image;

    private BigDecimal price;

    private Date loadDate;

    @JoinColumn(nullable = false)
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private AudioEntity audio;

    private Integer BPM;

    private String key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BeatEntity that = (BeatEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
