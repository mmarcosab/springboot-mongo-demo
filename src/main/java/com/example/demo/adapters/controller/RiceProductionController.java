package com.example.demo.adapters.controller;

import com.example.demo.adapters.database.document.RiceProductionData;
import com.example.demo.adapters.database.repository.SpringDataRiceProductionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rice-production")
public class RiceProductionController {

    private final Logger logger = LoggerFactory.getLogger(RiceProductionController.class);
    private final SpringDataRiceProductionRepository springDataRiceProductionRepository;

    public RiceProductionController(final SpringDataRiceProductionRepository springDataRiceProductionRepository) {
        this.springDataRiceProductionRepository = springDataRiceProductionRepository;
    }

    @GetMapping("/error")
    public ResponseEntity<?> createError() {
        logger.error("log error");
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/warn")
    public ResponseEntity<?> createWarn() {
        logger.warn("log warn");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final RiceProductionData riceProductionData) {
        return ResponseEntity.ok(springDataRiceProductionRepository.save(riceProductionData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody final RiceProductionData riceProductionData) {
        final var response = springDataRiceProductionRepository.findById(id);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        final var responseUpdated = RiceProductionData.update(id, riceProductionData);
        return ResponseEntity.ok(springDataRiceProductionRepository.save(responseUpdated));
    }

    @GetMapping
    public ResponseEntity<?> listRiceProduction(
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "5") final Integer size
    ) {
        final var response = springDataRiceProductionRepository.findAll(PageRequest.of(pageNumber, size));
        return ResponseEntity.ok(convertToResponse(response));
    }

    @GetMapping("/area/{countryName}")
    public ResponseEntity<?> findByArea(@PathVariable final String countryName,
                                        @RequestParam(defaultValue = "0") final Integer pageNumber,
                                        @RequestParam(defaultValue = "5") final Integer size) {
        final var response = springDataRiceProductionRepository.findByArea(countryName, PageRequest.of(pageNumber, size));
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResponse(response));
    }

    @GetMapping("/value/{value}")
    public ResponseEntity<?> listByValue(@RequestParam final String value,
                                        @RequestParam(defaultValue = "higher") final String comparator,
                                        @RequestParam(defaultValue = "0") final Integer pageNumber,
                                        @RequestParam(defaultValue = "5") final Integer size) {
        Page<RiceProductionData> response;
        if ("higher".equals(comparator)) {
            response = springDataRiceProductionRepository.listByValueEqualOrHigher(value, PageRequest.of(pageNumber, size));
        } else if ("less".equals(comparator)) {
            response = springDataRiceProductionRepository.listByValueEqualOrLess(value, PageRequest.of(pageNumber, size));
        } else {
            response = springDataRiceProductionRepository.findByValue(value, PageRequest.of(pageNumber, size));
        }
        return ResponseEntity.ok(convertToResponse(response));
    }

    @GetMapping("/areas")
    public ResponseEntity<?> findByAreas(@RequestParam final String areaOne,
                                         @RequestParam final String areaTwo,
                                         @RequestParam(defaultValue = "0") final Integer pageNumber,
                                         @RequestParam(defaultValue = "5") final Integer size) {
        return ResponseEntity.ok(convertToResponse(springDataRiceProductionRepository.findByAreas(
                areaOne, areaTwo, PageRequest.of(pageNumber, size)))
        );
    }

    @GetMapping("/values")
    public ResponseEntity<?> findByValues(@RequestParam final String beginValue,
                                          @RequestParam final String endValue,
                                          @RequestParam(defaultValue = "0") final Integer pageNumber,
                                          @RequestParam(defaultValue = "5") final Integer size) {
        return ResponseEntity.ok(convertToResponse(springDataRiceProductionRepository.findByValues(beginValue, endValue, PageRequest.of(pageNumber, size))));
    }

    @GetMapping("/years")
    public ResponseEntity<?> findByPeriod(@RequestParam final String beginValue,
                                          @RequestParam final String endValue,
                                          @RequestParam(defaultValue = "0") final Integer pageNumber,
                                          @RequestParam(defaultValue = "5") final Integer size) {
        return ResponseEntity.ok(convertToResponse(springDataRiceProductionRepository.findByPeriod(beginValue, endValue, PageRequest.of(pageNumber, size))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        final var response = springDataRiceProductionRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
        springDataRiceProductionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Map<String, Object> convertToResponse(final Page<RiceProductionData> pagePersons) {
        final Map<String, Object> response = new HashMap<>();
        response.put("rice-productions", pagePersons.getContent());
        response.put("current-page", pagePersons.getNumber());
        response.put("total-items", pagePersons.getTotalElements());
        response.put("total-pages", pagePersons.getTotalPages());
        return response;
    }
}
