package org.example.boardbyrest.domain;

//CREATE TABLE Posts (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(255) NOT NULL,
//content TEXT NOT NULL,
//posted_date DATETIME DEFAULT CURRENT_TIMESTAMP,
//user_id INT NOT NULL,
//FOREIGN KEY (user_id) REFERENCES Users(id)
//        );


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="Posts")
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "posted_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // FK 컬럼명 명시
    private User user;
}


