package com.project.inventory.app.products;

import com.project.inventory.app.categories.entity.Categories;
import com.project.inventory.app.unitsOfMeasure.entity.UnitsOfMeasure;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id")
    private  UnitsOfMeasure unitOfMeasure;
    private BigDecimal costPrice;
    private Integer minimumStock;
    @Builder.Default
    private Boolean isActive = true;
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Products products = (Products) o;
        return getId() != null && Objects.equals(getId(), products.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
