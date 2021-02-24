package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/covid/result")
public class CovidAggregateController {
    @Autowired
    @Qualifier("ICovidAggregateServiceStub")
    ICovidAggregateService covidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    public ResponseEntity addTruePositiveResult(Result result) {
        //TODO
        if(covidAggregateService.aggregateResult(result, ResultType.TRUE_POSITIVE)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return null;
    }

    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.POST)
    public ResponseEntity addTrueNegativeResult(Result result) {
        //TODO
        if(covidAggregateService.aggregateResult(result, ResultType.TRUE_NEGATIVE)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return null;
    }

    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.POST)
    public ResponseEntity addFalsePositiveResult(Result result) {
        //TODO
        if(covidAggregateService.aggregateResult(result, ResultType.FALSE_POSITIVE)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return null;
    }

    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.POST)
    public ResponseEntity addFalseNegativeResult(Result result) {
        //TODO
       if(covidAggregateService.aggregateResult(result, ResultType.FALSE_NEGATIVE)){
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
        return null;
    }

    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        //TODO
        //covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_POSITIVE),HttpStatus.OK);
    }

    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.GET)
    public ResponseEntity getTrueNegativeResult() {
        //TODO
        //covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_NEGATIVE),HttpStatus.OK);
    }

    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveFalseResult() {
        //TODO
        //covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.FALSE_POSITIVE),HttpStatus.OK);
    }

    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.GET)
    public ResponseEntity getTruePositivefalseResult() {
        //TODO
        //covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.FALSE_NEGATIVE),HttpStatus.OK);
    }


    //TODO: Implemente el método.

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity savePersonaWithMultipleTests() {
        //TODO
        covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return null;
    }
    
}