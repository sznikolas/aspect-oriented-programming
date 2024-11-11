package com.nikolas.JobApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobPost {

    @Id
    private int postId;
    private String postProfile;
    private String postDesc;
    private Integer reqExperience;

//    @ElementCollection
//    @CollectionTable(name = "post_tech_stack", joinColumns = @JoinColumn(name = "post_id"))
//    @Column(name = "tech_stack")
    private List<String> postTechStack;
}
