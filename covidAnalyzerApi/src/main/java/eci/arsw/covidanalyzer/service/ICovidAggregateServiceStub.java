package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

import java.util.UUID;

public class ICovidAggregateServiceStub implements  ICovidAggregateService{
    /**
     * Add a new result into the specified result type storage.
     *
     * @param result
     * @param type
     * @return
     */
    @Override
    public boolean aggregateResult(Result result, ResultType type) {
        return false;
    }

    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
    @Override
    public boolean getResult(ResultType type) {
        return false;
    }

    /**
     * @param id
     * @param type
     */
    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) {

    }
}
