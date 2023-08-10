package br.com.fiapsoat.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@AllArgsConstructor
@RestController
@RequestMapping("internal/application-status")
public class ApplicationStatusController {

    private final ApplicationContext context;
    private final ApplicationAvailability applicationAvailability;

    @PutMapping(path = "liveness")
    private Boolean updateLivenessState(LivenessState state){
        AvailabilityChangeEvent.publish(context, state);
        return applicationAvailability.getLivenessState().equals(state);
    }

    @PutMapping(path = "readiness")
    private Boolean updateReadinessState(ReadinessState state){
        AvailabilityChangeEvent.publish(context, state);
        return applicationAvailability.getReadinessState().equals(state);
    }

}
