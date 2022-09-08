package com.project.crux.domain;

import com.project.crux.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column
    private String imgUrl;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.REMOVE)
    private List<MemberCrew> memberCrewList = new ArrayList<>();

    @Formula("(select count(*) from member_crew mc where mc.crew_id = id)")
    private int countOfMemberCrewList;

    @Builder
    public Crew(String name, String content, String imgUrl) {
        Assert.hasText(name, ErrorCode.INVALID_CREW_NAME.getErrorMessage());
        this.name = name;
        this.content = content;
        this.imgUrl = imgUrl;
    }
}
