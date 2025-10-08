package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.web.api.AtorApi;
import com.devweb.plocadora.web.model.AtorApiModel;
import com.devweb.plocadora.web.model.AtorCriadoApiModel;
import com.devweb.plocadora.web.model.NovoAtorApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AtorController implements AtorApi{
    /**
     * @param novoAtorApiModel (optional)
     * @return
     */
    @Override
    public ResponseEntity<AtorCriadoApiModel> cadastroAtor(NovoAtorApiModel novoAtorApiModel) {
        return null;
    }

    /**
     * @param atorId (required)
     * @return
     */
    @Override
    public ResponseEntity<AtorApiModel> deleteAtor(String atorId) {
        return null;
    }

    /**
     * @param atorId (required)
     * @return
     */
    @Override
    public ResponseEntity<AtorApiModel> getAtor(String atorId) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<List<AtorApiModel>> getAtores() {
        return null;
    }

    /**
     * @param atorId       (required)
     * @param atorApiModel (optional)
     * @return
     */
    @Override
    public ResponseEntity<AtorApiModel> putAtor(String atorId, AtorApiModel atorApiModel) {
        return null;
    }
}
