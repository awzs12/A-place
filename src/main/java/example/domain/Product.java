package example.domain;

import javax.persistence.*;

public class Product {
    @Id
    @Column(name = "pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      //상품 코드

    @Column(nullable = false, length = 50)
    private String proName; // 상품명

    @Column(name="price", nullable = false)
    private int price;      //상품 가격

    @Lob
    @Column(nullable = false)
    private String proInfo;  //상품 정보







}
