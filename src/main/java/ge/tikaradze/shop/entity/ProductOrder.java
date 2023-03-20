package ge.tikaradze.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderName;

    @JoinColumn(name = "app_user_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    private AppUser appUser;

    @Column(name = "app_user_id")
    private Long appUserId;

    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "product_id")
    private Long productId;
}
