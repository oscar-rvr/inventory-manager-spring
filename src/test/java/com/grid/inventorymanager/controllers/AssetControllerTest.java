package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.service.AssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AssetControllerTest {
    @Autowired
    private MockMvc mockMvc;  // MockMvc permite realizar peticiones HTTP simuladas

    @MockBean
    private AssetService assetService;  // Inyectamos el servicio

    @Test
    void testShowAssets() throws Exception {
        // Arrange: Creamos datos mockeados para devolver desde el servicio
        List<Asset> mockAssets = List.of(new Asset(1, "Asset1", "Description1", "SN001", null));
        when(assetService.findAll()).thenReturn(mockAssets);

        // Act: Realizamos la petición GET al controlador
        MvcResult result = mockMvc.perform(get("/assets"))
                .andExpect(status().isOk())   // Verificamos que el status sea 200 OK
                .andExpect(view().name("assets"))  // Verificamos que la vista renderizada sea "assets"
                .andExpect(model().attributeExists("assets"))  // Verificamos que el modelo tenga el atributo "assets"
                .andExpect(model().attribute("assets", mockAssets))  // Verificamos que el valor del atributo sea correcto
                .andReturn();  // Ejecuta la petición
    }
}
