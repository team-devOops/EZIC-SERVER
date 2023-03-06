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
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "q_seq", nullable = false, columnDefinition = "bigint")
    private Long qSeq;

    @Comment("질문")
    @Column(name = "question", nullable = false, columnDefinition = "varchar(512)")
    private String question;

    @Comment("정답")
    @Column(name = "answer", nullable = false, columnDefinition = "text")
    private String answer;

    @Comment("레벨")
    @Column(name = "level", nullable = true, columnDefinition = "varchar(32)")
    private String level;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1)")
    private Flag useYn;

    public void changeQuestion(String question) {
        this.question = question;
    }

    public void changeAnswer(String answer) {
        this.answer = answer;
    }

    public void changeLevel(String level) {
        this.level = level;
    }

    public void changeUseYn(Flag useYn) {
        this.useYn = useYn;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qSeq=" + qSeq +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", level='" + level + '\'' +
                ", useYn=" + useYn +
                '}';
    }
}

