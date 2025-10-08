package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.web.api.ClasseApi;
import com.devweb.plocadora.web.model.ClasseApiModel;
import com.devweb.plocadora.web.model.ClasseCriadaApiModel;
import com.devweb.plocadora.web.model.NovaClasseApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClasseController implements ClasseApi{
    /**
     * @param novaClasseApiModel Post the necessary fields for the API to create a new user. (optional)
     * @return
     */
    @Override
    public ResponseEntity<ClasseCriadaApiModel> cadastroClasse(NovaClasseApiModel novaClasseApiModel) {
        return null;
    }

    /**
     * @param classeId (required)
     * @return
     */
    @Override
    public ResponseEntity<ClasseApiModel> deleteClasse(String classeId) {
        return null;
    }

    /**
     * @param classeId (required)
     * @return
     */
    @Override
    public ResponseEntity<ClasseApiModel> getClasse(String classeId) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<List<ClasseApiModel>> getClasses() {
        return null;
    }

    /**
     * @param classeId       (required)
     * @param classeApiModel (optional)
     * @return
     */
    @Override
    public ResponseEntity<ClasseApiModel> putClasse(String classeId, ClasseApiModel classeApiModel) {
        return null;
    }
}
