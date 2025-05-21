package com.sumitsee.archival_service.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.sumitsee.archival_service.entity.config.ArchivalJpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArchivalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testSaveConfig() {
        String url = "http://localhost:" + port + "/api/archival/config";

        ArchivalJpaConfig config = new ArchivalJpaConfig();
        config.setTableName("users");
        config.setCriteriaColumn("created_at");
        config.setArchiveAfterMonths(6);
        config.setDeleteAfterMonths(24);
        config.setPageSize(500);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ArchivalJpaConfig> request = new HttpEntity<>(config, headers);
        var response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Configuration saved!");
    }

    @Test
    void testRunArchive() {
        String url = "http://localhost:" + port + "/api/archival/run";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        var response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Archival Complete!");
    }
}
