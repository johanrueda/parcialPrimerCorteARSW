package eci.arsw.covidanalyzer.Persistence;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

import java.util.Set;
import java.util.UUID;

public interface iCovidPersistence {

    void saveResult(Result result,ResultType type);
    boolean personUpdate (UUID id,ResultType resultType);
    Set<Result> getResult(ResultType resultType);


}
