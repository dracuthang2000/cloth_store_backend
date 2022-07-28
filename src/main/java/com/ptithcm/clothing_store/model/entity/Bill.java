package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BILL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraph(name = "bill-graph",
    attributeNodes = {
        @NamedAttributeNode(value = "billProductDetails",subgraph = "sub-graph.bill.bill-product-detail"),
            @NamedAttributeNode(value = "customer"),
            @NamedAttributeNode(value = "orders")
    },
    subgraphs = {
        @NamedSubgraph(name = "sub-graph.bill.bill-product-detail",
        attributeNodes = {
            @NamedAttributeNode(value = "productColorSize",subgraph = "sub-product-color-size"),
        }),@NamedSubgraph(name = "sub-product-color-size"
                ,attributeNodes = {@NamedAttributeNode("size"),
            @NamedAttributeNode(value = "productColor",subgraph = "sub-product-color")}),
            @NamedSubgraph(name = "sub-product-color"
                    ,attributeNodes = {@NamedAttributeNode(value = "product",subgraph = "sub-product"),
                                        @NamedAttributeNode(value = "color")}),
            @NamedSubgraph(name = "sub-product",attributeNodes = {
                    @NamedAttributeNode("prices"),
                    @NamedAttributeNode("brand"),
                    @NamedAttributeNode("label"),
                    @NamedAttributeNode("material"),
                    @NamedAttributeNode("gender"),
                    @NamedAttributeNode(value = "productDiscounts",subgraph = "sub-discount"),
            }),
            @NamedSubgraph(name = "sub-discount"
                    ,attributeNodes ={@NamedAttributeNode("discount")})

})
public class Bill extends IdAndVersion {
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "is_payment")
    private Boolean isPayment;
    @Column(name = "note")
    private String note;
    @Column(name = "total")
    private Long total;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "mail")
    private String mail;
    @ManyToOne
    @JoinColumn(name = "id_customer",referencedColumnName = "id")
    private Customer customer;
    @OneToMany(mappedBy = "bill",cascade = {CascadeType.ALL},orphanRemoval = true)
    private Set<BillProductDetail> billProductDetails = new HashSet<>();
    @OneToOne(mappedBy = "bill",cascade = {CascadeType.ALL})
    private Orders orders;

    public void addBillDetail(BillProductDetail detail){
        if(billProductDetails==null){
            billProductDetails = new HashSet<>();
        }
        billProductDetails.add(detail);
        detail.setBill(this);
    }
    public void  addOrder(Orders orders){
        orders.setBill(this);
        this.setOrders(orders);
    }
}
