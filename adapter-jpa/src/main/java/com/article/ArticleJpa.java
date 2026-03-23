package com.article;

import jakarta.persistence.*;


@Entity
@Table(name="articles")
public class ArticleJpa {

    @Id
    public String id;

    public String title;
    @Column(name = "description")
    public String desc;

}
