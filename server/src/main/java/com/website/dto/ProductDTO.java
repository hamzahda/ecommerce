package com.website.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProductDTO {


@ApiModelProperty(position =1)
  private String name;
  @ApiModelProperty(position =2)
  private double price;
  @ApiModelProperty(position =3)
  private String img_link;
  @ApiModelProperty(position =4)
  private int stock;

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

  public String getImg_link() {
      return img_link;
  }

  public void setImg_link(String img_link) {
      this.img_link = img_link;
  }

  public int getStock() {
      return stock;
  }

  public void setStock(int stock) {
      this.stock = stock;
  }


}