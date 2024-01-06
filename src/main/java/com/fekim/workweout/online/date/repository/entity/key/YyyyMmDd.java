package com.fekim.workweout.online.date.repository.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YyyyMmDd implements Serializable {

    @Column(name="YYYY", nullable = false, columnDefinition = "VARCHAR2(4)")
    private String yyyy;

    @Column(name="MM", nullable = false, columnDefinition = "VARCHAR2(2)")
    private String mm;

    @Column(name="DD", nullable = false, columnDefinition = "VARCHAR2(2)")
    private String dd;

}
