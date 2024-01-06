package com.fekim.workweout.online.jnal.repository.entity;


import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WkoutJnal")
@Table(name="WKOUT_JNAL")
@SequenceGenerator(
        name = "SEQ_WKOUT_JNAL_GENERATOR"
        , sequenceName = "SEQ_WKOUT_JNAL"
        , initialValue = 1
        , allocationSize = 1
)
public class WkoutJnal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WKOUT_JNAL_GENERATOR")
    @Column(name="JNAl_ID", nullable = false, columnDefinition = "NUMBER(15)")
    private Long jnalId;

    @ManyToOne
    @JoinColumn(name = "MBR_ID")
    private Member member;

    @Embedded
    private YyyyMmDd yyyyMmDd;

    @Column(name="COMMENTS", nullable = true, columnDefinition = "VARCHAR2(500)")
    private String comments;

}
