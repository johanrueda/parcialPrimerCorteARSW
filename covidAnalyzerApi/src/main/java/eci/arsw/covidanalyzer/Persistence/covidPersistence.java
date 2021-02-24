package eci.arsw.covidanalyzer.Persistence;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

import java.util.Set;
import java.util.UUID;

public class covidPersistence implements iCovidPersistence {
    @Override
    public void saveResult(Result result, ResultType type) {
    }

    @Override
    public boolean personUpdate(UUID id, ResultType resultType) {
        return false;
    }

    @Override
    public Set<Result> getResult(ResultType resultType) {
        return null;
    }
}
