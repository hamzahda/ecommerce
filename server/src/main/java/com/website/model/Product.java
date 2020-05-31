package com.website.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* 
Author hamzahda

*/


@Entity
public class Product{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;
  
  @Column()
  private double price;

  @Column()
  private String img_link;
  
  public String getImg_link() {
    return img_link  ;
  }

  public void setImg_link(String img_link) {
    this.img_link = img_link;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


}
