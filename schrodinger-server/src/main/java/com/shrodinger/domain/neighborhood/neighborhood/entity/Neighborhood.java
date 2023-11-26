package com.shrodinger.domain.neighborhood.neighborhood.entity;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class Neighborhood extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "neighborhood_id")
    private Long id;

    @Column(nullable = false)
    private String city;  //시

    @Column(nullable = false)
    private String gu; // 구

    @Column(nullable = false)
    private String dong; // 동

    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

}
