package com.ezic.domain;

import com.ezic.global.domain.BaseDomain;
import com.ezic.global.domain.Flag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comments extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "c_seq", nullable = false, columnDefinition = "bigint")
    private Long cSeq;

    @org.hibernate.annotations.Comment("qSeq")
    @Column(name = "q_seq", nullable = false, columnDefinition = "bigint")
    private Long qSeq;

    @org.hibernate.annotations.Comment("참조 cSeq")
    @Column(name = "ref_c_seq", nullable = false, columnDefinition = "bigint")
    private Long refCSeq;

    @org.hibernate.annotations.Comment("내용")
    @Column(name = "comments", nullable = false, columnDefinition = "varchar(512)")
    private String comments;

    @org.hibernate.annotations.Comment("코멘트레벨")
    @Column(name = "level", nullable = false, columnDefinition = "bigint")
    private Long level;

    @org.hibernate.annotations.Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1)")
    private Flag useYn;

    @org.hibernate.annotations.Comment("추천수")
    @Column(name = "like", nullable = false, columnDefinition = "bigint")
    private Long like;

    public void changeUseYn(Flag useYn) {
        this.useYn = useYn;
    }

    public void changeComments(String comments) {
        this.comments = comments;
    }
}
