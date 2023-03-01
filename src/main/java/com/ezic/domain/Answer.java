package com.ezic.domain;

import com.ezic.global.domain.BaseDomain;
import com.ezic.global.domain.Flag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "answer")
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "a_seq", nullable = false, columnDefinition = "bigint")
    private Long aSeq;

    @Comment("qSeq")
    @Column(name = "q_seq", nullable = false, columnDefinition = "bigint")
    private Long qSeq;

    @Comment("정답")
    @Column(name = "answer", nullable = false, columnDefinition = "text")
    private String answer;

    @Comment("정답여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "answer_yn", nullable = false, columnDefinition = "char(1)")
    private Flag answerYn;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1)")
    private Flag useYn;

    public void changeUseYn(Flag useYn) {
        this.useYn = useYn;
    }
}

