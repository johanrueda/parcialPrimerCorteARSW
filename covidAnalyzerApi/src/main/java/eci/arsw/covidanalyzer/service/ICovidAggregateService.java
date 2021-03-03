package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public interface ICovidAggregateService {


    /**
     * Add a new result into the specified result type storage.
     *
     * @param result
     * @param type
     * @return
     */
    void aggregateResult(Result result, ResultType type) throws Excepcion;

    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
    List<Result> getResult(ResultType type);

    /**
     *  @param id
     * @param type
     * @return
     */
    void  upsertPersonWithMultipleTests(UUID id, ResultType type);


}
