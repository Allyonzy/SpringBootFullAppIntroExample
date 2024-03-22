package ru.innopolis.shop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

/**
 * Класс продукта
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String disclaimer;
    private Integer price;
    private String image;

    @OneToMany(mappedBy = "item", cascade = {CascadeType.REMOVE})
    private List<Basket> basket;

    @OneToMany(mappedBy = "item", cascade = {CascadeType.REMOVE})
    private List<ListOrder> listOrder;
}
