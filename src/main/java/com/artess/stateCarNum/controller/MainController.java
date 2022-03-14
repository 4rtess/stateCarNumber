package com.artess.stateCarNum.controller;

import com.artess.stateCarNum.entity.CarNumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Car number controller")
@RestController
public class MainController {

    //BEGIN OF DOCUMENTATION
    @Operation(summary = "Create random car number")
    //END OF DOCUMENTATION
    @GetMapping("/number/random")
    public String randomCarNumber() {
        return CarNumber.createRandomCarNumber().toString();
    }

    //BEGIN OF DOCUMENTATION
    @Operation(summary = "Get next car number after the last",
    description = "If there is no last car number(not created with /random), return first car number A000AA")
    //END OF DOCUMENTATION
    @GetMapping("/number/next")
    public String nextCarNumber() {
        return CarNumber.nextCarNumber().toString();
    }
}
