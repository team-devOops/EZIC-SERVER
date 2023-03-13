package com.ezic.domain;

import com.ezic.global.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "test_det")
@AllArgsConstructor
@NoArgsConstructor
public class TestDet extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false, columnDefinition = "bigint")
    private Long seq;

    @Comment("t_seq")
    @Column(name = "t_seq", nullable = false, columnDefinition = "bigint")
    private Long tSeq;

    @Comment("q_seq")
    @Column(name = "q_seq", nullable = false, columnDefinition = "bigint")
    private Long qSeq;

    @Comment("a_seq")
    @Column(name = "a_seq", nullable = false, columnDefinition = "bigint")
    private Long aSeq;
}

