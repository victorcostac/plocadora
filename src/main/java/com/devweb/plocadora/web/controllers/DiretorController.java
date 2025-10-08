package com.devweb.plocadora.web.controllers;


import com.devweb.plocadora.web.api.DiretorApi;
import com.devweb.plocadora.web.model.DiretorApiModel;
import com.devweb.plocadora.web.model.DiretorCriadoApiModel;
import com.devweb.plocadora.web.model.NovoDiretorApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiretorController implements DiretorApi {
    /**
     * @param diretorId (required)
     * @return
     */
    @Override
    public ResponseEntity<DiretorApiModel> deleteDiretor(String diretorId) {
        return null;
    }

    /**
     * @param diretorId (required)
     * @return
     */
    @Override
    public ResponseEntity<DiretorApiModel> getDiretor(String diretorId) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<List<DiretorApiModel>> getDiretores() {
        return null;
    }

    /**
     * @param novoDiretorApiModel (optional)
     * @return
     */
    @Override
    public ResponseEntity<DiretorCriadoApiModel> postDiretor(NovoDiretorApiModel novoDiretorApiModel) {
        return null;
    }

    /**
     * @param diretorId       (required)
     * @param diretorApiModel (optional)
     * @return
     */
    @Override
    public ResponseEntity<DiretorApiModel> putDiretor(String diretorId, DiretorApiModel diretorApiModel) {
        return null;
    }
}
