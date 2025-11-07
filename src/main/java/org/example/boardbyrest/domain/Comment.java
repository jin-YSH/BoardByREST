package org.example.boardbyrest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//CREATE TABLE Comments (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        content TEXT NOT NULL,
//        del_yn BOOLEAN DEFAULT FALSE,
//        regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
//        post_id INT NOT NULL,
//        user_id INT NOT NULL,
//        FOREIGN KEY (post_id) REFERENCES Posts(id),
//FOREIGN KEY (user_id) REFERENCES Users(id)
//        );
@Entity
@Table(name = "Comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name ="del_yn", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean delYn = false;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="post_id",nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id",nullable = false)
    private User user;
}
