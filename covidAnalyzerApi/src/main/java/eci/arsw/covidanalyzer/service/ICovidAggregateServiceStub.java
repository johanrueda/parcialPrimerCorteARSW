package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.Persistence.covidPersistence;
import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
@Service("ICovidAggregateServiceStub")
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
        new covidPersistence().saveResult(result,type);
        return true;
    }

    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
    @Override
    public Set<Result> getResult(ResultType type) {
        return new covidPersistence().getResult(type);
    }

    /**
     * @param id
     * @param type
     * @return
     */
    @Override
    public boolean upsertPersonWithMultipleTests(UUID id, ResultType type) {
        return new covidPersistence().personUpdate(id,type);
    }
}
