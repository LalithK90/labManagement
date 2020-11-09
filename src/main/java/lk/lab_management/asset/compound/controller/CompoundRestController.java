package lk.lab_management.asset.compound.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lk.lab_management.asset.compound.service.CompoundService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/compound")
public class CompoundRestController {
    private final CompoundService compoundService;

    public CompoundRestController(CompoundService compoundService) {
        this.compoundService = compoundService;
    }

    @GetMapping("/price")
    public MappingJacksonValue getPrice(@RequestParam Integer id){
        //MappingJacksonValue

        //employeeService.findByWorkingPlace(workingPlaceService.findById(id));

        //Create new mapping jackson value and set it to which was need to filter
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(compoundService.findById(id));

        //simpleBeanPropertyFilter :-  need to give any id to addFilter method and created filter which was mentioned
        // what parameter's necessary to provide
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "price");
        //filters :-  set front end required value to before filter

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("Compound", simpleBeanPropertyFilter);
        //Employee :- need to annotate relevant class with JsonFilter  {@JsonFilter("Employee") }
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
