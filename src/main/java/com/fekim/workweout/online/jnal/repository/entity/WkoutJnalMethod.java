package com.fekim.workweout.online.jnal.repository.entity;

import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WkoutJnalMethod")
@Table(name="WKOUT_JNAL_METHOD")
@SequenceGenerator(
        name = "SEQ_WKOUT_JNAL_METHOD_GENERATOR"
        , sequenceName = "SEQ_WKOUT_JNAL_METHOD"
        , initialValue = 1
        , allocationSize = 1
)
public class WkoutJnalMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WKOUT_JNAL_METHOD_GENERATOR")
    @Column(name="JNAL_METHOD_ID", nullable = false, columnDefinition = "NUMBER(17)")
    private Long jnalMethodId;

    @ManyToOne
    @JoinColumn(name = "JNAL_ID")
    private WkoutJnal wkoutJnal;

    @ManyToOne
    @JoinColumn(name = "METHOD_ID")
    private WkoutMethod wkoutMethod;

    @Column(name="WEIGHT", nullable = true, columnDefinition = "NUMBER(4)")
    private Long weight;

    @Column(name="SETS", nullable = true, columnDefinition = "NUMBER(4)")
    private Long sets;

    @Column(name="REPS", nullable = true, columnDefinition = "NUMBER(4)")
    private Long reps;

    @Column(name="REST_TIME", nullable = true, columnDefinition = "NUMBER(5)")
    private Long restTime;

}
