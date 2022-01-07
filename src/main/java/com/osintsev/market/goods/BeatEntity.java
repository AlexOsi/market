package com.osintsev.market.goods;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "BEAT")
class BeatEntity {

    @Setter(AccessLevel.NONE)
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;

    private String genre;

    private String image;

    private BigDecimal price;

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
