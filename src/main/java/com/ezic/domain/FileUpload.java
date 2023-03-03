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
@Table(name = "file_upload")
@AllArgsConstructor
@NoArgsConstructor
public class FileUpload extends BaseDomain {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false, columnDefinition = "bigint")
    private Long seq;

    @Comment("파일명")
    @Column(name = "origin_name", nullable = false, columnDefinition = "varchar(512)")
    private String originName;

    @Comment("업로드 성공 여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "upload_yn", nullable = false, columnDefinition = "char(1)")
    private Flag uploadYn;

    @Comment("사용 여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1)")
    private Flag useYn;

    public void changeUploadYn(Flag flag) {
        this.uploadYn = flag;
    }

    public void changeUseYn(Flag useYn) {
        this.useYn = useYn;
    }
}
