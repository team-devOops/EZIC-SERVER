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
@Table(name = "test_mst")
@AllArgsConstructor
@NoArgsConstructor
public class TestMst extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "t_seq", nullable = false, columnDefinition = "bigint")
    private Long tSeq;

    @Comment("질문 수")
    @Column(name = "question_cnt", nullable = false, columnDefinition = "bigint")
    private int questionCnt;

    @Comment("질문 리스트")
    @Column(name = "question_list", nullable = false, columnDefinition = "varchar(512)")
    private String questionList;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1)")
    private Flag useYn;

    public void changeUseYn(Flag useYn) {
        this.useYn = useYn;
    }
}

