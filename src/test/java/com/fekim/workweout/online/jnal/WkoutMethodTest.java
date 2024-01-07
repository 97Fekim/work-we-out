package com.fekim.workweout.online.jnal;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.jnal.service.WkoutMethodService;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodListDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WkoutMethodTest {

    @Autowired
    private WkoutMethodService wkoutMethodService;

    @Test
    void getAllWkoutMethodPass() {

        WkoutMethodListDTO wkoutMethodListDTO = wkoutMethodService.getAllWkoutMethod();

        System.out.println("===============getAllWkoutMethodPass===============");
        for (WkoutMethodDTO dto : wkoutMethodListDTO.getWkoutMethodDTOList()) {
            System.out.println(dto.getMethodId()+" "+dto.getMethodNm()+" "+dto.getTargetPart());
        }
        System.out.println("===============getAllWkoutMethodPass===============");

        Assertions.assertThat(wkoutMethodListDTO.getWkoutMethodDTOList().size() > 0);

    }

}
